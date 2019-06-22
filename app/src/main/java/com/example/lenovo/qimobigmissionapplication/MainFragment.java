package com.example.lenovo.qimobigmissionapplication;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    int week, index, step;
    String TAG = "MainFragment";
    String name, location, teacher, dbid;
    LinearLayout week1, week2, week3, week4, week5;
    DBManager2 dbManager2;
    LinearLayout.LayoutParams lp;
    int tid;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        setHasOptionsMenu(true);
        init();
        Log.i(TAG, "onActivityCreated: ");
    }

    public void init() {
        dbManager2 = new DBManager2(getContext());
        List<ClassItem> list = dbManager2.listAll();
        for (ClassItem classItem : list) {
//            TextView tv = new TextView(getActivity());
            dbid = classItem.getId();
            week = classItem.getWeek();
            index = classItem.getIndex();
            step = classItem.getStep();
            name = classItem.getName();
            location = classItem.getLocation();
            teacher = classItem.getTeacher();
            Log.i(TAG, "init: dbid:" + dbid);
            Log.i(TAG, "onActivityCreated: name" + name);
            Log.i(TAG, "onActivityCreated: location" + location);
            Log.i(TAG, "onActivityCreated: teacher" + teacher);
            Log.i(TAG, "onActivityCreated: week" + week);
            Log.i(TAG, "onActivityCreated: index" + index);
            Log.i(TAG, "onActivityCreated: step" + step);
            //构造id
            String sid = "w" + String.valueOf(week) + String.valueOf(index);
            Log.i(TAG, "init: id:" + sid);
            //动态id寻找view
            final TextView view = getView().findViewById(getContext().getResources().getIdentifier(sid, "id", getContext().getPackageName()));
            //设置view
            lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, step);
            lp.gravity = Gravity.CENTER;
            view.setLayoutParams(lp);
            view.setText(name + "\n" + location + "\n" + teacher + "\n课时:" + step);
            //隐藏其他view
            for (int i = 1; i < step; i++) {
                String oid = "w" + String.valueOf(week) + String.valueOf(index + i);
                TextView textView = getView().findViewById(getContext().getResources().getIdentifier(oid, "id", getContext().getPackageName()));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0);
                textView.setLayoutParams(layoutParams);
//                        textView.setHeight(0);
            }
            //对view添加监听
            view.setOnLongClickListener(new TextView.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    tid = v.getId();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("提示").setMessage("请选择您的操作：").setPositiveButton("删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i(TAG, "onClick: which:" + which);
                            deletClass(tid);
                        }
                    }).setNegativeButton("修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            updateClass(tid);
                        }
                    }).setNeutralButton("取消", null);
                    builder.create().show();
                    return true;
                }
            });
        }
    }

    public void deletClass(int tid) {
        //通过id值获取控件
        TextView textView = getView().findViewById(tid);
        //获取id值获取id名
        String id = textView.getResources().getResourceName(tid);
        int first = id.indexOf("/"); //斜杠第一次出现的位置
        String idname = id.substring(first + 1);//截取后变成新的字符串
        Log.i(TAG, "deletClass: idname:" + idname);
        //在数据库中删除课程
        dbManager2.delete(idname);
        //删除当前页面的课程（不刷新）
        //通过id名获取week、index
        String id_week = idname.substring(0, 2);
        Log.i(TAG, "deletClass: id_week:" + id_week);
        int id_index = Integer.parseInt(idname.substring(2));
        Log.i(TAG, "deletClass: id_index:" + id_index);
        //通过text获取step
        String text = textView.getText().toString();
        Log.i(TAG, "deletClass: text:" + text);
        int first1 = text.indexOf("课时:");
        int id_step = Integer.parseInt(text.substring(first1 + 3));
        Log.i(TAG, "deletClass: id_step:" + id_step);
        textView.setText("");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        layoutParams.gravity = Gravity.CENTER;
        textView.setLayoutParams(layoutParams);
        for (int i = 1; i < id_step; i++) {
            String idd = id_week + String.valueOf(id_index + i);
            Log.i(TAG, "deletClass: idd:" + idd);
            TextView textView1 = getView().findViewById(getContext().getResources().getIdentifier(idd, "id", getContext().getPackageName()));
            textView1.setLayoutParams(layoutParams);
////                        textView.setHeight(0);
        }
    }

    public void updateClass(int tid) {
        Log.i(TAG, "updateClass: ");
        //通过id值获取控件
        TextView textView = getView().findViewById(tid);
        //获取id值获取id名
        String id = textView.getResources().getResourceName(tid);
        int first = id.indexOf("/"); //斜杠第一次出现的位置
        String idname = id.substring(first + 1);//截取后变成新的字符串
        Log.i(TAG, "deletClass: idname:" + idname);
        Intent intent = new Intent(getActivity(), EditClassActivity.class);
        intent.putExtra("id", idname);
        startActivity(intent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于onResume
            Log.i(TAG, "setUserVisibleHint: 刷新！！！！！！！！");
            init();
        }
    }
}
