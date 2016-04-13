package com.example.user.dtuapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by user on 07-04-2016.
 */
public class TimeTableDialog extends Dialog {

    public Activity c;
    public Dialog d;
    public ImageButton ok;
    public int image;
    public String text;


    public TimeTableDialog(Activity a,int i, String s) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        image=i;
        text=s;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.time_table_dialog);
        ZoomableImageView iv = (ZoomableImageView) findViewById(R.id.ttiv);

            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                String url=getContext().getResources().getString(image).toString();
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent(),null,options);
                /*int displayWidth=iv.getWidth();
                int width = options.outWidth;
                if (width > displayWidth) {
                    int widthRatio = Math.round((float) width / (float) displayWidth);
                    options.inSampleSize = widthRatio;
                }
                options.inJustDecodeBounds = false;
                Bitmap scaledBitmap =  BitmapFactory.decodeStream((InputStream) new URL(getContext().getResources().getString(image).toString()).getContent(),null,options);
                */
                //iv.setImageBitmap(decodeSampledBitmapFromResource(getContext().getResources(),url,1600,1200));
                //iv.setImageBitmap(scaledBitmap);
                iv.setImageBitmap(Bitmap.createScaledBitmap(bitmap,1000,1000,false));

            }  catch (Exception e) {
                e.printStackTrace();
            }

        ok = (ImageButton) findViewById(R.id.ttb);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, String url,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
             BitmapFactory.decodeStream((InputStream) new URL(url).getContent(), null, options);
        }catch (Exception e){
            e.printStackTrace();
        }

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bitmap=null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent(), null, options);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
