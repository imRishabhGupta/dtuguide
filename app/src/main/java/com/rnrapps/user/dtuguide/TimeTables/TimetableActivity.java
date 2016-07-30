package com.rnrapps.user.dtuguide.TimeTables;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.rnrapps.user.dtuguide.LruBitmapCache;
import com.rnrapps.user.dtuguide.R;
import com.rnrapps.user.dtuguide.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

public class TimetableActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ArrayList<Timetable> mItems;
    private AutoCompleteTextView actv;
    private NetworkImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_timetable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        actv=(AutoCompleteTextView)findViewById(R.id.actv);
        iv=(NetworkImageView)findViewById(R.id.iv);
        final CardView cardView=(CardView)findViewById(R.id.timetable);
        final ImageButton download_timetable=(ImageButton)findViewById(R.id.download_timetable);


        mItems=new ArrayList<>();
        TimetableAdapter endangeredItemAdapter = new TimetableAdapter(getApplicationContext(), R.layout.list_item, mItems);
        actv.setAdapter(endangeredItemAdapter);


        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

                int image=mItems.get(position).getThumbnail();
                String url=getApplicationContext().getResources().getString(image);

                try {
                    ImageLoader.ImageCache imageCache = new LruBitmapCache();
                    ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(getApplicationContext()), imageCache);
                    iv.setImageUrl(url,imageLoader);
                    PhotoViewAttacher photoViewAttacher=new PhotoViewAttacher(iv);
                    cardView.setVisibility(View.VISIBLE);
                    download_timetable.setVisibility(View.VISIBLE);
                }
                catch (Exception e){
                    iv.setImageResource(R.drawable.nointernet);
                    e.printStackTrace();
                }
            }

        });

        download_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>= 23)
                    if (ContextCompat.checkSelfPermission(TimetableActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(TimetableActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},12);
                    }
                    else{
                        saveTimetable();
                    }
                else{
                    saveTimetable();
                }
            }
        });

    }
    public void saveTimetable(){
        String location="/sdcard/DtuApp/Timetables";
        File file=new File(location);
        if(!file.exists()) {
            File wallpaperDirectory = new File("/sdcard/DtuApp/Timetables/");
            wallpaperDirectory.mkdirs();
        }
        File file1 = new File(new File("/sdcard/DtuApp/Timetables/"), actv.getText().toString()+".jpg");
        try {
            FileOutputStream out = new FileOutputStream(file1);
            Bitmap bitmap=((BitmapDrawable)iv.getDrawable()).getBitmap();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            Toast.makeText(getApplicationContext(),"Timetable saved at "+location,Toast.LENGTH_LONG).show();
            out.flush();
            out.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext()," Error ocurred while downloading! ",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 12: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    saveTimetable();

                } else {

                }
                return;
            }
        }
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