package com.rnrapps.user.dtuguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by rohanpc on 10/18/2015.
 */
public class Tab2 extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    LinearLayout cll;
    ArrayList<Society> items;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_2,container,false);
        // Calling the RecyclerView
        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        cll=(LinearLayout)v.findViewById(R.id.cll);
        // The number of Columns
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        items=new ArrayList<>();
        mAdapter = new GridAdapter(items,getContext());
        mRecyclerView.setAdapter(mAdapter);


        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((GridAdapter) mAdapter).setOnItemClickListener(new GridAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                final Society society=items.get(position);
                final String title=society.getTitle();
                final int image=society.getImage();
                final String description=getString(society.getDescription()).toString();
                final String contactName=society.getContactName();
                final String contactNumber=society.getContactNumber();
                Intent intent=new Intent(getActivity(),ContactActivity.class);

                    intent.putExtra("title",title);
                    intent.putExtra("image",image);
                    intent.putExtra("description",description);
                    intent.putExtra("contactName",contactName);
                    intent.putExtra("contactNumber",contactNumber);
                            startActivity(intent);

            }
        });
    }
}