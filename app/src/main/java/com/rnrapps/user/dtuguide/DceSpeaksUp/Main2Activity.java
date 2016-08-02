package com.rnrapps.user.dtuguide.DceSpeaksUp;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rnrapps.user.dtuguide.AppController;
import com.rnrapps.user.dtuguide.NotifyService;
import com.rnrapps.user.dtuguide.R;
import com.rnrapps.user.dtuguide.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,PostsAdapter.AdapterCallback {

    private PostsAdapter postsAdapter;
    private List<FeedItem> feedItems;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    public static String FACEBOOK_URL = "https://www.facebook.com/DceSpeaksUp";
    public static String FACEBOOK_PAGE_ID = "382057938566656";
    private String URL_FEED = "https://graph.facebook.com/382057938566656/feed?fields=id,full_picture,message,story,created_time,link,comments&access_token=1610382879221507|eQEEkGV4wk9PHCBzrw9Whbdzyuc";
    private String id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBardce);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.cordinatorlayout);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(getApplicationContext());
                facebookIntent.setData(Uri.parse(facebookUrl));
                startActivity(facebookIntent);
            }
        });
        feedItems = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        postsAdapter = new PostsAdapter(this, feedItems);

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonFeed(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                    URL_FEED, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    progressBar.setVisibility(View.VISIBLE);
                    if (response != null) {
                        parseJsonFeed(response);
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    progressBar.setVisibility(View.GONE);
                    Snackbar.make(coordinatorLayout, "No Internet :(", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            });

            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(jsonReq);
        }


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Refreshing data on server
                feedItems.clear();
                postsAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                        URL_FEED, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        if (response != null) {
                            parseJsonFeed(response);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });

                // Adding request to volley request queue
                AppController.getInstance().addToRequestQueue(jsonReq);
            }
        });

//        listView.setAdapter(listAdapter);
        recyclerView.setAdapter(postsAdapter);

    }

    private void parseJsonFeed(final JSONObject response) {

        mSwipeRefreshLayout.setRefreshing(true);

        new Thread() {
            @Override
            public void run() {
                try {
                    JSONArray feedArray = response.getJSONArray("data");

                    for (int i = 0; i < feedArray.length(); i++) {
                        JSONObject feedObj = (JSONObject) feedArray.get(i);

                        FeedItem item = new FeedItem();
                        item.setId(feedObj.getString("id"));
                        if(i==0)
                            id=item.getId();
                        // Image might be null sometimes
                        String image = feedObj.isNull("full_picture") ? null : feedObj
                                .getString("full_picture");
                        item.setImge(image);

                        JSONObject comments = null;
                        ArrayList<CommentItem> commentItems = new ArrayList<>();
                        if (feedObj.has("comments")) {

                            comments = feedObj.getJSONObject("comments");
                            JSONArray commentsData = comments.getJSONArray("data");
                            for (int j = 0; j < commentsData.length(); j++) {
                                JSONObject commentsObject = (JSONObject) commentsData.get(j);
                                CommentItem commentItem = new CommentItem();
                                commentItem.setId(commentsObject.getString("id"));
                                commentItem.setComment(commentsObject.getString("message"));
                                commentItem.setFrom(commentsObject.getJSONObject("from").getString("name"));
                                commentItem.setTimeStamp(commentsObject.getString("created_time"));
                                commentItems.add(commentItem);
                            }
                        }

                        item.setCommentItems(commentItems);

                        if (feedObj.opt("message") != null)
                            item.setStatus(feedObj.getString("message"));
                        else
                            item.setStatus(feedObj.getString("story"));

                        item.setTimeStamp(feedObj.getString("created_time"));

                        String feedUrl = feedObj.isNull("link") ? null : feedObj
                                .getString("link");
                        item.setUrl(feedUrl);

                        feedItems.add(item);

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            postsAdapter.notifyDataSetChanged();
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    });

                    Utils.configureNotificationService(getApplicationContext(),id);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Utils.NavDrawer(item, this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


        @Override
        public void onMethodCallback ( final ArrayList<CommentItem> commentItems){
            FragmentManager fm = getFragmentManager();
            CommentsDialogFragment newFragment = new CommentsDialogFragment();
            Bundle b=new Bundle();
            b.putParcelableArrayList("comments",commentItems);
            newFragment.setArguments(b);
            newFragment.show(fm,"Comments");

        }

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    }


