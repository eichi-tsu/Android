package com.example.main_page;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText etid,etpasswd;
    Button nextbtn;
    SQLiteDatabase db;
    DBHelper dh;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("로그인");
        etid = (EditText)findViewById(R.id.etid);
        etpasswd = (EditText)findViewById(R.id.etpasswd);
        dh = new DBHelper(getApplicationContext());
        db = dh.getReadableDatabase();
        nextbtn = (Button)findViewById(R.id.nextbtn);
        c = db.rawQuery("select * from member;",null);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                while (c.moveToNext()) {
                    if (etid.getText().toString().equals(c.getString(0)) && etpasswd.getText().toString().equals(c.getString(1))) {
                        String s = c.getString(0).toString();
                        AlertDialog.Builder ad = new AlertDialog.Builder(Login.this);
                        ad.setTitle("information");
                        ad.setMessage("success login");
                        ad.show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("id",s);
                        startActivity(i);
                    }
                    /*else{
                        AlertDialog.Builder ad2 = new AlertDialog.Builder(Login.this);
                        ad2.setTitle("information");
                        ad2.setMessage("failure login");
                        ad2.show();
                    }*/
                }
                c.close();
                dh.close();
            }
        });
    }
}