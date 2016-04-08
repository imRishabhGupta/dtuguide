package com.example.user.dtuapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 31-01-2016.
 */
public class Tab4 extends Fragment {

    private  EndangeredItemAdapter endangeredItemAdapter;
    ArrayList<EndangeredItem> mItems;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View v =inflater.inflate(R.layout.tab_4,container,false);

        mItems=new ArrayList<>();
        endangeredItemAdapter=new EndangeredItemAdapter(getContext(),mItems);

        final ListView listView=(ListView)v.findViewById(R.id.list_view);
        listView.setAdapter(endangeredItemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                endangeredItemAdapter.db.update(endangeredItemAdapter.mItems.get(position).getThumbnail());

                final TimeTableDialog dialog = new TimeTableDialog(getActivity(),endangeredItemAdapter.mItems.get(position).getThumbnail(),endangeredItemAdapter.mItems.get(position).getName());
                //dialog.setTitle(endangeredItemAdapter.mItems.get(position).getName());
                //ImageView iv = (ImageView) dialog.findViewById(R.id.ttiv);
                //iv.setImageResource(endangeredItemAdapter.mItems.get(position).getThumbnail());
                //Button button=(Button)dialog.findViewById(R.id.ttb);
                //button.setOnClickListener(new View.OnClickListener() {
                //    @Override
                //    public void onClick(View v) {
                //        dialog.dismiss();
                //    }
                //});
                dialog.show();
                Window window = dialog.getWindow();
                //window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            }
        });

        return v;

    }

}
