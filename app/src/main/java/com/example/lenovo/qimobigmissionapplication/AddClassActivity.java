package com.example.lenovo.qimobigmissionapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddClassActivity extends AppCompatActivity {

    Spinner spinner,spinner_index;
    String TAG="AddClassActivity";
    int week,step,index;
    String name,loacation,teacher;
    EditText class_name,class_location,class_teacher;
    RadioGroup radioGroup;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        //添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        class_name=findViewById(R.id.class_name);
        class_location=findViewById(R.id.class_loacation);
        class_teacher=findViewById(R.id.class_teacher);
        spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String result=parent.getItemAtPosition(position).toString();
                Log.i(TAG, "onItemSelected: result:"+result);
                Log.i(TAG, "onItemSelected: positon:"+position);
                week=position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup=findViewById(R.id.class_step);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=findViewById(checkedId);
                Log.i(TAG, "onCheckedChanged: checkId:"+checkedId);
                String check=radioButton.getText().toString();
                if (check.equals("2课时")){
                    step=2;
                }else if(check.equals("3课时")) {
                    step = 3;
                }
            }
        });

        spinner_index=findViewById(R.id.spinner_index);
        spinner_index.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index=position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    public void add(View view){
        name=class_name.getText().toString();
        loacation=class_location.getText().toString();
        teacher=class_teacher.getText().toString();
        id="w"+String.valueOf(week)+String.valueOf(index);
        Intent intent=new Intent(this,MainActivity.class);
        ClassItem classItem=new ClassItem();
        classItem.setId(id);
        classItem.setWeek(week);
        classItem.setIndex(index);
        classItem.setStep(step);
        classItem.setName(name);
        classItem.setLocation(loacation);
        classItem.setTeacher(teacher);
        try {
            DBManager2 dbManager2=new DBManager2(AddClassActivity.this);
            dbManager2.add(classItem);
        }catch (Exception e){
            Toast.makeText(this,"添加失败",Toast.LENGTH_LONG).show();
        }
        startActivity(intent);
    }
}
