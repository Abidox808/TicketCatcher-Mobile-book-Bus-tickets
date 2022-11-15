package com.said.supra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {
    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE IF NOT EXISTS admins (id INTEGER PRIMARY KEY AUTOINCREMENT , nom TEXT , prenom TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase my_db, int oldVersion, int newVersion) {
    onCreate(my_db);
    }
    void addAdmin(String n, String p ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom",n);
        cv.put("prenom",p);
        db.insert("admins",null,cv);
        db.close();
    }

    int getadmins(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cur = database.rawQuery("select * from admins", null);

        return cur.getCount();
    }


    public boolean getSpecifiqueUser(String email, String pass) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select id from admins where nom = ? AND prenom = ? " , new String[]{ email , pass});

        if(cur.getCount()>0){
            return true;
        }

        return false;
    }
    public ArrayList<String> Getusername(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select nom,prenom from users where id = " + id,null);
        ArrayList<String> user = new ArrayList<String>();
        cur.moveToFirst();
        user.add(cur.getString(cur.getColumnIndexOrThrow("nom"))+" "+cur.getString(cur.getColumnIndexOrThrow("prenom")));
        return user;
    }
}
