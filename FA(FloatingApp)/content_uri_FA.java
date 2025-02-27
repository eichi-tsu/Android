package com.example.tamesi_floating;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class content_uri extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rd_w;
    RadioButton rd_c;
    RadioButton rd_l;
    RadioButton rd_w_c;
    RadioButton rd_w_c_l;
    ListView lv;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.uricontent);
        List<String> listing = new ArrayList<String>();
        rd_w = (RadioButton)findViewById(R.id.rd_w);
        rd_c = (RadioButton)findViewById(R.id.rd_c);
        rd_l = (RadioButton)findViewById(R.id.rd_l);
        rd_w_c = (RadioButton)findViewById(R.id.rd_w_c);
        rd_w_c_l = (RadioButton)findViewById(R.id.rd_w_c_l);
        rg = (RadioGroup) findViewById(R.id.rg);
        lv = (ListView)findViewById(R.id.list2);
        for(int iter =0; iter<10;iter++){
            listing.add("http://mjc.ac.kr");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listing);
        lv.setAdapter(adapter);
        /*rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                if(checkId==R.id.rd_w){
                    Intent i = new Intent(getApplicationContext(),WebViewActivity.class);
                    i.putExtra("rg","w");
                }
                else if(checkId==R.id.rd_c){
                    Intent i = new Intent(getApplicationContext(),WebViewActivity.class);
                    i.putExtra("rg","c");
                }
                else if(checkId==R.id.rd_l){
                    Intent i = new Intent(getApplicationContext(),WebViewActivity.class);
                    i.putExtra("rg","l");
                }
                else if(checkId==R.id.rd_w_c){
                    Intent i = new Intent(getApplicationContext(),WebViewActivity.class);
                    i.putExtra("rg","w_c");
                }
                else if(checkId==R.id.rd_w_c_l){
                    Intent i = new Intent(getApplicationContext(),WebViewActivity.class);
                    i.putExtra("rg","w_c_l");
                }
            }
        });*/
    }
}
