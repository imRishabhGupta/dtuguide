package com.example.user.dtuapp;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by rohanpc on 10/19/2015.
 */
public class GridAdapter  extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    ArrayList<Society> mItems;
    private static MyClickListener myClickListener;

    public GridAdapter(ArrayList<Society> items) {
        super();
        mItems=items;

        Society society=new Society();
        society.setTitle("Sahitya");
        society.setDescription(R.string.sahitya);
        society.setContactName("Vidushi tyagi (president)");
        society.setContactNumber("9871739158");
        mItems.add(society);

        society=new Society();
        society.setTitle("Madhurima");
        society.setDescription(R.string.madhurima);
        society.setContactName("Anurag Saika");
        society.setContactNumber("9899581945");
        mItems.add(society);

        society=new Society();
        society.setTitle("Pratibimb");
        society.setDescription(R.string.pratibimb);
        society.setContactName("Vineet Maurya");
        society.setContactNumber("9810383175");
        mItems.add(society);

        society=new Society();
        society.setTitle("NSS-DTU");
        society.setDescription(R.string.nssdtu);
        society.setContactName("Md. Khurshid Hussain");
        society.setContactNumber("9899897831");
        mItems.add(society);

        society=new Society();
        society.setTitle("UAS-DTU");
        society.setDescription(R.string.uasdtu);
        society.setContactName("uas.dce@gmail.com");
        society.setContactNumber(null);
        mItems.add(society);

        society=new Society();
        society.setTitle("Parchayi");
        society.setDescription(R.string.parchayi);
        society.setContactName("Ayush Goel");
        society.setContactNumber("9999882000");
        mItems.add(society);

        society=new Society();
        society.setTitle("SR-DTU");
        society.setDescription(R.string.srdtu);
        society.setContactName("Nakul Sharma");
        society.setContactNumber("9650356007");
        mItems.add(society);

        society=new Society();
        society.setTitle("IEEE DTU");
        society.setDescription(R.string.ieeedtu);
        society.setContactName("Chirag");
        society.setContactNumber("9910443068");
        mItems.add(society);

        society=new Society();
        society.setTitle("SITE");
        society.setDescription(R.string.site);
        society.setContactName("Snehal Toppo");
        society.setContactNumber("9718441404");
        mItems.add(society);

        society=new Society();
        society.setTitle("BLEED-X");
        society.setDescription(R.string.bleedx);
        society.setContactName("Pratyush");
        society.setContactNumber("9810114227");
        mItems.add(society);

        society=new Society();
        society.setTitle("Kalakriti");
        society.setDescription(R.string.kalakritidtu);
        society.setContactName("Nayan Lal");
        society.setContactNumber("9540669462");
        mItems.add(society);

        society=new Society();
        society.setTitle("Rotaract");
        society.setDescription(R.string.rotract);
        society.setContactName("Himanshu Yadav");
        society.setContactNumber("9971485952");
        mItems.add(society);

        society=new Society();
        society.setTitle("Quizzing Inc");
        society.setDescription(R.string.quizzinc);
        society.setContactName("Saket Saurav");
        society.setContactNumber("9958984709");
        mItems.add(society);

        society=new Society();
        society.setTitle("ASCE DTU");
        society.setDescription(R.string.asce);
        society.setContactName("Aporv");
        society.setContactNumber("9971542618");
        mItems.add(society);

        society=new Society();
        society.setTitle("Dance Society");
        society.setDescription(R.string.dance);
        society.setContactName("Sarthak");
        society.setContactNumber("9582500216");
        mItems.add(society);

        society=new Society();
        society.setTitle("Cognitive Minds");
        society.setDescription(R.string.cognitive);
        society.setContactName("Simrat Singh");
        society.setContactNumber("9990003470");
        mItems.add(society);

        society=new Society();
        society.setTitle("Solaris");
        society.setDescription(R.string.solaris);
        society.setContactName("solaris.dtu.ac.in");
        society.setContactNumber(null);
        mItems.add(society);

        society=new Society();
        society.setTitle("AUV");
        society.setDescription(R.string.auv);
        society.setContactName("auv.dce.edu");
        society.setContactNumber(null);
        mItems.add(society);

        society=new Society();
        society.setTitle("Team Defianz");
        society.setDescription(R.string.defianz);
        society.setContactName("Harsh");
        society.setContactNumber("8800910345");
        mItems.add(society);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Society nature = mItems.get(i);
        viewHolder.tvspecies.setText(nature.getTitle());

        //viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imgThumbnail;
        public TextView tvspecies;

        public ViewHolder(View itemView) {
            super(itemView);
            //imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvspecies = (TextView)itemView.findViewById(R.id.tv_species);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }


    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}