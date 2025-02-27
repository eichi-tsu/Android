package com.example.main_page;

import static java.lang.System.out;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class CheckRecoder extends AppCompatActivity {
    AnalogClock ac;
    DigitalClock dc;
    TextView tv,titletv,titletv2,tv3,syu,chi,ke;
    LinearLayout sumlay,listlay;
    SQLiteDatabase db;
    DBHelper dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_recoder);
        setTitle("출석여부");
        syu = (TextView)findViewById(R.id.syultusseki);
        chi = (TextView)findViewById(R.id.chikoku);
        ke = (TextView)findViewById(R.id.keltusseki);
        int ssum = 0,csum=0,ksum=0;
        dh = new DBHelper(getApplicationContext());
        db = dh.getReadableDatabase();
        tv3 = (TextView)findViewById(R.id.tv3);

        Cursor c = db.rawQuery("select * from check_member where name = '"+getIntent().getStringExtra("id")+"'",null);

        while(c.moveToNext()){
            if(c.getString(0).equals(getIntent().getStringExtra("id"))){
                tv3.append(c.getString(1)+" : "+c.getString(2)+":"+c.getString(3)+"\n");
                switch (c.getString(3)) {
                    case "출석":
                        ssum += 1;
                    case "지각":
                        csum += 1;
                    case "결석":
                        ksum += 1;
                }
            }
        }
        syu.append(""+ssum);
        chi.append(""+csum);
        ke.append(""+ksum);

        /*
        Random rand = new Random();
        HashMap<String, String> date_check = new HashMap<String, String>();
        for(int i3 = 0;i3<20;i3++){
            date_check.put("2021-12-"+(9+i3)+"\n",state[(int)rand.nextInt(2)]);
        }*/
        tv = (TextView) findViewById(R.id.ctv);

        registerForContextMenu(tv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.cmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        sumlay = (LinearLayout) findViewById(R.id.sumlay);
        listlay = (LinearLayout) findViewById(R.id.listlay);
        titletv = (TextView) findViewById(R.id.titletv);
        titletv2 = (TextView) findViewById(R.id.titletv2);
        if(item.getItemId()==R.id.summenu){
            listlay.setVisibility(View.INVISIBLE);
            sumlay.setVisibility(View.VISIBLE);
            titletv2.setVisibility(View.INVISIBLE);
            titletv.setVisibility(View.VISIBLE);
        }
        else if(item.getItemId()==R.id.listmenu){
            sumlay.setVisibility(View.INVISIBLE);
            listlay.setVisibility(View.VISIBLE);
            titletv2.setVisibility(View.VISIBLE);
            titletv.setVisibility(View.INVISIBLE);
        }
        return super.onContextItemSelected(item);
    }
}