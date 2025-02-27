package com.example.tamesi_floating;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Search_Page extends AppCompatActivity {
    EditText etEmail;
    Button etResetPassword;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.page_search);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etResetPassword = (Button)findViewById(R.id.btnResetPassword);

        etResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = etEmail.getText().toString();
                Toast.makeText(getApplicationContext(), "Password reset link sent to email", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
