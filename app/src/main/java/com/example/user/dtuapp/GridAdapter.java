package com.example.user.dtuapp;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by rohanpc on 10/19/2015.
 */
public class GridAdapter  extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    List<EndangeredItem> mItems;
    private static MyClickListener myClickListener;

    public GridAdapter() {
        super();
        mItems = new ArrayList<EndangeredItem>();
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
        EndangeredItem nature = mItems.get(i);
        viewHolder.tvspecies.setText(nature.getName());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
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