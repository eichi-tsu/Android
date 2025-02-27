package com.example.main_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class message extends AppCompatActivity {
    RadioButton inputrdo;
    InputRadio inputfra;
    FragmentManager fm = getSupportFragmentManager();
    DBHelper dh;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        setTitle("메시지");
        inputrdo = (RadioButton) findViewById(R.id.inputrdo);
        inputfra = new InputRadio();
        inputrdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dh = new DBHelper(getApplicationContext());
                long now = System.currentTimeMillis();
                db = dh.getWritableDatabase();
                Date date = new Date(now);
                SimpleDateFormat date_fo = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat time_fo = new SimpleDateFormat("hh-mm");
                String s = getIntent().getStringExtra("id");
                db.execSQL("INSERT INTO check_member values ('"+getIntent().getStringExtra("id")+"','"+date_fo.format(date)+"','"+null+"','"+"결석"+"')");
                dh.close();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.cont1,inputfra);
                ft.commit();
            }
        });
    }
}