package com.example.main_page;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.Vector;

public class InputRadio extends Fragment {
    EditText input_reason;
    AutoCompleteTextView input_address;
    Button msgbtn;
    SQLiteDatabase db;
    DBHelper dh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_input_radio, container, false);
        input_address = (AutoCompleteTextView) v.findViewById(R.id.inputaddress);
        input_reason = (EditText) v.findViewById(R.id.check_addreess);
        msgbtn =(Button)v.findViewById(R.id.msgbtn2);
        Vector<String> vector = new Vector<String>();
        vector.removeAllElements();

        dh = new DBHelper(getContext());
        db = dh.getReadableDatabase();
        Cursor c = db.rawQuery("select Prof_Add from member",null);
        while(c.moveToNext()){
            vector.add(c.getString(0).toString());
        }
        db.close();
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,vector);
        input_address.setAdapter(aa);
        msgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse( "smsto:"+input_address.getText().toString() ) );
                i.putExtra("sms_body",input_reason.getText().toString()+"로 인해 결석입니다.");
                startActivity(i);
            }
        });
        return v;
    }
}