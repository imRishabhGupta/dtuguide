package com.rnrapps.user.dtuguide.DceSpeaksUp;

/**
 * Created by rohanagarwal94 on 26/7/16.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.rnrapps.user.dtuguide.AppController;
import com.rnrapps.user.dtuguide.R;
import com.rnrapps.user.dtuguide.Utils;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {

    private Activity activity;
    private List<FeedItem> feedItems;
    private AdapterCallback mAdapterCallback;
    private ArrayList<CommentItem> commentItems;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView timestamp,statusMsg,url;
        public FeedImageView feedImageView;
        public Button getComments;

        public MyViewHolder(View view) {
            super(view);
            view.bringToFront();
            timestamp = (TextView) view
                    .findViewById(R.id.timestamp);
            statusMsg = (TextView) view
                    .findViewById(R.id.txtStatusMsg);
            url = (TextView) view.findViewById(R.id.txtUrl);
            feedImageView = (FeedImageView) view
                    .findViewById(R.id.feedImage1);
            getComments=(Button)view.findViewById(R.id.commentsbutton);

            getComments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FeedItem forComment=feedItems.get(getPosition());
//                    FeedItem forComment=feedItems.get(getAdapterPosition());   //Test this
                    commentItems = new ArrayList<>();
                    if(forComment.getCommentsSize()!=0) {
                        commentItems.addAll(forComment.getCommentItems());
                    }
                    if(commentItems.size()!=0)
//                        showDialog(v);     //This is ins
                        mAdapterCallback.onMethodCallback(commentItems);
                    else
                        Snackbar.make(v, "No Comments Yet :(", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();

                }
            });
        }
    }
    
    public PostsAdapter(Activity activity,List<FeedItem> feedItems) {
        this.feedItems=feedItems;
        this.activity=activity;
        try {
            this.mAdapterCallback = ((AdapterCallback) activity);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement AdapterCallback.");
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



        final FeedItem item=feedItems.get(position);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        String a=item.getTimeStamp();
        holder.timestamp.setText(Utils.getTimeFromTimestamp(a));

        if (!TextUtils.isEmpty(item.getStatus())) {
            holder. statusMsg.setText(item.getStatus());
            holder.statusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            holder.statusMsg.setVisibility(View.GONE);
        }

        // Checking for null feed url
        if (item.getUrl() != null) {
            holder.url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
                    + item.getUrl() + "</a> "));

            // Making url clickable
            holder.url.setMovementMethod(LinkMovementMethod.getInstance());
            holder.url.setVisibility(View.VISIBLE);
        } else {
            // url is null, remove from the view
            holder.url.setVisibility(View.GONE);
        }

        if (item.getImge() != null) {
            holder.feedImageView.setImageUrl(item.getImge(), imageLoader);
            holder.feedImageView.setVisibility(View.VISIBLE);
            holder.feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            holder.feedImageView.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {
        return feedItems.size();
    }


//    public void showDialog(View v)
//    {
////        CommentsAdapter commentsAdapter;
//        CommentsListAdapter commentsListAdapter;
//        final Dialog dialog = new Dialog(v.getContext(), R.style.cust_dialog);
//        dialog.setTitle("Comments");
//        View layout = activity.getLayoutInflater().inflate(R.layout.content_comment,null);
////        final LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
////        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        final RecyclerView recyclerView=(RecyclerView) layout.findViewById(R.id.recycler_view_comment);
//        Log.d("comment items",String.valueOf(commentItems));
////        recyclerView.setLayoutManager(layoutManager);
//        commentsListAdapter = new CommentsListAdapter(commentItems);
//        commentsListAdapter.notifyDataSetChanged();
//        recyclerView.setAdapter(commentsListAdapter);
//        dialog.setContentView(R.layout.content_comment);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.setCancelable(true);
//        dialog.show();
//    }

    public static interface AdapterCallback {
        void onMethodCallback(ArrayList<CommentItem> commentItems);
    }


}