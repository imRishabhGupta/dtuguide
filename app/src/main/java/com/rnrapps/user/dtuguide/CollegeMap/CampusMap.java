package com.rnrapps.user.dtuguide.CollegeMap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.rnrapps.user.dtuguide.R;
import com.rnrapps.user.dtuguide.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CampusMap extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private AutoCompleteTextView actv;
    private List<MyItem> items = new ArrayList<>();
    private String m;
    private ArrayList<getmarkerfromstring> users = new ArrayList<>();
    private String[] countries = new String[576];
    private GoogleMap mMap;
    //private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
//                .addApi(LocationServices.API)
//                .build();
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setDropDownBackgroundResource(R.color.stroke_color);
        //setUpMapIfNeeded();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        initCamera();
        setUpMap();
    }

    @Override
    public void onResume() {
        super.onResume();
   //     setUpMapIfNeeded();
    }

//    private void setUpMapIfNeeded() {
//        // Do a null check to confirm that we have not already instantiated the map.
//        if (mMap == null) {
//            // Try to obtain the map from the SupportMapFragment.
//            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
////             Check if we were successful in obtaining the map.
//            if (mMap != null) {
//                setUpMap();
//            }
//        }
//    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 11:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setUpMap();
                }
        }
    }

    private void initCamera() {
        CameraPosition position = CameraPosition.builder()
                .target(new LatLng(28.75007207311156, 77.11772996932268))
                .zoom(18f)
                .bearing(0.0f)
                .tilt(40f)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setTrafficEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CampusMap.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},11);
        }
        else
            mMap.setMyLocationEnabled(true);
    }

    private void setUpMap() {

        //Location mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        //initCamera();
        ClusterManager<MyItem> mClusterManager = new ClusterManager<>(getApplicationContext(), mMap);
        //mMap.setOnCameraChangeListener(this);
        mMap.setOnCameraIdleListener(mClusterManager);
        //mMap.setOnCameraMoveListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        mClusterManager
                .setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
                    @Override
                    public boolean onClusterClick(final Cluster<MyItem> cluster) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                cluster.getPosition(), (float) Math.floor(mMap
                                        .getCameraPosition().zoom + 2)), 300,
                                null);

                        return true;
                    }
                });

        try {
            InputStream inputstream = getResources().getAssets().open("map.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
            m = reader.toString();
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                total.append(line);
            }
            m = total.toString();
        } catch (IOException ex) {
            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
        }

        try {
            JSONObject jsonObject = new JSONObject(m);
            JSONArray jsonArray = jsonObject.optJSONArray("map");
            int arraylength = jsonArray.length();

            for (int i = 0; i < arraylength; i++) {

                JSONObject jsonChildNode = jsonArray.getJSONObject(i);
                String title = jsonChildNode.optString("title");
                String subtitle = jsonChildNode.optString("subtitle");
                String floor = jsonChildNode.optString("floor");
                String type = jsonChildNode.optString("type");
                double lat1 = jsonChildNode.optDouble("latitude");
                double lng1 = jsonChildNode.optDouble("longitude");
                items.add(new MyItem(lat1,lng1, title, subtitle, floor));
                countries[2*i]= title;
                countries[2*i+1]= subtitle;
                getmarkerfromstring user = new getmarkerfromstring();
                user.setLat(lat1);
                user.setLng(lng1);
                user.setName(countries[i]);
                users.add(user);
                getmarkerfromstring user2 = new getmarkerfromstring();
                user2.setLat(lat1);
                user2.setLng(lng1);
                user2.setName(countries[2*i+1]);
                users.add(user2);

            }

            mClusterManager.addItems(items);
            mClusterManager.setRenderer(new MyClusterRenderer(getApplicationContext(), mMap, mClusterManager));
            mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
                @Override
                public boolean onClusterItemClick(MyItem myItem) {
                    Toast.makeText(getApplicationContext(), myItem.getfloor() + " floor",Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,countries);
        actv.setAdapter(adapter);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                //... your stuff
                String s=parent.getItemAtPosition(position).toString();
                int pos= Arrays.asList(countries).indexOf(s);
                LatLng lng=new LatLng(users.get(pos).getLat(),users.get(pos).getLng());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lng,(float)Math.floor(mMap.getCameraPosition().zoom+8)));
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
