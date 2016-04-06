package com.example.user.dtuapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by user on 03-02-2016.
 */
public class Counter extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "dtuadda";

    // Contacts table name
    private static final String TABLE_NAME= "counter";

    private static final String ID = "thumbnail";
    private static final String NAME="name";


    private static final String COUNT="count";

    public Counter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE counter(thumbnail INTEGER NOT NULL, count INTEGER, name TEXT);";
        db.execSQL(CREATE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    void add (int thumbnail, String name){
        int c=0;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(ID,thumbnail);
        values.put(COUNT,c);
        values.put(NAME,name);
        db.insert(TABLE_NAME, null, values);
        Log.v(Counter.class.getSimpleName(), "Added an item");
    }
    boolean isEmpty(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME, new String[]{ID, COUNT, NAME}, null, null, null, null, null);
        cursor.moveToFirst();
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }
    ArrayList<EndangeredItem> getAllDetails(){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM counter ORDER BY count DESC",null);

        if (cursor != null&&cursor.moveToFirst()) {
            ArrayList<EndangeredItem> arrayList=new ArrayList<>();
            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount();i++ ) {
                cursor.moveToPosition(i);
                EndangeredItem item=new EndangeredItem();
                Log.v(Counter.class.getSimpleName(), "getAllDetails " + cursor.getInt(cursor.getColumnIndex(COUNT))
                        + " " + cursor.getCount() + " " + cursor.getInt(cursor.getColumnIndex(ID)) + " " + cursor.getString(cursor.getColumnIndex(NAME)));
                item.setThumbnail(cursor.getInt(cursor.getColumnIndex(ID)));
                item.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                arrayList.add(item);
            }
            return arrayList;
        }
        else Log.v(Counter.class.getSimpleName(),"Nothing returned");
        return null;
    }
    int getDetail(int thumbnail){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM counter WHERE thumbnail = " + thumbnail + ";", null);
        //Cursor cursor=db.query(TABLE_NAME,new String[] {COUNT},ID,new String[]{""+thumbnail},null,null,null);
        if(cursor!=null&&cursor.moveToFirst()){
            cursor.moveToFirst();

        Log.v(Counter.class.getSimpleName(),"getDetail "+cursor.getInt(cursor.getColumnIndex(COUNT))
               +" "+cursor.getCount()+" "+cursor.getInt(cursor.getColumnIndex(ID))+" "+cursor.getString(cursor.getColumnIndex(NAME)));
        }
        else Log.v(Counter.class.getSimpleName(),"Nothing returned");

        EndangeredItem item=new EndangeredItem();
        item.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        item.setThumbnail(cursor.getInt(cursor.getColumnIndex(ID)));
        return cursor.getInt(cursor.getColumnIndex(COUNT));
    }
    void update(int thumbnail){
        SQLiteDatabase db=this.getWritableDatabase();
        int newCount=getDetail(thumbnail)+1;

        String update="UPDATE counter SET count ="+newCount+" WHERE thumbnail="+thumbnail+";";
        //Cursor i=db.rawQuery("UPDATE "+TABLE_NAME+" SET "+COUNT+" = " + newCount + " WHERE "+ID+" = "+thumbnail+";",null);
        db.execSQL(update);
        Log.v(Counter.class.getSimpleName(),thumbnail+" Updated "+newCount);

    }

}
