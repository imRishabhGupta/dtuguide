package com.example.user.dtuapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 31-01-2016.
 */
public class EndangeredItemAdapter extends BaseAdapter {

    List<EndangeredItem> mItems;
    Context mContext;
    Counter db;
    public static final int TYPE_REGULAR = 0;
    public static final int TYPE_MAIN = 1;

    public EndangeredItemAdapter(Context context,ArrayList<EndangeredItem> arrayList) {
        super();

        mContext=context;
        mItems = arrayList;

        EndangeredItem species = new EndangeredItem();
        species.setName("1st year Group A1");
        species.setThumbnail(R.drawable.a1);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group A2");
        species.setThumbnail(R.drawable.a2);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group A3");
        species.setThumbnail(R.drawable.a3);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group A4");
        species.setThumbnail(R.drawable.a4);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group A5");
        species.setThumbnail(R.drawable.a5);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group A6");
        species.setThumbnail(R.drawable.a6);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group A7");
        species.setThumbnail(R.drawable.a7);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group A8");
        species.setThumbnail(R.drawable.a8);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group A9");
        species.setThumbnail(R.drawable.a9);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group A10");
        species.setThumbnail(R.drawable.a10);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group B1");
        species.setThumbnail(R.drawable.b1);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group B2");
        species.setThumbnail(R.drawable.b2);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group B3");
        species.setThumbnail(R.drawable.b3);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group B4");
        species.setThumbnail(R.drawable.b4);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group B5");
        species.setThumbnail(R.drawable.b5);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group B6");
        species.setThumbnail(R.drawable.b6);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group B7");
        species.setThumbnail(R.drawable.b7);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group B8");
        species.setThumbnail(R.drawable.b8);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group B9");
        species.setThumbnail(R.drawable.b9);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("1st year Group B10");
        species.setThumbnail(R.drawable.b10);
        mItems.add(species);


        db=new Counter(mContext);
        if(db.isEmpty()){
            for (EndangeredItem item : mItems) {
                db.add(item.getThumbnail(),item.getName());
            }
        }
        else{
            arrayList=db.getAllDetails();

            mItems=arrayList;

        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EndangeredItem endangeredItem = mItems.get(position);


            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(position==0)
            convertView = inflater.inflate(R.layout.list_item_main, null);
        else
            convertView = inflater.inflate(R.layout.list_item, null);

        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv);

        tv.setText(endangeredItem.getName());
        iv.setImageResource(endangeredItem.getThumbnail());
        iv.setImageBitmap(
                decodeSampledBitmapFromResource(mContext.getResources(), endangeredItem.getThumbnail(), 100, 100));

        return convertView;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
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
    @Override
    public int getCount() {
        return 20;

    }
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    public void show(int position){
       db.update(mItems.get(position).getThumbnail());
        Toast.makeText(mContext, "item clicked" + String.valueOf(db.getDetail(mItems.get(position).getThumbnail())),
                Toast.LENGTH_SHORT).show();
    }


}
