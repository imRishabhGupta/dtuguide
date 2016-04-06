package com.example.user.dtuapp;

/**
 * Created by rohanpc on 2/3/2016.
 */


        import android.content.Context;
        import android.widget.Toast;

        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.maps.android.clustering.Cluster;
        import com.google.maps.android.clustering.ClusterItem;
        import com.google.maps.android.clustering.ClusterManager;
        import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class MyItem implements ClusterItem {
    private final LatLng mPosition;
    private  String title;
    private String subtitle;
    private String floor;

    public MyItem(double lat, double lng,String stitle,String ssubtitle,String sfloor ) {

        mPosition = new LatLng(lat, lng);
        title=stitle;
        subtitle=ssubtitle;
        floor=sfloor;



    }

    public String getTitle() {
        return title;
    }



    public String getsubtitle() {
        return subtitle;
    }

    public String getfloor() {
        return floor;
    }




    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}

class MyClusterRenderer extends DefaultClusterRenderer<MyItem> {

    public MyClusterRenderer(Context context, GoogleMap map,
                             ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);

        markerOptions.title(item.getTitle());
        markerOptions.snippet(item.getsubtitle());


//        Toast.makeText(this,item.getfloor()+" floor",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onClusterItemRendered(final MyItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);


        //here you have access to the marker itself
    }

}