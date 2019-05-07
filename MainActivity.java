package com.example.chap8;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button btn_add,btn_inquiry;
TextView result;
EditText depart,num,name,tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add=findViewById(R.id.btn1);
        btn_inquiry=findViewById(R.id.btn2);
        result= findViewById(R.id.text);
        depart = findViewById(R.id.edt_depart);
        num = findViewById(R.id.edt_num);
        name = findViewById(R.id.edt_name);
        tel = findViewById(R.id.edt_tel);

        btn_add.setOnClickListener(this);
        btn_inquiry.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==btn_add){
            //레코드 추가
            //데이터베이스 생성 또는 오픈
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //insert into student values(20601, "강경태", "010-1234-6789","소프트웨어과")
            String num1 = num.getText().toString();
            String name1 = name.getText().toString();
            String tel1 = tel.getText().toString();
            String depart1 = depart.getText().toString();
            String insertSQL = "insert into student values("+num1+", '"+name1+"', '"+tel1+"', '"+depart1+"')";
            try {
                db.execSQL(insertSQL);
                Toast.makeText(this, "레코드 1개 추가", Toast.LENGTH_SHORT).show();
            }catch(Exception e){
                Toast.makeText(this, "레코드 추가 실패", Toast.LENGTH_SHORT).show();
                Log.e("insertSQL","Error");
            }
            db.close();
        }else if(v==btn_inquiry){
            //레코드 조회
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String inquirySQL = "select * from student";
            //데이터 받아오기
            Cursor cursor = db.rawQuery(inquirySQL,null);
            result.setText("");
            while(cursor.moveToNext()){
                int num1 = cursor.getInt(0);
                String name1 = cursor.getString(1);
                String tel1 = cursor.getString(2);
                String depart1 = cursor.getString(3);
                result.append(depart1+"\t"+num1+"\t"+name1+"\t"+tel1+"\n");
            }
            db.close();
        }
    }
}
