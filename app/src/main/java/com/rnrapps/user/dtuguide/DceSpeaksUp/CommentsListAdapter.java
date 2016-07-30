package com.rnrapps.user.dtuguide.DceSpeaksUp;

/**
 * Created by rohanagarwal94 on 27/7/16.
 */

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rnrapps.user.dtuguide.R;
import com.rnrapps.user.dtuguide.Utils;

import java.util.ArrayList;


public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.MyViewHolder> {

    private ArrayList<CommentItem> commentItems;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView commentTime, from, comment;

        public MyViewHolder(View view) {
            super(view);
            view.bringToFront();
            commentTime = (TextView) view
                    .findViewById(R.id.commenttime);
            from = (TextView) view
                    .findViewById(R.id.from);
            comment = (TextView) view.findViewById(R.id.comment);

        }


    }


    public CommentsListAdapter(ArrayList<CommentItem> commentItems) {
        this.commentItems=commentItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.commentview, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final CommentItem commentItem = commentItems.get(position);

        if (commentItem.getTimeStamp() != null) {
            String a = commentItem.getTimeStamp();
            holder.commentTime.setText(Utils.getTimeFromTimestamp(a));
        }

        if (!TextUtils.isEmpty(commentItem.getComment())) {
            holder.comment.setText(commentItem.getComment());
            holder.comment.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            holder.comment.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(commentItem.getFrom())) {
            holder.from.setText(commentItem.getFrom());
            holder.from.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            holder.from.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return commentItems.size();
    }

}
