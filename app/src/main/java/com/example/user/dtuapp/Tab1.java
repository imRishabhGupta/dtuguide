package com.example.user.dtuapp;

import android.graphics.BitmapFactory;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rohanpc on 8/1/16.
 */
public class Tab1 extends Fragment {
    InputStream inputstream;
    BufferedReader reader;
    String title;
    MapView mapView;
    private Location mCurrentLocation;
    AutoCompleteTextView actv;
    Map<String, String> moviemap;
    List<MyItem> items = new ArrayList<MyItem>();
    String subtitle;
    String floor;
    String type;
    Double lat;
    Double lng;
    ArrayList<getmarkerfromstring> users=new ArrayList<getmarkerfromstring>();
    String[] countries=new String[576];
    int len;
    int arraylength;
    ClusterManager<MyItem> mClusterManager;
    List<Map<String, String>> moviedata;
    JSONArray jsonArray;
    JSONObject jsonObject;
    String m;
    private GoogleMap mMap;
    private GoogleApiClient
            mGoogleApiClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .build();
                View v=inflater.inflate(R.layout.tab_1, container, false);
        actv=(AutoCompleteTextView)v.findViewById(R.id.autoCompleteTextView);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpMapIfNeeded();

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
            mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
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
        mCurrentLocation =
                LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        initCamera(mCurrentLocation);
        mClusterManager = new ClusterManager<MyItem>(getActivity(), mMap);
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
            inputstream = getResources().getAssets().open("map.json");
            reader = new BufferedReader(new InputStreamReader(inputstream));
            m = reader.toString();
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                total.append(line);
            }
            m = total.toString();
        } catch (IOException ex) {
            Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
        }


//        moviedata = new ArrayList<Map<String, String>>();
        try {
            jsonObject = new JSONObject(m);
            jsonArray = jsonObject.optJSONArray("map");
            arraylength = jsonArray.length();
            len = arraylength;
//            Toast.makeText(getActivity(),String.valueOf(len),Toast.LENGTH_LONG).show();

            for (int i = 0; i < arraylength; i++) {

                JSONObject jsonChildNode = jsonArray.getJSONObject(i);
                title = jsonChildNode.optString("title");
                subtitle = jsonChildNode.optString("subtitle");
                floor = jsonChildNode.optString("floor");
                type = jsonChildNode.optString("type");
                lat = jsonChildNode.optDouble("latitude");
                lng = jsonChildNode.optDouble("longitude");
                double lat1=lat;
                double lng1=lng;
                items.add(new MyItem(lat1,lng1,title,subtitle,floor));
                countries[2*i]=title;
                countries[2*i+1]=subtitle;
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
            mClusterManager.setRenderer(new MyClusterRenderer(getActivity(), mMap, mClusterManager));
            mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
                @Override
                public boolean onClusterItemClick(MyItem myItem) {
                    Toast.makeText(getActivity(), myItem.getfloor() + " floor",Toast.LENGTH_SHORT).show();
                    return false;
                }
            });






        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getActivity(),
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