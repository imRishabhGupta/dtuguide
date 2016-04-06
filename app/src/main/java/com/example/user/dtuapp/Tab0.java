package com.example.user.dtuapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rohanpc on 2/8/2016.
 */
public class Tab0 extends Fragment {

    private static final String TAG = "RecyclerViewExample";

    private List<FeedItem> feedItemList = new ArrayList<FeedItem>();

    private RecyclerView mRecyclerView;
    String url;



    private MyRecyclerAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private JSONObject newpage;
    LinearLayoutManager linearLayoutManager;private boolean mLoading = false;
    private static int count=0;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_0, container, false);




        /* Initialize recyclerview */
        mRecyclerView = (RecyclerView)v. findViewById(R.id.rv);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        /*Downloading data from below url*/
        url = "https://graph.facebook.com/382057938566656/feed?fields=message,story,id,full_picture&access_token=1750413825187852|kUl9nlZFPvdGxGZX4WYabKKG2G4";
        new AsyncHttpTask().execute(url);

        mSwipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipeContainer);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Refreshing data on server
                feedItemList.clear();
                new AsyncHttpTask().execute(url);

            }
        });

//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int totalItem = linearLayoutManager.getItemCount();
//                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//
//                if (!mLoading && lastVisibleItem == totalItem - 1) {
//                    mLoading = true;
//                    parsenewpage();
//                    mLoading = false;
//                }
//            }
//        });

            return v;

    }





    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;
            Integer result = 0;
            HttpURLConnection urlConnection = null;

            try {
                /* forming th java.net.URL object */
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                /* for Get request */
                urlConnection.setRequestMethod("GET");

                int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
                if (statusCode ==  200) {

                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }

                    parseResult(response.toString());
                    result = 1; // Successful
                }else{
                    result = 0; //"Failed to fetch data!";
                }

            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }

            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {

            /* Download complete. Lets update UI */
            if (result == 1) {

                if(adapter==null)
                adapter = new MyRecyclerAdapter(getActivity(), feedItemList);
//                int cursize=adapter.getItemCount();
//                adapter.notifyItemRangeInserted(cursize,feedItemList.size());
                mRecyclerView.setAdapter(adapter);


                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }

            } else {
                Log.e(TAG, "Failed to fetch data!");
            }
        }
    }

//    private void parsenewpage()
//    {
//
//        new AsyncHttpTask().execute(newpage.optString("next"));
//    }




    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("data");
//            newpage=response.optJSONObject("paging");


            /*Initialize array if null*/
            if (null == feedItemList) {
                feedItemList = new ArrayList<FeedItem>();
            }

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);

                FeedItem item = new FeedItem();
                if(post.opt("message")!=null)
                    item.setTitle(post.optString("message"));
                else
                item.setTitle(post.optString("story"));

                    if (post.opt("full_picture") != null) {
                        item.setThumbnail(post.optString("full_picture"));
                    }


                feedItemList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
