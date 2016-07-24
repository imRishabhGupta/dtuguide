package com.rnrapps.user.dtuguide;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

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

public class CampusMap extends AppCompatActivity {

    private AutoCompleteTextView actv;
    private List<MyItem> items = new ArrayList<>();
    private String m;
    private ArrayList<getmarkerfromstring> users=new ArrayList<>();
    private String[] countries=new String[576];
    private GoogleMap mMap;
    private GoogleApiClient
            mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addApi(LocationServices.API)
                .build();
        actv=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        setUpMapIfNeeded();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
//             Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void initCamera(Location location) {
        CameraPosition position = CameraPosition.builder()
                .target(new LatLng(28.75007207311156, 77.11772996932268))
                .zoom(18f)
                .bearing(0.0f)
                .tilt(40f)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setTrafficEnabled(true);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        Location mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        initCamera(mCurrentLocation);
        ClusterManager<MyItem> mClusterManager = new ClusterManager<>(getApplicationContext(), mMap);
        mMap.setOnCameraChangeListener(mClusterManager);
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
//                    Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();


//                        LatLng lng=new LatLng(Double.parseDouble(moviemap.get("E")),Double.parseDouble(moviemap.get("F")));
                LatLng lng=new LatLng(users.get(pos).getLat(),users.get(pos).getLng());
//                        Toast.makeText(getActivity(),String.valueOf(lng),Toast.LENGTH_LONG).show();

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lng,(float)Math.floor(mMap.getCameraPosition().zoom+8)));




            }


//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                                cluster.getPosition(), (float) Math.floor(mMap
//                                        .getCameraPosition().zoom + 2)), 300,
//                        null);



        });
    }
}
