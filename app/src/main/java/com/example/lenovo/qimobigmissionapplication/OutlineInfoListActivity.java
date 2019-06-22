package com.example.lenovo.qimobigmissionapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OutlineInfoListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ArrayList<HashMap<String,String>> retList =new ArrayList<HashMap<String, String>>();
    List<String> hrefs=new ArrayList<String>();
    String url;
    String TAG="OutlineinfolistActivity";

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //添加返回键
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outline_info_list);
        //添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ListView listView=findViewById(R.id.outline_list);
        DBManager dbManager=new DBManager(this);
        List<CollegeItem> collegeItemList=dbManager.listAll();
        for(CollegeItem item:collegeItemList) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle", item.getTitle());
            map.put("ItemDate", item.getDate());
            retList.add(map);
            hrefs.add(item.getUrl());
        }
        MyAdapter listAdapter = new MyAdapter(this,R.layout.collegelist, retList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent listItemInfo =new Intent(this,CollegeListItemInfoActivity.class);
        url=hrefs.get(position);
        Log.i(TAG, "onItemClick: url:"+url);
        listItemInfo.putExtra("url",url);
        startActivity(listItemInfo);
    }
}
