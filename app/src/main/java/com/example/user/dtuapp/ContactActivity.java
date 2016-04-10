package com.example.user.dtuapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private TextView contactName;
    private TextView conatctNumber;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide(Gravity.BOTTOM);
            slide.addTarget(R.id.ll);
            slide.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in));
            slide.setDuration(3000);
            getWindow().setEnterTransition(slide);
        }

        title=(TextView)findViewById(R.id.title11);
        description=(TextView)findViewById(R.id.description);
        contactName=(TextView)findViewById(R.id.contact_name);
        conatctNumber=(TextView)findViewById(R.id.contact_number);
        imageButton=(ImageButton)findViewById(R.id.call);
        final String str1=getIntent().getExtras().getString("contactName");
        final String str2=getIntent().getExtras().getString("contactNumber");

        title.setText(getIntent().getExtras().getString("title"));
        description.setText(getIntent().getExtras().getString("description"));
        contactName.setText(str1);
        if(str2!=null){
            conatctNumber.setText(str2);
            imageButton.setImageResource(R.drawable.call_button);
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str2!=null){
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + str2));
                    startActivity(intent);
                }
                else {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(str1));
                    startActivity(i);
                }
            }
        });

    }

}
