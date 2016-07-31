package com.rnrapps.user.dtuguide.DceSpeaksUp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rnrapps.user.dtuguide.R;

import java.util.ArrayList;

/**
 * Created by rohanagarwal94 on 30/7/16.
 */
public class CommentsDialogFragment extends DialogFragment {
    private RecyclerView mRecyclerView;
    private ArrayList<CommentItem> commentItems;
    // this method create view for your Dialog

    public CommentsDialogFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate layout with recycler view
        View v = inflater.inflate(R.layout.content_comment, container, false);
        getDialog().getWindow().getAttributes().alpha = 0.8f;
        commentItems=this.getArguments().getParcelableArrayList("comments");
        Log.d("comments size",String.valueOf(commentItems.size()));
        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view_comment);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CommentsListAdapter adapter = new CommentsListAdapter(commentItems);
        mRecyclerView.setAdapter(adapter);
        return v;
    }
}