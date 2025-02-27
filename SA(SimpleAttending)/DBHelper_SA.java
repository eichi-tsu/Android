package com.example.main_page;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.security.PublicKey;


public class DBHelper extends SQLiteOpenHelper {
    public static final int version = 6;
    public DBHelper(Context context){
        super(context,"db",null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase Db) {
       String sql = "CREATE TABLE IF NOT EXISTS member(" + "name CHAR(30)," + "password CHAR(30)," + "Prof_Add TEXT);";
       String sql2 = "CREATE TABLE IF NOT EXISTS check_member("+"name CHAR(30),"+"Date CHAR(30),"+"Time CHAR(30),"+"State CHAR(30));";
       Db.execSQL(sql);
       Db.execSQL(sql2);
       Db.execSQL("INSERT INTO member (name, password, Prof_Add) values ('Sagiri','Em','01012345679');");
       Db.execSQL("INSERT INTO member (name, password, Prof_Add) values ('Yamada','Em','01012345678');");
       Db.execSQL("INSERT INTO member (name, password, Prof_Add) values ('Masamune','Em','01012345670');");


       Db.execSQL("INSERT INTO check_member values ('Sagiri','2021-12-09','10:00','출석');");
       Db.execSQL("INSERT INTO check_member values ('Yamada','2021-12-10','10:05','지각');");
       Db.execSQL("INSERT INTO check_member values ('Masamune','2021-12-11','09:45','출석');");
       Db.execSQL("INSERT INTO check_member values ('Sagiri','2021-12-12','10:00','출석');");
       Db.execSQL("INSERT INTO check_member values ('Sagiri','2021-12-13','10:00','출석');");
       Db.execSQL("INSERT INTO check_member values ('Sagiri','2021-12-14','10:00','출석');");
       Db.execSQL("INSERT INTO check_member values ('Yamada','2021-12-15','10:00','결석');");
       Db.execSQL("INSERT INTO check_member values ('Yamada','2021-12-16','12:03','지각');");
       Db.execSQL("INSERT INTO check_member values ('Yamada','2021-12-17',null,'결석');");
       Db.execSQL("INSERT INTO check_member values ('Masamune','2021-12-18','10:00','출석');");
       Db.execSQL("INSERT INTO check_member values ('Sagiri','2021-12-19','10:00','출석');");
       Db.execSQL("INSERT INTO check_member values ('Sagiri','2021-12-20','10:00','출석');");
       Db.execSQL("INSERT INTO check_member values ('Sagiri','2021-12-21','10:00','출석');");
       Db.execSQL("INSERT INTO check_member values ('Sagiri','2021-12-22','10:00','출석');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int i, int i1) {
        if(i1 == version){
            Db.execSQL("DROP TABLE member;");
            Db.execSQL("DROP TABLE check_member;");
            onCreate(Db);
        }
    }
}
