package com.example.lenovo.qimobigmissionapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

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
//        TextView tv = (TextView)getView().findViewById(R.id.main_tv1);
//        tv.setText("主页面");
        TextView textView2=getView().findViewById(R.id.textView2);
        TextView tv=new TextView(getActivity());
        tv.setText("动态课表");
        tv.setGravity(Gravity.CENTER);
        LinearLayout week1=getView().findViewById(R.id.week1);
        LinearLayout week2=getView().findViewById(R.id.week2);
        LinearLayout week3=getView().findViewById(R.id.week3);
        LinearLayout week4=getView().findViewById(R.id.week4);
        LinearLayout week5=getView().findViewById(R.id.week5);
        week1.addView(tv,5);
    }
}
