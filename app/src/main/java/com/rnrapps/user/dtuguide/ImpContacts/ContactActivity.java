package com.rnrapps.user.dtuguide.ImpContacts;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.rnrapps.user.dtuguide.R;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView description = (TextView) findViewById(R.id.description);
        TextView contactName = (TextView) findViewById(R.id.contact_name);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        TextView conatctNumber = (TextView) findViewById(R.id.contact_number);
        ImageButton imageButton = (ImageButton) findViewById(R.id.call);
        final String str1=getIntent().getExtras().getString("contactName");
        final String str2=getIntent().getExtras().getString("contactNumber");
        if(str1==null){
            conatctNumber.setVisibility(View.GONE);
            contactName.setVisibility(View.GONE);
            imageButton.setVisibility(View.GONE);
            TextView contact=(TextView)findViewById(R.id.contact);
            contact.setVisibility(View.GONE);
            description.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        }
        getSupportActionBar().setTitle(getIntent().getExtras().getString("title"));

        description.setText(getIntent().getExtras().getString("description"));
        contactName.setText(str1);
        imageView.setImageResource(getIntent().getExtras().getInt("image"));
        //toolbar.setLogo(getIntent().getExtras().getInt("image"));
        if(str2!=null){
            conatctNumber.setText(str2);
            imageButton.setImageResource(R.drawable.call_button);
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(str2!=null){
                    try {
                        int permission = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE);
                        if (permission != PackageManager.PERMISSION_GRANTED) {
                            // We don't have permission so prompt the user
                            ActivityCompat.requestPermissions(
                                    ContactActivity.this,
                                    new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.CALL_PRIVILEGED},
                                    REQUEST_CALL);
                        }


                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+str2));
                        startActivity(callIntent);
                    } catch (SecurityException e) {
                        Log.e("myphone dialer", "Call failed", e);
                    }
                }
                else  {
                    try {
                        String url=str1;
                        if (!str1.startsWith("http://") && !str1.startsWith("https://"))
                            url = "http://" + str1;
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }

}