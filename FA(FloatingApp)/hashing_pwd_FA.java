package com.example.tamesi_floating;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class hashing_pwd extends AppCompatActivity {
    TextView input_factor;
    TextView hashed_string;
    Button need_hash_btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwd_hasing);

        input_factor = (TextView) findViewById(R.id.hpasswd_input);
        String need_hash = input_factor.getText().toString();
        Button hash_need_btn = (Button)findViewById(R.id.hash_need_btn);

        hash_need_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDigest md = null;
                try {
                    md = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                md.update(need_hash.getBytes(StandardCharsets.UTF_8));
                byte[] bytes = md.digest();
                String hash = String.format("%64x", new BigInteger(1, bytes));
                hashed_string = (TextView)findViewById(R.id.hashed_string);
                hashed_string.setText(hash);
            }
        });



    }
}
