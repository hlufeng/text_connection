package com.example.lenovo.qimobigmissionapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditClassActivity extends AppCompatActivity {
    TextView tweek,ttime;
    EditText ename,elocation,eteacher;
    int week,index,step;
    String name,location,teacher,id;

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
        setContentView(R.layout.activity_edit_class);
        tweek=findViewById(R.id.classweek);
        ttime=findViewById(R.id.classbe);
        ename=findViewById(R.id.classname);
        elocation=findViewById(R.id.classlocation);
        eteacher=findViewById(R.id.classteacher);
        //添加返回键
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        DBManager2 dbManager2=new DBManager2(this);
        ClassItem classItem=dbManager2.findById(id);
        week=classItem.getWeek();
        index=classItem.getIndex();
        step=classItem.getStep();
        name=classItem.getName();
        location=classItem.getLocation();
        teacher=classItem.getTeacher();

        tweek.setText("week"+week);
        ttime.setText("第"+index+"-"+(index+step)+"节");
        elocation.setText(location);
        ename.setText(name);
        eteacher.setText(teacher);
    }

    public void edit(View view){
        name=ename.getText().toString();
        location=elocation.getText().toString();
        teacher=eteacher.getText().toString();
        Intent intent=new Intent(this,MainActivity.class);
        ClassItem classItem=new ClassItem();
        classItem.setId(id);
        classItem.setWeek(week);
        classItem.setIndex(index);
        classItem.setStep(step);
        classItem.setName(name);
        classItem.setLocation(location);
        classItem.setTeacher(teacher);
        try {
            DBManager2 dbManager2=new DBManager2(this);
            dbManager2.update(classItem);
        }catch (Exception e){
            Toast.makeText(this,"更新失败",Toast.LENGTH_LONG).show();
        }
        startActivity(intent);
    }
}
