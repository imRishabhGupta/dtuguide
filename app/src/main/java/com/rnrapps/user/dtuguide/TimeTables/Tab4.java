//package com.rnrapps.user.dtuguide.TimeTables;
//
//import android.graphics.Bitmap;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.CardView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AutoCompleteTextView;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.rnrapps.user.dtuguide.R;
//import com.squareup.picasso.Picasso;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//
//import uk.co.senab.photoview.PhotoViewAttacher;
//
///**
// * Created by user on 31-01-2016.
// */
//public class Tab4 extends Fragment {
//
//    private CustomAdapter endangeredItemAdapter;
//    ArrayList<Timetable> mItems;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
//
//        View v =inflater.inflate(R.layout.tab_5,container,false);
//
//        final AutoCompleteTextView actv=(AutoCompleteTextView)v.findViewById(R.id.actv);
//        final ImageView iv=(ImageView)v.findViewById(R.id.iv);
//        final CardView cardView=(CardView)v.findViewById(R.id.timetable);
//        final ImageButton download_timetable=(ImageButton)v.findViewById(R.id.download_timetable);
//
//
//        mItems=new ArrayList<>();
//        endangeredItemAdapter=new CustomAdapter(getContext(),R.layout.list_item,mItems);
//        actv.setAdapter(endangeredItemAdapter);
//
//
//        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
//
//                int image=mItems.get(position).getThumbnail();
//                String url=getContext().getResources().getString(image).toString();
//
//                try {
//                    Picasso.with(getContext()).load(url).into(iv);
//                    //bitmap= BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
//                     PhotoViewAttacher photoViewAttacher=new PhotoViewAttacher(iv);
//                    cardView.setVisibility(View.VISIBLE);
//                    download_timetable.setVisibility(View.VISIBLE);
//                }
//                catch (Exception e){
//                    iv.setImageResource(R.drawable.nointernet);
//                    e.printStackTrace();
//                }
//            }
//
//        });
//
//        download_timetable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String location="/sdcard/DtuApp/Timetables";
//                File file=new File(location);
//                if(!file.exists()) {
//                    File wallpaperDirectory = new File("/sdcard/DtuApp/Timetables/");
//                    wallpaperDirectory.mkdirs();
//                }
//                File file1 = new File(new File("/sdcard/DtuApp/Timetables/"), actv.getText().toString()+".jpg");
//                try {
//                    FileOutputStream out = new FileOutputStream(file1);
//                    Bitmap bitmap=((BitmapDrawable)iv.getDrawable()).getBitmap();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
//                    Toast.makeText(getContext(),"Timetable saved at "+location,Toast.LENGTH_LONG).show();
//                    out.flush();
//                    out.close();
//
//                } catch (Exception e) {
//                    Toast.makeText(getContext()," Error ocurred while downloading! ",Toast.LENGTH_LONG).show();
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//        return v;
//
//    }
//
//}
