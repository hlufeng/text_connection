package com.example.lenovo.qimobigmissionapplication;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager2 {

    private DBHelper dbHelper;
    private String TBNAME;
    String TAG="DBManger2";

    public DBManager2(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME2;
    }

    public void add(ClassItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", item.getId());
        values.put("week", item.getWeek());
        values.put("cindex", item.getIndex());
        values.put("step", item.getStep());
        values.put("name", item.getName());
        values.put("location", item.getLocation());
        values.put("teacher", item.getTeacher());
        db.insert(TBNAME, null, values);
        db.close();
    }

    public void addAll(List<ClassItem> list){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (ClassItem item : list) {
            ContentValues values = new ContentValues();
            values.put("id", item.getId());
            values.put("week", item.getWeek());
            values.put("cindex", item.getIndex());
            values.put("step", item.getStep());
            values.put("name", item.getName());
            values.put("location", item.getLocation());
            values.put("teacher", item.getTeacher());
            db.insert(TBNAME, null, values);
        }
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME,null,null);
        db.close();
    }

    public void delete(String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void update(ClassItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("id", item.getId());
        values.put("week", item.getWeek());
        values.put("cindex", item.getIndex());
        values.put("step", item.getStep());
        values.put("name", item.getName());
        values.put("location", item.getLocation());
        values.put("teacher", item.getTeacher());
        db.update(TBNAME, values, "ID=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    public List<ClassItem> listAll(){
        List<ClassItem> rateList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if(cursor!=null){
            rateList = new ArrayList<ClassItem>();
            while(cursor.moveToNext()){
                ClassItem item = new ClassItem();
                item.setId(cursor.getString(cursor.getColumnIndex("ID")));
                item.setWeek(cursor.getInt(cursor.getColumnIndex("WEEK")));
                item.setIndex(cursor.getInt(cursor.getColumnIndex("CINDEX")));
                item.setStep(cursor.getInt(cursor.getColumnIndex("STEP")));
                item.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                item.setLocation(cursor.getString(cursor.getColumnIndex("LOCATION")));
                item.setTeacher(cursor.getString(cursor.getColumnIndex("TEACHER")));
                rateList.add(item);
            }
            cursor.close();
        }
        db.close();
        return rateList;

    }

    public ClassItem findById(String id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, "ID=?", new String[]{String.valueOf(id)}, null, null, null);
        ClassItem item = null;
        if(cursor!=null && cursor.moveToFirst()){
            item = new ClassItem();
            item.setId(cursor.getString(cursor.getColumnIndex("ID")));
            item.setWeek(cursor.getInt(cursor.getColumnIndex("WEEK")));
            item.setIndex(cursor.getInt(cursor.getColumnIndex("CINDEX")));
            item.setStep(cursor.getInt(cursor.getColumnIndex("STEP")));
            item.setName(cursor.getString(cursor.getColumnIndex("NAME")));
            item.setLocation(cursor.getString(cursor.getColumnIndex("LOCATION")));
            item.setTeacher(cursor.getString(cursor.getColumnIndex("TEACHER")));
            cursor.close();
        }
        db.close();
        return item;
    }
}


