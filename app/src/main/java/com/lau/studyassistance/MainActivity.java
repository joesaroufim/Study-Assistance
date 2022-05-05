package com.lau.studyassistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declaring variables
    int code_index, name_index;
    String CSC498G, HST242H, MEE548, GNE340;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Storing the urls in String Objects
        CSC498G = "https://ionicframework.com/";
        HST242H = "https://www.edx.org/learn/middle-east";
        MEE548 = "https://www.media.mit.edu/groups/biomechatronics/overview/";
        GNE340 = "https://www.edx.org/course/entrepreneurship-for-engineers";
        String[] pages = {CSC498G, HST242H, MEE548, GNE340};

        // Initializing the ListView
        listView = (ListView) findViewById(R.id.list);
        // Creating an ArrayList
        ArrayList<String> list = new ArrayList<String>();

        try{ // Creating an SQLiteDatabase object
            SQLiteDatabase sql = this.openOrCreateDatabase("final_exam_db", MODE_PRIVATE, null);
            sql.execSQL("CREATE Table IF NOT EXISTS exams (code VARCHAR, name VARCHAR)"); // Creating or updating the created table
//            sql.execSQL("INSERT INTO exams (code, name) VALUES ('CSC498G', 'Mobile Computing')");
//            sql.execSQL("INSERT INTO exams (code, name) VALUES ('HST242H', 'History - Europe & M.E.')");
//            sql.execSQL("INSERT INTO exams (code, name) VALUES ('MEE548', 'Introduction to Biomechatronics')");
//            sql.execSQL("INSERT INTO exams (code, name) VALUES ('GNE340', 'Engineering Entrepreneurship')");

            // Creating a cursor
            Cursor cursor = sql.rawQuery("SELECT * FROM exams", null);
            code_index = cursor.getColumnIndex("code"); // Getting the index of each column
            name_index = cursor.getColumnIndex("name");
            cursor.moveToFirst(); // Moving the cursor to the top of the table

            do { // adding each row to the arraylist
                list.add(cursor.getString(name_index));
            }while (cursor.moveToNext());

            // Created an ArrayAdapter
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
            listView.setAdapter(adapter); // assigning the adapter to the ListView

            // Creating a clickable listView items
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent next = new Intent(Intent.ACTION_VIEW, Uri.parse(pages[i])); // Moving to the corresponding webpage
                    startActivity(next);
                }
            });

        }catch(Exception e){
            Log.i ("exceptt", e.getMessage());
            e.printStackTrace();
        }

    }
}