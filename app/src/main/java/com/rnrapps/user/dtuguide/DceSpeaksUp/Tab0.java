//package com.rnrapps.user.dtuguide.DceSpeaksUp;
//
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.os.Bundle;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.annotation.SuppressLint;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.android.volley.Cache;
//import com.android.volley.Cache.Entry;
//import com.android.volley.Request.Method;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.rnrapps.user.dtuguide.AppController;
//import com.rnrapps.user.dtuguide.R;
//
//
///**
// * Created by rohanpc on 2/8/2016.
// */
//public class Tab0 extends Fragment {
//
//
//    private ListView listView;
//    private FeedListAdapter listAdapter;
//    boolean isLoading=false;
//    private String nexturl;
//    private List<FeedItem> feedItems;
//    private Map<FeedItem,ArrayList<CommentItem>> feedsWithComments;
//
//    SwipeRefreshLayout mSwipeRefreshLayout;
//    private String URL_FEED = "https://graph.facebook.com/382057938566656/feed?fields=id,full_picture,message,story,created_time,link,comments&access_token=1610382879221507|eQEEkGV4wk9PHCBzrw9Whbdzyuc";
//
//    @SuppressLint("NewApi")
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v =inflater.inflate(R.layout.tab_0,container,false);
//
//        listView = (ListView)v.findViewById(R.id.list);
//
//        feedItems=new ArrayList<>();
//
//        feedsWithComments = Collections.synchronizedMap(new HashMap<FeedItem, ArrayList<CommentItem>>());
//
//        listAdapter = new FeedListAdapter(getActivity(), feedsWithComments,feedItems);
//
//
//        // We first check for cached request
//        Cache cache = AppController.getInstance().getRequestQueue().getCache();
//        Entry entry = cache.get(URL_FEED);
//        if (entry != null) {
//            // fetch the data from cache
//            try {
//                String data = new String(entry.data, "UTF-8");
//                try {
//                    parseJsonFeed(new JSONObject(data));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            // making fresh volley request and getting json
//            JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
//                    URL_FEED, null, new Response.Listener<JSONObject>() {
//
//                @Override
//                public void onResponse(JSONObject response) {
//
//                    if (response != null) {
//                        parseJsonFeed(response);
//                    }
//                }
//            }, new Response.ErrorListener() {
//
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                    Toast.makeText(getActivity(),"NO INTERNET",Toast.LENGTH_LONG).show();
//                }
//            });
//
//            // Adding request to volley request queue
//            AppController.getInstance().addToRequestQueue(jsonReq);
//        }
//
//        mSwipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipeContainer);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                //Refreshing data on server
//                feedItems.clear();
//                JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
//                        URL_FEED, null, new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        if (response != null) {
//                            parseJsonFeed(response);
////                            listAdapter.notifyDataSetChanged();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        mSwipeRefreshLayout.setRefreshing(false);
//                    }
//                });
//
//                // Adding request to volley request queue
//                AppController.getInstance().addToRequestQueue(jsonReq);
//
//
//            }
//        });
//
//        listView.setAdapter(listAdapter);
//
//
//        return v;
//    }
//
//
//    /**
//     * Parsing json reponse and passing the data to feed view list adapter
//     * */
//    private void parseJsonFeed(final JSONObject response) {
//        mSwipeRefreshLayout.setRefreshing(true);
//
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    JSONArray feedArray = response.getJSONArray("data");
////            JSONObject nexturljson=response.getJSONObject("paging");
////            nexturl=nexturljson.getString("next");
//                    for (int i = 0; i < feedArray.length(); i++) {
//                        Log.d("feed array length",String.valueOf(feedArray.length()));
//                        JSONObject feedObj = (JSONObject) feedArray.get(i);
//
//                        FeedItem item = new FeedItem();
//                        item.setId(feedObj.getString("id"));
//
//
//                        // Image might be null sometimes
//                        String image = feedObj.isNull("full_picture") ? null : feedObj
//                                .getString("full_picture");
//                        item.setImge(image);
//
//                        JSONObject comments=null;
//                        ArrayList<CommentItem> commentItems = new ArrayList<>();
//                        if(feedObj.has("comments")) {
//
//                            comments = feedObj.getJSONObject("comments");
//                            JSONArray commentsData = comments.getJSONArray("data");
//                            for (int j = 0; j < commentsData.length(); j++) {
//                                JSONObject commentsObject = (JSONObject) commentsData.get(j);
//                                CommentItem commentItem = new CommentItem();
//                                commentItem.setId(commentsObject.getString("id"));
//                                commentItem.setComment(commentsObject.getString("message"));
//                                commentItem.setFrom(commentsObject.getJSONObject("from").getString("name"));
//                                commentItem.setTimeStamp(commentsObject.getString("created_time"));
//                                commentItems.add(commentItem);
//                            }
//                        }
//
//
//                        if (feedObj.opt("message") != null)
//                            item.setStatus(feedObj.getString("message"));
//                        else
//                            item.setStatus(feedObj.getString("story"));
//
//                        item.setTimeStamp(feedObj.getString("created_time"));
//
//                        // url might be null sometimes
//                        String feedUrl = feedObj.isNull("link") ? null : feedObj
//                                .getString("link");
//                        item.setUrl(feedUrl);
//
//                        feedItems.add(item);
//                       if(comments!=null)
//                        feedsWithComments.put(item, commentItems);
//                        else
//                        feedsWithComments.put(item,null);
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//
//        // notify data changes to list adapter
//        listAdapter.notifyDataSetChanged();
//        mSwipeRefreshLayout.setRefreshing(false);
//    }
//
//
//}
