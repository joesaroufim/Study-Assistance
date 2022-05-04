package com.lau.studyassistance;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            SQLiteDatabase sql = this.openOrCreateDatabase("final_examdb", MODE_PRIVATE, null);
            sql.execSQL("CREATE Table IF NOT EXISTS exams (name VARCHAR, code VARCHAR)");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}