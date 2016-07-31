package com.rnrapps.user.dtuguide.ImpContacts;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rnrapps.user.dtuguide.*;

import java.util.ArrayList;
/**
 * Created by rohanpc on 10/19/2015.
 */
public class GridAdapter  extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    ArrayList<Society> mItems;
    private static MyClickListener myClickListener;
    private Context mContext;


    public GridAdapter(ArrayList<Society> items, Context context) {
        super();
        mItems=items;
        mContext=context;

        Society society=new Society();
        society.setTitle("DTU-SA");
        society.setDescription(R.string.student_association);
        society.setImage(R.mipmap.dtulogo);
        mItems.add(society);

        society=new Society();
        society.setTitle("Cultural Council");
        society.setDescription(R.string.cultural_council);
        society.setImage(R.drawable.cultural_council);
        mItems.add(society);

        society=new Society();
        society.setTitle("Sahitya");
        society.setDescription(R.string.sahitya);
        society.setImage(R.drawable.sahitya);
        society.setContactName("Vidushi tyagi (president)");
        society.setContactNumber("9871739158");
        mItems.add(society);

        society=new Society();
        society.setTitle("Madhurima");
        society.setImage(R.drawable.madhurima);
        society.setDescription(R.string.madhurima);
        society.setContactName("Anurag Saika");
        society.setContactNumber("9899581945");
        mItems.add(society);

        society=new Society();
        society.setTitle("Pratibimb");
        society.setImage(R.drawable.pratibimb);
        society.setDescription(R.string.pratibimb);
        society.setContactName("Vineet Maurya");
        society.setContactNumber("9810383175");
        mItems.add(society);

        society=new Society();
        society.setTitle("NSS-DTU");
        society.setImage(R.drawable.nss);
        society.setDescription(R.string.nssdtu);
        society.setContactName("Md. Khurshid Hussain");
        society.setContactNumber("9899897831");
        mItems.add(society);

        society=new Society();
        society.setTitle("UAS-DTU");
        society.setImage(R.drawable.uas);
        society.setDescription(R.string.uasdtu);
        society.setContactName("uas.dce@gmail.com");
        society.setContactNumber(null);
        mItems.add(society);

        society=new Society();
        society.setTitle("Parchhayi");
        society.setImage(R.drawable.parchayi);
        society.setDescription(R.string.parchayi);
        society.setContactName("Ayush Goel");
        society.setContactNumber("9999882000");
        mItems.add(society);

        society=new Society();
        society.setTitle("SR-DTU");
        society.setImage(R.drawable.srdtu);
        society.setDescription(R.string.srdtu);
        society.setContactName("Nakul Sharma");
        society.setContactNumber("9650356007");
        mItems.add(society);

        society=new Society();
        society.setTitle("IEEE DTU");
        society.setImage(R.drawable.ieee);
        society.setDescription(R.string.ieeedtu);
        society.setContactName("Chirag");
        society.setContactNumber("9910443068");
        mItems.add(society);

        society=new Society();
        society.setTitle("SITE");
        society.setImage(R.drawable.site);
        society.setDescription(R.string.site);
        society.setContactName("Snehal Toppo");
        society.setContactNumber("9718441404");
        mItems.add(society);

        society=new Society();
        society.setTitle("Kalakriti");
        society.setImage(R.drawable.kalakriti);
        society.setDescription(R.string.kalakritidtu);
        society.setContactName("Nayan Lal");
        society.setContactNumber("9540669462");
        mItems.add(society);

        society=new Society();
        society.setTitle("Rotaract");
        society.setImage(R.drawable.rotaract);
        society.setDescription(R.string.rotract);
        society.setContactName("Himanshu Yadav");
        society.setContactNumber("9971485952");
        mItems.add(society);

        society=new Society();
        society.setTitle("Quizzing Inc");
        society.setImage(R.drawable.quizzinc);
        society.setDescription(R.string.quizzinc);
        society.setContactName("Saket Saurav");
        society.setContactNumber("9958984709");
        mItems.add(society);

        society=new Society();
        society.setTitle("ASCE DTU");
        society.setImage(R.drawable.asce);
        society.setDescription(R.string.asce);
        society.setContactName("Aporv");
        society.setContactNumber("9971542618");
        mItems.add(society);

        society=new Society();
        society.setTitle("Dance Society");
        society.setImage(R.drawable.dance);
        society.setDescription(R.string.dance);
        society.setContactName("Sarthak");
        society.setContactNumber("9582500216");
        mItems.add(society);

        society=new Society();
        society.setTitle("Cognitive Minds");
        society.setImage(R.drawable.cognitive);
        society.setDescription(R.string.cognitive);
        society.setContactName("Simrat Singh");
        society.setContactNumber("9990003470");
        mItems.add(society);

        society=new Society();
        society.setTitle("Solaris");
        society.setImage(R.drawable.solaris);
        society.setDescription(R.string.solaris);
        society.setContactName("solaris.dtu.ac.in");
        society.setContactNumber(null);
        mItems.add(society);

        society=new Society();
        society.setTitle("AUV");
        society.setImage(R.drawable.auv);
        society.setDescription(R.string.auv);
        society.setContactName("auv.dce.edu");
        society.setContactNumber(null);
        mItems.add(society);

        society=new Society();
        society.setTitle("Team Defianz");
        society.setImage(R.drawable.defianz);
        society.setDescription(R.string.defianz);
        society.setContactName("Harsh");
        society.setContactNumber("8800910345");
        mItems.add(society);

        society=new Society();
        society.setTitle("IGTS");
        society.setImage(R.mipmap.dtulogo);
        society.setDescription(R.string.igts);
        society.setContactName("Divyanshu Goel");
        society.setContactNumber("9899101508");
        mItems.add(society);

        society=new Society();
        society.setTitle("Global Youth India DTU Chapter");
        society.setImage(R.drawable.golbalyouth);
        society.setDescription(R.string.globalyouth);
        society.setContactName("Taresh Jerath ");
        society.setContactNumber("9013394755");
        mItems.add(society);

        society=new Society();
        society.setTitle("Supermileage");
        society.setImage(R.mipmap.dtulogo);
        society.setDescription(R.string.supermileage);
        society.setContactName("Akash");//www.dtusmv.com
        society.setContactNumber("9716715130");
        mItems.add(society);

        society=new Society();
        society.setTitle("DelTech MUN Society");
        society.setImage(R.drawable.dtmun);
        society.setDescription(R.string.deltechmun);
        society.setContactName("Manas Sahni");
        society.setContactNumber("9582584875");
        mItems.add(society);

        society=new Society();
        society.setTitle("Delhi-42");
        society.setImage(R.drawable.delhi42);
        society.setDescription(R.string.delhi42);
        society.setContactName("Shreyam");
        society.setContactNumber(" 9560600595");
        mItems.add(society);

        society=new Society();
        society.setTitle("SD DTU");
        society.setImage(R.drawable.sddtulogo);
        society.setDescription(R.string.sddtu);
        society.setContactName("Krishna Rai");
        society.setContactNumber("9717430033");
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

        Glide
                .with(mContext)
                .load(nature.getImage())
                .into(viewHolder.imgThumbnail);

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
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
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