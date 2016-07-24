//package com.rnrapps.user.dtuguide;
//
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuItem;
//
//
//public class MainActivity extends AppCompatActivity {
//
//    private Toolbar toolbar;
//    private ViewPager pager;
//    private ViewPagerAdapter adapter;
//    private TabLayout tabs;
//
//    public static FragmentManager fragmentManager;
//    CharSequence Titles[]={"  Dce Speaks Up  ","  Map  ","  Contact  ","  Notes  "," Time Table "};
//    int Numboftabs =5;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
//        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);
//
//        fragmentManager = getSupportFragmentManager();
//
//        pager = (ViewPager) findViewById(R.id.pager);
//        pager.setAdapter(adapter);
//        tabs = (TabLayout) findViewById(R.id.sliding_tabs);
//        tabs.setupWithViewPager(pager);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        if(id==R.id.rate){
//            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + this.getPackageName())));
//        }
//        else if(id==R.id.feedback){
//            Intent intent = new Intent(Intent.ACTION_SENDTO);
//            intent.setType("text/plain");
//            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//            intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
//            intent.setData(Uri.parse("mailto:dtuapp16@gmail.com"));
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
//        else if(id==R.id.aboutDevelopers){
//
//            Intent intent=new Intent(getApplicationContext(),AboutDevelopers.class);
//            startActivity(intent);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onResumeFragments() {
//        super.onResumeFragments();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//}
