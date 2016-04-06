package com.example.user.dtuapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

/**
 * Created by rohanpc on 10/18/2015.
 */
public class Tab2 extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    LinearLayout cll;

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

        mAdapter = new GridAdapter();
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
                Intent intent=new Intent(getActivity(),ContactActivity.class);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
                    getContext().startActivity(intent, bundle);
                }
                else {
                    Animation animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.fade);
                    cll.startAnimation(animation1);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            Intent intent=new Intent(getActivity(),ContactActivity.class);

                            startActivity(intent);


                        }

                    }, 1000);

                }
            }
        });
    }
}