package com.example.lenovo.qimobigmissionapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CollegeListItemInfoActivity extends AppCompatActivity implements Runnable {

    String url;
    String TAG = "CollegeListItemInfoActivity";
    Handler handler;
    TextView titletv, datetv, texttv, addtv,sourcetv;
    String title, date, text, add = "";
    String href;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_list_item_info);

        titletv = findViewById(R.id.clii_title);
        datetv = findViewById(R.id.clii_date);
        texttv = findViewById(R.id.clii_text);
        addtv = findViewById(R.id.clii_add);
        sourcetv =findViewById(R.id.clii_source);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        Log.i(TAG, "onCreate: url:" + url);

        //阅读原文控件
        sourcetv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        Thread thread = new Thread(this);
        thread.start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    Bundle bundle = (Bundle) msg.obj;
                    title = bundle.get("title").toString();
                    date = bundle.get("date").toString();
                    text = bundle.get("text").toString();
                    add = bundle.get("add").toString();
                    href = bundle.get("href").toString();
                    Log.i(TAG, "handleMessage: add:" + add);
                    titletv.setText(title);
                    datetv.setText(date);
                    texttv.setText(text);
                    Log.i(TAG, "handleMessage: add:"+add);
                    if (!add.equals("关闭 打印")){
                        addtv.setText(add);
                        addtv.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(href));
                                startActivity(intent);
                            }
                        });
                    }else {
                        addtv.setVisibility(addtv.INVISIBLE);//隐藏
                    }
                }
                super.handleMessage(msg);
            }
        };

        //添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

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
    public void run() {
        Document doc = null;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean marker = false;
        try {
            doc = Jsoup.connect(url).get();
            Log.i(TAG, "run: " + doc.title());
            Elements divs = doc.getElementsByTag("div");
            //标题
            title = divs.get(19).text();
            //时间
            Element div = divs.get(18);
            div.getElementsByTag("span");
            date = div.getAllElements().get(3).text();
//            Log.i(TAG, "run: "+div.getAllElements().get(3).text());
            //正文
            text = divs.get(22).text();
            //附件
            add = divs.get(23).getElementsByAttribute("href").text();
//            Log.i(TAG, "run: add:"+add);
            String a = "https://it.swufe.edu.cn";
            href = a + divs.get(23).getElementsByTag("a").attr("href");
            Log.i(TAG, "run: href:"+href);
            marker = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("date", date);
        bundle.putString("text", text);
        bundle.putString("add", add);
        bundle.putString("href", href);
        Message msg = handler.obtainMessage(1);
        //使用msg中的arg1属性标记操作是否成功
        if (marker) {
            msg.arg1 = 1;
        } else {
            msg.arg1 = 0;
        }
        msg.obj = bundle;
        handler.sendMessage(msg);
    }
}
