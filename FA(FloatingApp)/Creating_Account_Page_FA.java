package com.example.tamesi_floating;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Creating_Account_Page extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;
    Button btnSignUp;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.creating_account_page);


        etEmail = (EditText) findViewById(R.id.etEmail) ;
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
    }
}
