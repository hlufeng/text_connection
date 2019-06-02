package com.example.lenovo.qimobigmissionapplication;


import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CollegeFragment extends Fragment implements Runnable{

    String TAG="CollegeFragment";
    Handler handler;
    private ArrayList<HashMap<String, String>> listItems; // 存放文字、图片信息
    private SimpleAdapter listItemAdapter; // 适配器
    List<HashMap<String,String>> retList =new ArrayList<HashMap<String, String>>();

    public CollegeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_college, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        TextView tv = (TextView)getView().findViewById(R.id.college_tv1);
//        tv.setText("学院页面");
        final ListView listView=getView().findViewById(R.id.clist);
        Thread thread =new Thread(CollegeFragment.this);
        thread.start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    listItems = (ArrayList<HashMap<String, String>>) msg.obj;
                    Log.i(TAG, "handleMessage: "+listItems);
                    MyAdapter listAdapter = new MyAdapter(getActivity(),R.layout.collegelist, listItems);
                    listView.setAdapter(listAdapter);
                }
                super.handleMessage(msg);
            }
        };

    }

    @Override
    public void run() {
        Document doc = null;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean marker=false;
        try {
            doc = Jsoup.connect("https://it.swufe.edu.cn/index/tzgg.htm").get();
            Log.i(TAG, "run: "+doc.title());
            Elements uls =doc.getElementsByTag("ul");
            Element ul=uls.get(17);
//            Log.i(TAG, "run: "+ul);
            Elements lis=ul.getElementsByTag("li");
            for(Element li:lis){
//                Log.i(TAG, "run: li"+li.text());
                Elements spans=li.getAllElements();
                String str=spans.get(2).text();
                String val=spans.get(3).text();
                Log.i(TAG, "run: span3"+str);
                Log.i(TAG, "run: span4"+val);

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ItemTitle", str);
                map.put("ItemDate", val);
                retList.add(map);
            }
            marker=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msg =handler.obtainMessage(7);
        //使用msg中的arg1属性标记操作是否成功
        if(marker){
            msg.arg1=1;
        }else {
            msg.arg1=0;
        }
        msg.obj=retList;
        handler.sendMessage(msg);
    }
}
