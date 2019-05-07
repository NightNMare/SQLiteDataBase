package com.example.chap8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        //데이터베이스 생성 또는 열기
        super(context, "school_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //테이블 생성 - 처음에 한번만 실행
        String createSQL = "create table student (_num integer primary key, name text, tel text, depart text)";
        try{
            db.execSQL(createSQL);
        }catch(Exception e){
            Log.e("onCreate","createSQL Error");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //( 테이블 수정 또는 )    *테이블 삭제 후 재생성*
        String dropSQL = "drop table student";
        db.execSQL(dropSQL);
        onCreate(db);
    }
}
