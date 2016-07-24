package com.rnrapps.user.dtuguide.DceSpeaksUp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rnrapps.user.dtuguide.AboutDevelopers;
import com.rnrapps.user.dtuguide.AppController;
import com.rnrapps.user.dtuguide.CollegeMap.CampusMap;
import com.rnrapps.user.dtuguide.ImpContacts.SocietyActivity;
import com.rnrapps.user.dtuguide.Notes.NotesActivity;
import com.rnrapps.user.dtuguide.R;
import com.rnrapps.user.dtuguide.TimeTables.TimetableActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    private Map<FeedItem,ArrayList<CommentItem>> feedsWithComments;
    private FeedListAdapter listAdapter;
    private List<FeedItem> feedItems;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String URL_FEED = "https://graph.facebook.com/382057938566656/feed?fields=id,full_picture,message,story,created_time,link,comments&access_token=1610382879221507|eQEEkGV4wk9PHCBzrw9Whbdzyuc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        feedItems=new ArrayList<>();

        listView=(ListView)findViewById(R.id.list);
//        feedsWithComments = Collections.synchronizedMap(new HashMap<FeedItem, ArrayList<CommentItem>>());
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);
        listAdapter = new FeedListAdapter(this,feedItems);



        // We first check for cached request
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

                    if (response != null) {
                        parseJsonFeed(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getApplicationContext(),"NO INTERNET",Toast.LENGTH_LONG).show();
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

        listView.setAdapter(listAdapter);

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

                // Image might be null sometimes
                String image = feedObj.isNull("full_picture") ? null : feedObj
                        .getString("full_picture");
                item.setImge(image);

                JSONObject comments=null;
                ArrayList<CommentItem> commentItems = new ArrayList<>();
                if(feedObj.has("comments")) {

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

                if(feedObj.opt("message")!=null)
                    item.setStatus(feedObj.getString("message"));
                else
                    item.setStatus(feedObj.getString("story"));

                item.setTimeStamp(feedObj.getString("created_time"));

                // url might be null sometimes
                String feedUrl = feedObj.isNull("link") ? null : feedObj
                        .getString("link");
                item.setUrl(feedUrl);

                feedItems.add(item);

//                if(comments!=null)
//                    feedsWithComments.put(item, commentItems);
//                else
//                    feedsWithComments.put(item,null);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });

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

        int id = item.getItemId();

        if (id == R.id.nav_map) {
            Intent intent=new Intent(this,CampusMap.class);
            startActivity(intent);
        } else if (id == R.id.nav_societies) {
            Intent intent=new Intent(this,SocietyActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_notes) {
            Intent intent=new Intent(this,NotesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_timetable) {
            Intent intent=new Intent(this,TimetableActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "share body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        } else if (id == R.id.nav_rate) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + this.getPackageName())));
        } else if (id == R.id.nav_about) {
            Intent intent=new Intent(getApplicationContext(),AboutDevelopers.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_feedback){
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "feedback");
            intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
            intent.setData(Uri.parse("mailto:dtuapp16@gmail.com"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
