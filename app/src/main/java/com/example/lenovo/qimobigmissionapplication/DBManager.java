package com.example.lenovo.qimobigmissionapplication;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {

    private DBHelper dbHelper;
    private String TBNAME;
    String TAG="DBManger";

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    public void add(CollegeItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", item.getTitle());
        values.put("date", item.getDate());
        values.put("text", item.getText());
        values.put("addition", item.getAddition());
        values.put("url", item.getUrl());
        values.put("additionhref", item.getAdditionhref());
        db.insert(TBNAME, null, values);
        db.close();
    }

    public void addAll(List<CollegeItem> list){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (CollegeItem item : list) {
            ContentValues values = new ContentValues();
            values.put("title", item.getTitle());
            values.put("date", item.getDate());
            values.put("text", item.getText());
            values.put("addition", item.getAddition());
            values.put("url", item.getUrl());
            values.put("additionhref", item.getAdditionhref());
            db.insert(TBNAME, null, values);
        }
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
    }

    public void delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void update(CollegeItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", item.getTitle());
        values.put("date", item.getDate());
        values.put("text", item.getText());
        values.put("addition", item.getAddition());
        values.put("url", item.getUrl());
        values.put("additionhref", item.getAdditionhref());
        db.update(TBNAME, values, "ID=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    public List<CollegeItem> listAll(){
        List<CollegeItem> rateList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if(cursor!=null){
            rateList = new ArrayList<CollegeItem>();
            while(cursor.moveToNext()){
                CollegeItem item = new CollegeItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                item.setDate(cursor.getString(cursor.getColumnIndex("DATE")));
                item.setText(cursor.getString(cursor.getColumnIndex("TEXT")));
                item.setAddition(cursor.getString(cursor.getColumnIndex("ADDITION")));
                item.setUrl(cursor.getString(cursor.getColumnIndex("URL")));
                item.setAdditionhref(cursor.getString(cursor.getColumnIndex("ADDITIONHREF")));
                rateList.add(item);
            }
            cursor.close();
        }
        db.close();
        return rateList;

    }

    public CollegeItem findById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, "ID=?", new String[]{String.valueOf(id)}, null, null, null);
        CollegeItem collegeItem = null;
        if(cursor!=null && cursor.moveToFirst()){
            collegeItem = new CollegeItem();
            collegeItem.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            collegeItem.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
            collegeItem.setDate(cursor.getString(cursor.getColumnIndex("DATE")));
            collegeItem.setText(cursor.getString(cursor.getColumnIndex("TEXT")));
            collegeItem.setAddition(cursor.getString(cursor.getColumnIndex("ADDITION")));
            collegeItem.setUrl(cursor.getString(cursor.getColumnIndex("URL")));
            collegeItem.setAdditionhref(cursor.getString(cursor.getColumnIndex("ADDITIONHREF")));
            cursor.close();
        }
        db.close();
        return collegeItem;
    }

    public boolean searchUrl(String url){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TBNAME,null,"URL=?",new String[]{url},null,null,null);
        cursor.moveToFirst();
        Boolean b=true;
        if (cursor.getCount()==0){
            b=false;
        }
        cursor.close();
        db.close();
        return b;
    }

    public CollegeItem getInfo(String url){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        CollegeItem collegeItem = new CollegeItem();
        Cursor cursor=db.query(TBNAME,null,"URL=?",new String[]{url},null,null,null);
        cursor.moveToFirst();
        try {
            if(cursor.getCount()!=0){
                Log.i(TAG, "getInfo: 已在数据库中查询到此数据");
            }
            collegeItem.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
            collegeItem.setDate(cursor.getString(cursor.getColumnIndex("DATE")));
            collegeItem.setText(cursor.getString(cursor.getColumnIndex("TEXT")));
            collegeItem.setAddition(cursor.getString(cursor.getColumnIndex("ADDITION")));
            collegeItem.setAdditionhref(cursor.getString(cursor.getColumnIndex("ADDITIONHREF")));
            cursor.close();
        }catch (NullPointerException e){
            Log.i(TAG, "getInfo: 未在数据库中查询到此数据");
        }
        db.close();
        return collegeItem;
    }
}

