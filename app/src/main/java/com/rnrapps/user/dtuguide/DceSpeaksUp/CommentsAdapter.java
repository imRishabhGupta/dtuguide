package com.rnrapps.user.dtuguide.DceSpeaksUp;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rnrapps.user.dtuguide.R;
import com.rnrapps.user.dtuguide.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rohanagarwal94 on 20/7/16.
 */
public class CommentsAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<CommentItem> commentItems;

    public CommentsAdapter(Context context,ArrayList<CommentItem> commentItems) {
        this.context=context;
        this.commentItems = commentItems;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
            return commentItems.size();
    }

    @Override
    public Object getItem(int position) {
        return commentItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = inflater.inflate(R.layout.commentview, null);

        TextView from = (TextView) convertView
                .findViewById(R.id.from);
        TextView comment = (TextView) convertView
                .findViewById(R.id.comment);
        TextView commentTime = (TextView) convertView
                .findViewById(R.id.commenttime);


            CommentItem commentItem = commentItems.get(position);

            if (commentItem.getTimeStamp() != null) {
                String a = commentItem.getTimeStamp();
                commentTime.setText(Utils.getTimeFromTimestamp(a));
            }

            if (!TextUtils.isEmpty(commentItem.getComment())) {
                comment.setText(commentItem.getComment());
                comment.setVisibility(View.VISIBLE);
            } else {
                // status is empty, remove from view
                comment.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(commentItem.getFrom())) {
                from.setText(commentItem.getFrom());
                from.setVisibility(View.VISIBLE);
            } else {
                // status is empty, remove from view
                from.setVisibility(View.GONE);
            }

        return convertView;

    }
}
