package com.rnrapps.user.dtuguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.rnrapps.user.dtuguide.DceSpeaksUp.Main2Activity;

/**
 * Created by rohanpc on 10/20/2015.
 */
public class Splashscreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView image = (ImageView) findViewById(R.id.dtulogo);
        RotateAnimation anim = new RotateAnimation(0.0f, 359.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(1600); //You can manage the time
        anim.setRepeatCount(Animation.INFINITE);
        anim.setRepeatMode(Animation.RESTART);
        image.startAnimation(anim);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){

                Intent i = new Intent(Splashscreen.this, Main2Activity.class);
                startActivity(i);

                finish();
            }

        },3000);
    }

}


