package com.lau.studyassistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int code_index, name_index, row_index;
    TextView col1, col2;
    TableLayout table;
    String CSC498G, HST242H, MEE548, GNE340;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        table = (TableLayout) findViewById(R.id.table);

        table.setColumnStretchable(0, true);
        table.setColumnStretchable(1, true);

        row_index = 0;

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
                final TableRow new_row = new TableRow(getApplicationContext());
                col1 = new TextView(getApplicationContext());
                col2= new TextView(getApplicationContext());
                col1.setText(cursor.getString(code_index));
                col1.setTextSize(18);
                col1.setGravity(Gravity.CENTER);
                col1.setTypeface(null, Typeface.BOLD);
                col2.setText(cursor.getString(name_index));
                col1.setGravity(Gravity.CENTER);
                col1.setTypeface(null, Typeface.BOLD);
                new_row.addView(col1);
                new_row.addView(col2);
                new_row.setId(row_index);

                new_row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent next = new Intent("android.intent.action.VIEW",
                                Uri.parse(cursor.getString(code_index)));
                    }
                });

                row_index++;
                cursor.moveToNext();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}