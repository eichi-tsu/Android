package com.example.main_page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageButton imgbtn,mapbtn;
    AnalogClock ac;
    DigitalClock dc;
    Button btncheck;
    SQLiteDatabase db;
    DBHelper dh;
    LocalDateTime current=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("출석 체크");
        imgbtn = (ImageButton)findViewById(R.id.imgbtn);
        mapbtn = (ImageButton)findViewById(R.id.mapbtn);
        ac = (AnalogClock)findViewById(R.id.ac);
        dc = (DigitalClock)findViewById(R.id.dc);
        btncheck = (Button)findViewById(R.id.btncheck);



        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("geo: 37.564213 , 127.001698");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ac.getVisibility()==View.VISIBLE){
                    imgbtn.setImageResource(R.drawable.pikachu_tokei);
                    ac.setVisibility(View.INVISIBLE);
                    dc.setVisibility(View.VISIBLE);
                }
                else if(dc.getVisibility()==View.VISIBLE){
                    imgbtn.setImageResource(R.drawable.emiya_tokei);
                    dc.setVisibility(View.INVISIBLE);
                    ac.setVisibility(View.VISIBLE);
                }
            }
        });
        btncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dh = new DBHelper(getApplicationContext());
                db = dh.getWritableDatabase();
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat date_fo = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat time_fo = new SimpleDateFormat("hh-mm");
                SimpleDateFormat time_hour = new SimpleDateFormat("h");
                SimpleDateFormat time_minute = new SimpleDateFormat("m");
                if(Integer.parseInt(time_hour.format(date))<10 &&Integer.parseInt(time_hour.format(date))>=9 &&Integer.parseInt(time_minute.format(date))>=30){
                    db.execSQL("INSERT INTO check_member values ('"+getIntent().getStringExtra("id")+"','"+date_fo.format(date)+"','"+time_fo.format(date)+"','"+"출석"+"')");
                }
                else{
                    db.execSQL("INSERT INTO check_member values ('"+getIntent().getStringExtra("id")+"','"+date_fo.format(date)+"','"+time_fo.format(date)+"','"+"지각"+"')");
                }
                db.close();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.count_menu){
            String s = getIntent().getStringExtra("id");
            Intent intent = new Intent(getApplicationContext(),CheckRecoder.class);
            intent.putExtra("id",s);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.message_menu){
            String s = getIntent().getStringExtra("id");
            Intent intent = new Intent(getApplicationContext(),message.class);
            intent.putExtra("id",s);
            startActivity(intent);
        }
        return false;
    }

}