package com.example.tamesi_floating;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView option_btn;
    ImageView passwd_saving_btn;
    ImageView passwd_hashing_btn;
    ImageView uri_content_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwd_saving_btn = (ImageView)findViewById(R.id.passwd_saving_btn);
        passwd_hashing_btn = (ImageView)findViewById(R.id.passwd_hasing_btn);
        uri_content_btn = (ImageView)findViewById(R.id.uri_content_btn);

        passwd_saving_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),saving_passwd.class);
                startActivity(i);
            }
        });
        passwd_hashing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),hashing_pwd.class);
                startActivity(i);
            }
        });
        uri_content_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), content_uri.class);
                startActivity(i);
            }
        });

        
        Button button;
        button = (Button) findViewById(R.id.notify);

        
        getpermission();

        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Settings.canDrawOverlays(MainActivity.this)){
                    getpermission();

                }else {
                    
                    Intent intent = new Intent(MainActivity.this, FloatingWidgetShowService.class);
                    startService(intent);

                    
                    finish();

                }
            }
        });
    }

    
    public void getpermission(){
        
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)){
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName()));

            startActivityForResult(intent,1);

        }
    }

    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        
        if(requestCode == 1){
            
            if (!Settings.canDrawOverlays(MainActivity.this)){

                Toast.makeText(this, "Permission denied by user", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
