package com.rnrapps.user.dtuguide;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * Created by rohanpc on 2/8/2016.
 */
public class Tab0 extends Fragment {


    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<FeedItem> feedItems;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private String URL_FEED = "https://graph.facebook.com/382057938566656/feed?fields=id,full_picture,message,story,created_time,link&access_token=1610382879221507|eQEEkGV4wk9PHCBzrw9Whbdzyuc";

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_0,container,false);

        listView = (ListView)v.findViewById(R.id.list);

        feedItems = new ArrayList<FeedItem>();

        listAdapter = new FeedListAdapter(getActivity(), feedItems);
        listView.setAdapter(listAdapter);

        // We first check for cached request
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Entry entry = cache.get(URL_FEED);
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
            JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
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

                    Toast.makeText(getActivity(),"NO INTERNET",Toast.LENGTH_LONG).show();
                }
            });

            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(jsonReq);
        }

        mSwipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipeContainer);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Refreshing data on server
                feedItems.clear();
                JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
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

        return v;
    }

    /**
     * Parsing json reponse and passing the data to feed view list adapter
     * */
    private void parseJsonFeed(JSONObject response) {
        mSwipeRefreshLayout.setRefreshing(true);
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
            }

            // notify data changes to list adapter
            listAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
