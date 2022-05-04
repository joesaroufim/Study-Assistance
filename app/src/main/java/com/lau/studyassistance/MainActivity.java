package com.lau.studyassistance;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    int code_index, name_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            SQLiteDatabase sql = this.openOrCreateDatabase("final_exam_db", MODE_PRIVATE, null);
            sql.execSQL("CREATE Table IF NOT EXISTS exams (code VARCHAR, name VARCHAR)");
//            sql.execSQL("INSERT INTO exams (code, name) VALUES ('CSC498G', 'Mobile Computing')");
//            sql.execSQL("INSERT INTO exams (code, name) VALUES ('HST242H', 'History - Europe & M.E.')");
//            sql.execSQL("INSERT INTO exams (code, name) VALUES ('MEE548', 'Introduction to Biomechatronics')");
//            sql.execSQL("INSERT INTO exams (code, name) VALUES ('GNE340', 'Engineering Entrepreneurship')");

            Cursor cursor = sql.rawQuery("SELECT * FROM exams", null);
            code_index = cursor.getColumnIndex("code");
            name_index = cursor.getColumnIndex("name");
            cursor.moveToFirst();

            while (cursor != null){
                String name = cursor.getString(code_index) + " " + cursor.getString(name_index);


                cursor.moveToNext();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}