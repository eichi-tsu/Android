package com.example.tamesi_floating;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class saving_passwd extends AppCompatActivity {
    ListView lv;
    DBHelper dh;
    SQLiteDatabase db;
    Cursor c;
    Button verify_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwd_saving);
        Intent i2 = getIntent();
        int version = i2.getIntExtra("version",-0);
        lv = findViewById(R.id.list2);
        dh = new DBHelper(getApplicationContext());
        db = dh.getReadableDatabase();
        if(version == 1){
            c = db.rawQuery("select * from HASHED;",null);
        }
        else{
            c = db.rawQuery("select * from PC;",null);
        }

        List<String> listing = new ArrayList<String>();
        verify_btn = (Button)findViewById(R.id.verify_btn);
        while(c.moveToNext()) {
            listing.add("Date:" + c.getString(0)+"Site:"+c.getString(1)+"id:"+c.getString(2)+"passwd:"+c.getString(3));
        }
        // adapter 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listing);
        lv.setAdapter(adapter);
        c.close();
        dh.close();

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), saving_passwd.class);
                i.putExtra("version",1);
                startActivity(i);
            }
        });
    }
}
