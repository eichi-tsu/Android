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

        // 버튼 정의
        Button button;
        button = (Button) findViewById(R.id.notify);

        // 권한을 확인한다.
        getpermission();

        // 버튼 클릭
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Settings.canDrawOverlays(MainActivity.this)){
                    getpermission();

                }else {
                    // 권한이 설정돼있으면 위젯 액티비티로 연결
                    Intent intent = new Intent(MainActivity.this, FloatingWidgetShowService.class);
                    startService(intent);

                    // 액티비티에 연결 후 MainActivity 종료
                    finish();

                }
            }
        });
    }

    // M 버전(안드로이드 6.0 마시멜로우 버전) 보다 같거나 큰 API에서만 설정창 이동 가능
    public void getpermission(){
        // 지금 창이 오버레이 설정창이 아니라면
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)){
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName()));

            startActivityForResult(intent,1);

        }
    }

    // WidgetService 에서 처리된 결과를 받는 메소드
    // 처리된 결과 코드가 requestCode 를 판별해 결과 처리를 진행한다.
    // WidgetService 에서 처리 결과가 담겨온 데이터를 메시지로 보여준다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 권한 여부 확인
        if(requestCode == 1){
            // 권한을 사용할 수없는 경우 알림 표시
            if (!Settings.canDrawOverlays(MainActivity.this)){

                Toast.makeText(this, "Permission denied by user", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
