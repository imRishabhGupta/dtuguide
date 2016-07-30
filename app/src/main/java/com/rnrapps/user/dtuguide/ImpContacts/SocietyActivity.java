package com.rnrapps.user.dtuguide.ImpContacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.rnrapps.user.dtuguide.R;
import com.rnrapps.user.dtuguide.Utils;

import java.util.ArrayList;

public class SocietyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<Society> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_society);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        items=new ArrayList<>();
        mAdapter = new GridAdapter(items,getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

        ((GridAdapter) mAdapter).setOnItemClickListener(new GridAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                final Society society=items.get(position);
                final String title=society.getTitle();
                final int image=society.getImage();
                final String description=getString(society.getDescription());
                final String contactName=society.getContactName();
                final String contactNumber=society.getContactNumber();

                Intent intent=new Intent(getApplicationContext(),ContactActivity.class);

                intent.putExtra("title",title);
                intent.putExtra("image",image);
                intent.putExtra("description",description);
                intent.putExtra("contactName",contactName);
                intent.putExtra("contactNumber",contactNumber);
                startActivity(intent);

            }
        });


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
        Utils.NavDrawer(item,this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
