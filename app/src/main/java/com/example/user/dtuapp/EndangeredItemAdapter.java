package com.example.user.dtuapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        species.setName("Amur Leopard");
        species.setThumbnail(R.drawable.leopard);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("Black Rhino");
        species.setThumbnail(R.drawable.rhino);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("Orangutan");
        species.setThumbnail(R.drawable.orangutan);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("Sea Lions");
        species.setThumbnail(R.drawable.seali);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("Indian Elephant");
        species.setThumbnail(R.drawable.elephant);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("Giant Panda");
        species.setThumbnail(R.drawable.panda);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("Snow Leopard");
        species.setThumbnail(R.drawable.snow);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("Dolphin");
        species.setThumbnail(R.drawable.dolphin);
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

        return convertView;
    }

    @Override
    public int getCount() {
        return 8;

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
