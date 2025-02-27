package com.example.tamesi_floating;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.String;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DBHelper extends SQLiteOpenHelper {
    public static final int version = 6;

    public String hashing(String need_hash) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(need_hash.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest();
        String hash = String.format("%64x", new BigInteger(1, bytes));
        return hash;
    }
    public DBHelper(Context context){
        super(context,"db",null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase Db) {
        String pc = "CREATE TABLE IF NOT EXISTS PC(" + "Date CHAR(30)," + "site CHAR(30),"+"PC_id CHAR(30)," + "PC_passwd CHAR(30));";
        String member = "CREATE TABLE IF NOT EXISTS Member("+"id CHAR(30),"+"passwd CHAR(30),"+"email CHAR(30));";
        String pc_hashed = "CREATE TABLE IF NOT EXISTS HASHED(" + "Date CHAR(30)," + "site CHAR(30),"+"PC_id CHAR(30)," + "PC_passwd CHAR(150));";
        //String cuh = "CREATE TABLE IF NOT EXISTS URI_HISTORY("+"http://mjc.ac.kr"+"http://cyber.mjc.ac.kr"+"http://icampus.mjc.ac.kr"+"http://icampus.mjc.ac.kr"+"http://icampus.mjc.ac.kr"+"http://icampus.mjc.ac.kr"+"http://icampus.mjc.ac.kr"+"http://icampus.mjc.ac.kr"+"http://icampus.mjc.ac.kr"+"http://icampus.mjc.ac.kr"+"http://icampus.mjc.ac.kr"+"http://icampus.mjc.ac.kr"+"http://icampus.mjc.ac.kr";)";
        Db.execSQL(pc);
        Db.execSQL(member);
        Db.execSQL(pc_hashed);
        int iter =0;
        for(iter = 0; iter<20; iter++){
            Db.execSQL("INSERT INTO PC(Date,site,PC_id,PC_passwd) values ('202405"+Integer.parseInt("10")+iter+"','http://cyber.mjc.ac.kr','id"+Integer.parseInt("0")+iter+"','passwd"+Integer.parseInt("0")+iter+"');");
            Db.execSQL("INSERT INTO Member values ('id"+Integer.parseInt("0")+iter+"','passwd"+Integer.parseInt("0")+iter+"','mjc@mjc.ac.kr');");
            String str = hashing("passwd"+iter);
            Db.execSQL("INSERT INTO HASHED(Date,site,PC_id,PC_passwd) values ('202405"+Integer.parseInt("10")+iter+"','http://cyber.mjc.ac.kr','id"+Integer.parseInt("0")+iter+"','passwd:"+str+"');");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int i, int i1) {
        if(i1 == version){
            Db.execSQL("DROP TABLE PC;");
            Db.execSQL("DROP TABLE Member;");
            Db.execSQL("DROP TABLE HASHED;");
            onCreate(Db);
        }
    }


}