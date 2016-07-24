//package com.rnrapps.user.dtuguide;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
//
////import com.rnrapps.user.dtuguide.CollegeMap.Tab1;
////import com.rnrapps.user.dtuguide.DceSpeaksUp.Tab0;
////import com.rnrapps.user.dtuguide.ImpContacts.Tab2;
////import com.rnrapps.user.dtuguide.Notes.Tab3;
////import com.rnrapps.user.dtuguide.TimeTables.Tab4;
//
///**
// * Created by rohanpc on 10/18/2015.
// */
//public class ViewPagerAdapter extends FragmentStatePagerAdapter {
//
//    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
//    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
//
//
//    // Build a Constructor and assign the passed Values to appropriate values in the class
//    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
//        super(fm);
//
//        this.Titles = mTitles;
//        this.NumbOfTabs = mNumbOfTabsumb;
//
//    }
//
//    //This method return the fragment for the every position in the View Pager
//    @Override
//    public Fragment getItem(int position) {
//
//        if(position == 1) // if the position is 0 we are returning the First tab
//        {
//            Tab1 tab1 = new Tab1();
//            return tab1;
//        }
//        else if (position==0){
//            Tab0 tab0=new Tab0();
//            return tab0;
//        }
//        else if (position==3){
//            Tab3 tab3=new Tab3();
//            return tab3;
//        }
//        else if (position==4){
//            Tab4 tab4=new Tab4();
//            return tab4;
//        }
//        else{
//            Tab2 tab2=new Tab2();
//            return tab2;
//        }
//
//    }
//
//    // This method return the titles for the Tabs in the Tab Strip
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return Titles[position];
//    }
//
//    // This method return the Number of tabs for the tabs Strip
//
//    @Override
//    public int getCount() {
//        return NumbOfTabs;
//    }
//}