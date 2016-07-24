//package com.rnrapps.user.dtuguide.TimeTables;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//import android.widget.Filter;
//
//import com.rnrapps.user.dtuguide.R;
//
//import java.util.ArrayList;
//
///**
// * Created by user on 13-04-2016.
// */
//public class CustomAdapter extends ArrayAdapter<Timetable> {
//    private final String MY_DEBUG_TAG = "CustomerAdapter";
//    private ArrayList<Timetable> mItems;
//    private ArrayList<Timetable> itemsAll;
//    private ArrayList<Timetable> suggestions;
//    private int viewResourceId;
//    private Context context;
//
//    public CustomAdapter(Context context, int viewResourceId, ArrayList<Timetable> items) {
//        super(context, viewResourceId, items);
//
//        this.mItems = items;
//        this.context=context;
//
//        Timetable species = new Timetable();
//        species.setName("2nd Sem Group A1");
//        species.setThumbnail(R.string.a1);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group A2");
//        species.setThumbnail(R.string.a2);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group A3");
//        species.setThumbnail(R.string.a3);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group A4");
//        species.setThumbnail(R.string.a4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group A5");
//        species.setThumbnail(R.string.a5);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group A6");
//        species.setThumbnail(R.string.a6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group A7");
//        species.setThumbnail(R.string.a7);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group A8");
//        species.setThumbnail(R.string.a8);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group A9");
//        species.setThumbnail(R.string.a9);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group A10");
//        species.setThumbnail(R.string.a10);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group B1");
//        species.setThumbnail(R.string.b1);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group B2");
//        species.setThumbnail(R.string.b2);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group B3");
//        species.setThumbnail(R.string.b3);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group B4");
//        species.setThumbnail(R.string.b4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group B5");
//        species.setThumbnail(R.string.b5);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group B6");
//        species.setThumbnail(R.string.b6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group B7");
//        species.setThumbnail(R.string.b7);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group B8");
//        species.setThumbnail(R.string.b8);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group B9");
//        species.setThumbnail(R.string.b9);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("2nd Sem Group B10");
//        species.setThumbnail(R.string.b10);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem COE Sec-A");
//        species.setThumbnail(R.string.coe_a4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem COE Sec-B");
//        species.setThumbnail(R.string.coe_b4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem IT Sec-A");
//        species.setThumbnail(R.string.it_a4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem IT Sec-B");
//        species.setThumbnail(R.string.it_b4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem SE Sec-A");
//        species.setThumbnail(R.string.se_a4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem SE Sec-B");
//        species.setThumbnail(R.string.se_b4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem ECE Sec-C");
//        species.setThumbnail(R.string.ece_c4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem ECE Sec-D");
//        species.setThumbnail(R.string.ece_d4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem ECE Sec-E");
//        species.setThumbnail(R.string.ece_e4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem EEE Sec-1");
//        species.setThumbnail(R.string.eee14);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem EEE Sec-2");
//        species.setThumbnail(R.string.eee24);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem MECH Sec-LMN");
//        species.setThumbnail(R.string.mechlmn4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem MECH Sec-OPQ");
//        species.setThumbnail(R.string.mechopq4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem CIVIL Sec-A");
//        species.setThumbnail(R.string.civil_a4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem CIVIL Sec-B");
//        species.setThumbnail(R.string.civil_b4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem EE Sec-1");
//        species.setThumbnail(R.string.ee14);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem EE Sec-2");
//        species.setThumbnail(R.string.ee24);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem PSCT");
//        species.setThumbnail(R.string.psct4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem EP");
//        species.setThumbnail(R.string.ep4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem PRODUCTION");
//        species.setThumbnail(R.string.prod4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem EN");
//        species.setThumbnail(R.string.en4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("4th Sem BIOTECH");
//        species.setThumbnail(R.string.bt4);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem COE Sec-A");
//        species.setThumbnail(R.string.coe_a6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem COE Sec-B");
//        species.setThumbnail(R.string.coe_b6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem IT Sec-A");
//        species.setThumbnail(R.string.it_a6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem ECE Sec-C");
//        species.setThumbnail(R.string.ece_c6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem ECE Sec-D");
//        species.setThumbnail(R.string.ece_d6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem ECE Sec-E");
//        species.setThumbnail(R.string.ece_e6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem MECH Sec-IJK");
//        species.setThumbnail(R.string.mechijk6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem MECH Sec-LMN");
//        species.setThumbnail(R.string.mechlmn6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem MECH Sec-OPQ");
//        species.setThumbnail(R.string.mechopq6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem CIVIL Sec-A");
//        species.setThumbnail(R.string.civil_a6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem CIVIL Sec-B");
//        species.setThumbnail(R.string.civil_b6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem MCE Sec-A");
//        species.setThumbnail(R.string.mce_a6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem PSCT");
//        species.setThumbnail(R.string.psct6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem EP");
//        species.setThumbnail(R.string.ep6);
//        mItems.add(species);
//
//        species = new Timetable();
//        species.setName("6th Sem PRODUCTION");
//        species.setThumbnail(R.string.prod6);
//        mItems.add(species);
//
//
//
//        this.itemsAll = (ArrayList<Timetable>) items.clone();
//        this.suggestions = new ArrayList<Timetable>();
//        this.viewResourceId = viewResourceId;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View v = convertView;
//        if (v == null) {
//            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            v = vi.inflate(viewResourceId, null);
//        }
//        Timetable timetable = mItems.get(position);
//        if (timetable != null) {
//            TextView textView = (TextView) v.findViewById(R.id.tv);
//            if (textView!= null) {
////              Log.i(MY_DEBUG_TAG, "getView Customer Name:"+customer.getName());
//                textView.setText(timetable.getName());
//            }
//        }
//        return v;
//    }
//
//    @Override
//    public Filter getFilter() {
//        return nameFilter;
//    }
//
//    Filter nameFilter = new Filter() {
//        @Override
//        public String convertResultToString(Object resultValue) {
//            String str = ((Timetable)(resultValue)).getName();
//            return str;
//        }
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            if(constraint != null) {
//                suggestions.clear();
//                for (Timetable timetable : itemsAll) {
//                    if(timetable.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())){
//                        suggestions.add(timetable);
//                    }
//                }
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = suggestions;
//                filterResults.count = suggestions.size();
//                return filterResults;
//            } else {
//                return new FilterResults();
//            }
//        }
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            ArrayList<Timetable> filteredList = (ArrayList<Timetable>) results.values;
//            if(results != null && results.count > 0) {
//                clear();
//                for (Timetable c : filteredList) {
//                    add(c);
//                }
//                notifyDataSetChanged();
//            }
//        }
//    };
//
//}