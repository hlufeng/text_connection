package com.example.lenovo.qimobigmissionapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends FragmentActivity {

    private Fragment mFragments[];
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RadioButton rbtMain,rbtCollege,rbtSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragments = new Fragment[3];
        fragmentManager = getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.fragment_main);
        mFragments[1] = fragmentManager.findFragmentById(R.id.fragment_college);
        mFragments[2] = fragmentManager.findFragmentById(R.id.fragment_setting);

        fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
        fragmentTransaction.show(mFragments[0]).commit();

        rbtMain = (RadioButton)findViewById(R.id.radioMain);
        rbtCollege = (RadioButton)findViewById(R.id.radioCollege);
        rbtSetting = (RadioButton)findViewById(R.id.radioSetting);
        rbtMain.setBackgroundResource(R.drawable.shape3);

        radioGroup = (RadioGroup)findViewById(R.id.bottomGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("radioGroup", "checkId=" + checkedId);
                fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);

                rbtMain.setBackgroundResource(R.drawable.shape2);
                rbtCollege.setBackgroundResource(R.drawable.shape2);
                rbtSetting.setBackgroundResource(R.drawable.shape2);

                switch(checkedId){
                    case R.id.radioMain:
                        fragmentTransaction.show(mFragments[0]).commit();
                        rbtMain.setBackgroundResource(R.drawable.shape3);
                        break;
                    case R.id.radioCollege:
                        fragmentTransaction.show(mFragments[1]).commit();
                        rbtCollege.setBackgroundResource(R.drawable.shape3);
                        break;
                    case R.id.radioSetting:
                        fragmentTransaction.show(mFragments[2]).commit();
                        rbtSetting.setBackgroundResource(R.drawable.shape3);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
