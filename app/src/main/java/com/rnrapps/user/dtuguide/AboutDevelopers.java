package com.rnrapps.user.dtuguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AboutDevelopers extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.imageButton3){
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://twitter.com/rohanagarwal94"));
            startActivity(i);
        }
        else if(v.getId()==R.id.imageButton4){
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://in.linkedin.com/in/rohan-agarwal-b1685b116"));
            startActivity(i);
        }
        else if(v.getId()==R.id.imageButton){
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://twitter.com/imRishabhGupta"));
            startActivity(i);
        }
        else if(v.getId()==R.id.imageButton2){
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://in.linkedin.com/in/rishabh-gupta-8b9626a0"));
            startActivity(i);
        }
        else if(v.getId()==R.id.imageButton5){
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://github.com/rohanagarwal94"));
            startActivity(i);
        }
        else if(v.getId()==R.id.imageButton6){
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://github.com/imRishabhGupta"));
            startActivity(i);
        }
    }
}