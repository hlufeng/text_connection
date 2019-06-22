package com.example.lenovo.qimobigmissionapplication;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        TextView tv = (TextView)getView().findViewById(R.id.setting_tv1);
//        tv.setText("添加课程");
//        View btn=getView().findViewById(R.id.button);
        LinearLayout l1 = getView().findViewById(R.id.setting_ll1);
        LinearLayout l2 = getView().findViewById(R.id.setting_ll2);
        LinearLayout l3 = getView().findViewById(R.id.setting_ll3);
        LinearLayout l4 = getView().findViewById(R.id.setting_ll4);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddClassActivity.class);
                startActivity(intent);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("提示").setMessage("是否清空课表：").setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBManager2 dbManager2 = new DBManager2(getContext());
                        dbManager2.deleteAll();
                        Toast.makeText(getContext(), "课表清空成功！", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("否", null);
                builder.create().show();
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OutlineInfoListActivity.class);
                startActivity(intent);
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("提示").setMessage("是否清空通知缓存：").setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBManager dbManager = new DBManager(getContext());
                        dbManager.deleteAll();
                        Toast.makeText(getContext(), "通知清空成功！", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("否", null);
                builder.create().show();
            }
        });
    }
}
