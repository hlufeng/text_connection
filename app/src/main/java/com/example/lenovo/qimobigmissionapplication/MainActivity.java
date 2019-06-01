package com.example.lenovo.qimobigmissionapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private Fragment mFragments[];
    TextView mainTV;
    TextView collegeTV;
    TextView settingTV;
    ImageView mainIG;
    ImageView collegeIG;
    ImageView settingIG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ImageView imageViewMain=findViewById(R.id.image_main);
//        ImageView imageViewCollege=findViewById(R.id.image_college);
//        ImageView imageViewSetting=findViewById(R.id.image_setting);
//        //创建监听器
//        imageViewMain.setOnClickListener(onClickListener);
//        imageViewCollege.setOnClickListener(onClickListener);
//        imageViewSetting.setOnClickListener(onClickListener);
        mainTV=findViewById(R.id.tv_main);
        collegeTV=findViewById(R.id.tv_college);
        settingTV=findViewById(R.id.tv_setting);
        mainIG=findViewById(R.id.image_main);
        collegeIG=findViewById(R.id.image_college);
        settingIG=findViewById(R.id.image_setting);
        //默认选中的图标
        mainTV.setTextColor(this.getResources().getColor(R.color.blue));
        mainIG.setImageResource(R.drawable.ic_mainclick);

        LinearLayout linearLayoutMain=findViewById(R.id.main_ll);
        LinearLayout linearLayoutCollege=findViewById(R.id.college_ll);
        LinearLayout linearLayoutSetting=findViewById(R.id.setting_ll);
        //创建监听器
        linearLayoutMain.setOnClickListener(onClickListener);
        linearLayoutCollege.setOnClickListener(onClickListener);
        linearLayoutSetting.setOnClickListener(onClickListener);

        mFragments = new Fragment[3];
        fragmentManager=getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.main_fragment);
        mFragments[1] = fragmentManager.findFragmentById(R.id.college_fragment);
        mFragments[2] = fragmentManager.findFragmentById(R.id.setting_fragment);
        fragmentTransaction=fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
        fragmentTransaction.show(mFragments[0]).commit();

    }

    public void resetColor(){
        mainTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        mainIG.setImageResource(R.drawable.ic_main);
        collegeTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        collegeIG.setImageResource(R.drawable.ic_college);
        settingTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        settingIG.setImageResource(R.drawable.ic_setting);
    }

    View.OnClickListener onClickListener =new View.OnClickListener(){
        @Override
        public void onClick(View v){
            fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
            switch (v.getId()){
                case R.id.main_ll:
                    fragmentTransaction.show(mFragments[0]).commit();
                    resetColor();
                    mainTV.setTextColor(getResources().getColor(R.color.blue));
                    mainIG.setImageResource(R.drawable.ic_mainclick);
                    break;
                case R.id.college_ll:
                    fragmentTransaction.show(mFragments[1]).commit();
                    resetColor();
                    collegeTV.setTextColor(getResources().getColor(R.color.blue));
                    collegeIG.setImageResource(R.drawable.ic_collegeclick);
                    break;
                case R.id.setting_ll:
                    fragmentTransaction.show(mFragments[2]).commit();
                    resetColor();
                    settingTV.setTextColor(getResources().getColor(R.color.blue));
                    settingIG.setImageResource(R.drawable.ic_settingclick);
                    break;
                default:
                    break;
            }
        }
    };
}
