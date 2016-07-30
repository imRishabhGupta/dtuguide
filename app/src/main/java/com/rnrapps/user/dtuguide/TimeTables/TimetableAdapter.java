package com.rnrapps.user.dtuguide.TimeTables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Filter;

import com.rnrapps.user.dtuguide.R;

import java.util.ArrayList;

/**
 * Created by user on 13-04-2016.
 */
public class TimetableAdapter extends ArrayAdapter<Timetable> {
    private ArrayList<Timetable> mItems;
    private ArrayList<Timetable> itemsAll;
    private ArrayList<Timetable> suggestions;
    private int viewResourceId;
    private Context context;

    public TimetableAdapter(Context context, int viewResourceId, ArrayList<Timetable> items) {
        super(context, viewResourceId, items);

        this.mItems = items;
        this.context=context;

        Timetable timetables = new Timetable();
        timetables.setName("2nd Sem Group A1");
        timetables.setThumbnail(R.string.a1);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group A2");
        timetables.setThumbnail(R.string.a2);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group A3");
        timetables.setThumbnail(R.string.a3);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group A4");
        timetables.setThumbnail(R.string.a4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group A5");
        timetables.setThumbnail(R.string.a5);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group A6");
        timetables.setThumbnail(R.string.a6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group A7");
        timetables.setThumbnail(R.string.a7);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group A8");
        timetables.setThumbnail(R.string.a8);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group A9");
        timetables.setThumbnail(R.string.a9);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group A10");
        timetables.setThumbnail(R.string.a10);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group B1");
        timetables.setThumbnail(R.string.b1);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group B2");
        timetables.setThumbnail(R.string.b2);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group B3");
        timetables.setThumbnail(R.string.b3);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group B4");
        timetables.setThumbnail(R.string.b4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group B5");
        timetables.setThumbnail(R.string.b5);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group B6");
        timetables.setThumbnail(R.string.b6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group B7");
        timetables.setThumbnail(R.string.b7);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group B8");
        timetables.setThumbnail(R.string.b8);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group B9");
        timetables.setThumbnail(R.string.b9);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("2nd Sem Group B10");
        timetables.setThumbnail(R.string.b10);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem COE Sec-A");
        timetables.setThumbnail(R.string.coe_a4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem COE Sec-B");
        timetables.setThumbnail(R.string.coe_b4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem IT Sec-A");
        timetables.setThumbnail(R.string.it_a4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem IT Sec-B");
        timetables.setThumbnail(R.string.it_b4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem SE Sec-A");
        timetables.setThumbnail(R.string.se_a4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem SE Sec-B");
        timetables.setThumbnail(R.string.se_b4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem ECE Sec-C");
        timetables.setThumbnail(R.string.ece_c4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem ECE Sec-D");
        timetables.setThumbnail(R.string.ece_d4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem ECE Sec-E");
        timetables.setThumbnail(R.string.ece_e4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem EEE Sec-1");
        timetables.setThumbnail(R.string.eee14);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem EEE Sec-2");
        timetables.setThumbnail(R.string.eee24);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem MECH Sec-LMN");
        timetables.setThumbnail(R.string.mechlmn4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem MECH Sec-OPQ");
        timetables.setThumbnail(R.string.mechopq4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem CIVIL Sec-A");
        timetables.setThumbnail(R.string.civil_a4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem CIVIL Sec-B");
        timetables.setThumbnail(R.string.civil_b4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem EE Sec-1");
        timetables.setThumbnail(R.string.ee14);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem EE Sec-2");
        timetables.setThumbnail(R.string.ee24);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem PSCT");
        timetables.setThumbnail(R.string.psct4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem EP");
        timetables.setThumbnail(R.string.ep4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem PRODUCTION");
        timetables.setThumbnail(R.string.prod4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem EN");
        timetables.setThumbnail(R.string.en4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("4th Sem BIOTECH");
        timetables.setThumbnail(R.string.bt4);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem COE Sec-A");
        timetables.setThumbnail(R.string.coe_a6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem COE Sec-B");
        timetables.setThumbnail(R.string.coe_b6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem IT Sec-A");
        timetables.setThumbnail(R.string.it_a6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem ECE Sec-C");
        timetables.setThumbnail(R.string.ece_c6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem ECE Sec-D");
        timetables.setThumbnail(R.string.ece_d6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem ECE Sec-E");
        timetables.setThumbnail(R.string.ece_e6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem MECH Sec-IJK");
        timetables.setThumbnail(R.string.mechijk6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem MECH Sec-LMN");
        timetables.setThumbnail(R.string.mechlmn6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem MECH Sec-OPQ");
        timetables.setThumbnail(R.string.mechopq6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem CIVIL Sec-A");
        timetables.setThumbnail(R.string.civil_a6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem CIVIL Sec-B");
        timetables.setThumbnail(R.string.civil_b6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem MCE Sec-A");
        timetables.setThumbnail(R.string.mce_a6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem PSCT");
        timetables.setThumbnail(R.string.psct6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem EP");
        timetables.setThumbnail(R.string.ep6);
        mItems.add(timetables);

        timetables = new Timetable();
        timetables.setName("6th Sem PRODUCTION");
        timetables.setThumbnail(R.string.prod6);
        mItems.add(timetables);



        this.itemsAll = (ArrayList<Timetable>) items.clone();
        this.suggestions = new ArrayList<>();
        this.viewResourceId = viewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(viewResourceId, null);
        }
        Timetable timetable = mItems.get(position);
        if (timetable != null) {
            TextView textView = (TextView) v.findViewById(R.id.tv);
            if (textView!= null) {
                textView.setText(timetable.getName());
            }
        }
        return v;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((Timetable)(resultValue)).getName();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                for (Timetable timetable : itemsAll) {
                    if(timetable.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        suggestions.add(timetable);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Timetable> filteredList = (ArrayList<Timetable>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (Timetable c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}