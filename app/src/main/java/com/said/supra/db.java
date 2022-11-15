package com.said.supra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class db extends SQLiteOpenHelper {
    public db(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , nom TEXT, prenom TEXT, email TEXT , password TEXT , telephone INTEGER )");
    db.execSQL("CREATE TABLE IF NOT EXISTS voyage (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , nomCar TEXT, villeDepart TEXT, villeArriver TEXT, hourDepart TEXT, hourArriver TEXT, price INTEGER)");
    db.execSQL("CREATE TABLE IF NOT EXISTS tickets (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , nomCar TEXT, villeDepart TEXT, villeArriver TEXT, dateReservation TEXT ,hourReservation TEXT, price TEXT)");
    AddBunchOfData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }


    void addUsers(String nom, String prenom , String email , String password ,Integer telephone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom",nom);
        cv.put("prenom",prenom);
        cv.put("email",email);
        cv.put("password",password);
        cv.put("telephone",telephone);
        db.insert("users",null,cv);
        db.close();
    }

    void UpdateUsers(int id,String nom, String prenom , String email , String password ,Integer telephone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom",nom);
        cv.put("prenom",prenom);
        cv.put("email",email);
        cv.put("password",password);
        cv.put("telephone",telephone);
        db.update("users",cv,"id = ?",new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor getUser(int id ){
        String Sid = String.valueOf(id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select nom,prenom,email,password,telephone from users where id = ? " , new String[]{Sid});
        cur.moveToFirst();
        return cur;
    }

    public boolean getSpecifiqueUser(String email, String pass) {
        boolean isRegistered;
        SQLiteDatabase my_db = this.getReadableDatabase();
        Cursor cur = my_db.rawQuery("select id from users where email = ? AND password = ? " , new String[]{ email , pass});

        if(cur.getCount()>0){
            isRegistered=true;
        }else{
            isRegistered=false;
        }
        my_db.close();
        return isRegistered;
    }


    public int getUserid(String email , String password){
        SQLiteDatabase my_db = this.getReadableDatabase();
        Cursor cur = my_db.rawQuery("select id from users where email = ? AND password = ? " , new String[]{ email , password});
        int id;
        cur.moveToFirst();
        id= cur.getInt(cur.getColumnIndexOrThrow("id"));
        return id;
    }
    public String Getusername(int id){
        String user;
        String name;
        String username;
        String Sid = String.valueOf(id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select nom,prenom from users where id = ? " , new String[]{Sid});
        cur.moveToFirst();
        user = cur.getString(cur.getColumnIndexOrThrow("nom"));
        name = cur.getString(cur.getColumnIndexOrThrow("prenom"));
        username = user + " " + name;

        return username;
    }
    //voyage (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , nomCar TEXT, villeDepart TEXT, villeArriver TEXT, hourDepart TEXT, hourArriver TEXT, price INTEGER
    public void AddVoyageData(SQLiteDatabase db ,String nomCar, String villeDepart , String villeArriver , String hourDepart ,String hourArriver, int price){
            ContentValues cv = new ContentValues();
            cv.put("nomCar",nomCar);
            cv.put("villeDepart",villeDepart);
            cv.put("villeArriver",villeArriver);
            cv.put("hourDepart",hourDepart);
            cv.put("hourArriver",hourArriver);
            cv.put("price",price);
            db.insert("voyage",null,cv);
    }

    public ArrayList<voyage> GetVoyageData(String Depart, String Arriver) {
       int count;
        voyage vo;
        ArrayList<voyage> v = new ArrayList<voyage>();
        SQLiteDatabase my_db = this.getReadableDatabase();
        Cursor cur = my_db.rawQuery("select nomCar,villeDepart,villeArriver,hourDepart,hourArriver,price from voyage where villeDepart = ? AND villeArriver = ? " , new String[]{ Depart , Arriver});
        count = cur.getCount();
        cur.moveToFirst();
        for(int i=0;i<count;i++){
             vo = new voyage(cur.getString(cur.getColumnIndexOrThrow("nomCar")),cur.getString(cur.getColumnIndexOrThrow("villeDepart"))
                    ,cur.getString(cur.getColumnIndexOrThrow("villeArriver")),cur.getString(cur.getColumnIndexOrThrow("hourDepart"))
                    ,cur.getString(cur.getColumnIndexOrThrow("hourArriver")),cur.getInt(cur.getColumnIndexOrThrow("price")));
             v.add(vo);
            cur.moveToNext();
        }

        return v;
    }

    public String GetEmail(int id){
        String email;
        String Sid = String.valueOf(id);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("select email from users where id = ? " , new String[]{Sid});
        cur.moveToFirst();
        email = cur.getString(cur.getColumnIndexOrThrow("email"));
        return email;
    }
    public void SaveTicket(String car , String depart , String arriver , String date , String hourReserv , String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nomCar",car);
        cv.put("villeDepart",depart);
        cv.put("villeArriver",arriver);
        cv.put("dateReservation",date);
        cv.put("hourReservation",hourReserv);
        cv.put("price",price);
        db.insert("tickets",null,cv);
        db.close();
    }
    public ArrayList<ticket> GetTickets(int id){
        String Sid = String.valueOf(id);
        int count;
        ticket tick;
        ArrayList<ticket> arr_ticket = new ArrayList<ticket>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * from tickets",null);
        count = cur.getCount();
        cur.moveToFirst();

        for(int i=0;i<count;i++){
            tick = new ticket(cur.getString(cur.getColumnIndexOrThrow("nomCar")),cur.getString(cur.getColumnIndexOrThrow("villeDepart"))
                    ,cur.getString(cur.getColumnIndexOrThrow("villeArriver")),cur.getString(cur.getColumnIndexOrThrow("hourReservation"))
                    ,cur.getString(cur.getColumnIndexOrThrow("dateReservation")),cur.getString(cur.getColumnIndexOrThrow("price")),cur.getInt(cur.getColumnIndexOrThrow("id")));

            arr_ticket.add(tick);
            cur.moveToNext();
        }

        return arr_ticket;
    }

    public void AddBunchOfData(SQLiteDatabase db){
        // agadir ------ > others
        AddVoyageData(db,"GLOBUS","Agadir","Casablanca","07:00","14:30",120);
        AddVoyageData(db,"CTM","Agadir","Casablanca","09:30","16:45",225);
        AddVoyageData(db,"ITRANE SOUSS","Agadir","Casablanca","13:00","20:00",120);
        AddVoyageData(db,"JAOUHARAT AGADIR","Agadir","Casablanca","14:30","22:00",120);
        AddVoyageData(db,"JANA VIAJES","Agadir","Casablanca","17:50","02:40",120);
        AddVoyageData(db,"SUPRATOURS","Agadir","Marrakech","05:45","09:30",120);
        AddVoyageData(db,"CHARAF BUS","Agadir","Marrakech","07:30","11:00",80);
        AddVoyageData(db,"ITRANE VOYAGE","Agadir","Marrakech","08:15","12:15",80);
        AddVoyageData(db,"CTM","Agadir","Marrakech","09:30","13:00",120);
        AddVoyageData(db,"ITRANE SOUSS","Agadir","Marrakech","13:00","17:00",60);
        AddVoyageData(db,"GLOBUS","Agadir","Rabat","12:30","12:30",150);
        AddVoyageData(db,"ITRANE SOUSS","Agadir","Rabat","13:00","21:00",130);
        AddVoyageData(db,"JAOUHARAT AGADIR","Agadir","Rabat","14:30","23:00",130);
        AddVoyageData(db,"JANA VIAJES","Agadir","Rabat","17:50","04:00",120);
        AddVoyageData(db,"ISMAILIA CAR","Agadir","Rabat","22:00","06:00",140);
        AddVoyageData(db,"TASSAOUT","Agadir","Tanger","22:30","10:45",200);
        AddVoyageData(db,"JANA VIAJES","Agadir","Tanger","23:30","10:30",200);
        // Casablanca ------ > others
        AddVoyageData(db,"ITRANE VOYAGE","Casablanca","Agadir","01:00","07:00",120);
        AddVoyageData(db,"TRS AL YAMAMA","Casablanca","Agadir","05:00","15:30",150);
        AddVoyageData(db,"GLOBUS","Casablanca","Agadir","06:00","14:30",130);
        AddVoyageData(db,"JAOUHARAT AGADIR","Casablanca","Agadir","09:45","18:00",120);
        AddVoyageData(db,"CTM","Casablanca","Agadir","12:00","20:30",225);
        AddVoyageData(db,"TRANS GHAZALA","Casablanca","Marrakech","06:00","09:30",80);
        AddVoyageData(db,"CTM","Casablanca","Marrakech","08:30","12:00",105);
        AddVoyageData(db,"ITRANE VOYAGE","Casablanca","Marrakech","08:45","12:00",59);
        AddVoyageData(db,"JAOUHARAT AGADIR","Casablanca","Marrakech","09:45","13:30",60);
        AddVoyageData(db,"STNR","Casablanca","Marrakech","12:30","16:00",60);
        AddVoyageData(db,"TRANS GHAZALA","Casablanca","Rabat","01:00","02:00",20);
        AddVoyageData(db,"CTM","Casablanca","Rabat","07:00","08:20",45);
        AddVoyageData(db,"TRS AL YAMAMA","Casablanca","Rabat","16:45","18:15",20);
        AddVoyageData(db,"TRANS AL HAMD","Casablanca","Rabat","17:30","19:00",20);
        AddVoyageData(db,"SAT","Casablanca","Rabat","18:15","19:30",30);
        AddVoyageData(db,"AL WISSAM ADDAHABI","Casablanca","Tanger","09:00","15:30",100);
        AddVoyageData(db,"CTM","Casablanca","Tanger","17:00","22:30",90);
        AddVoyageData(db,"TRANS AL HAMD","Casablanca","Tanger","17:30","22:30",80);
        // Marrakech ------ > others
        AddVoyageData(db,"ITRANE VOYAGE","Marrakech","Agadir","08:30","12:30",70);
        AddVoyageData(db,"CTM","Marrakech","Agadir","12:30","15:45",120);
        AddVoyageData(db,"CHARAF BUS","Marrakech","Agadir","13:30","17:00",80);
        AddVoyageData(db,"JAOUHARAT AGADIR","Marrakech","Agadir","13:30","18:00",60);
        AddVoyageData(db,"STNR","Marrakech","Agadir","16:00","20:15",60);
        AddVoyageData(db,"CTM","Marrakech","Casablanca","04:00","07:30",105);
        AddVoyageData(db,"EL BACHIRI","Marrakech","Casablanca","07:35","11:00",79);
        AddVoyageData(db,"PRESTIGE REDA","Marrakech","Casablanca","08:40","12:25",80);
        AddVoyageData(db,"TRANS GHAZALA","Marrakech","Casablanca","12:30","16:00",79);
        AddVoyageData(db,"ITRANE SOUSS","Marrakech","Casablanca","17:00","20:00",60);
        AddVoyageData(db,"CTM","Marrakech","Rabat","04:00","10:00",150);
        AddVoyageData(db,"EL BACHIRI","Marrakech","Rabat","07:35","12:40",99);
        AddVoyageData(db,"PRESTIGE REDA","Marrakech","Rabat","08:40","13:40",100);
        AddVoyageData(db,"TRANS GHAZALA","Marrakech","Rabat","12:30","17:45",99);
        AddVoyageData(db,"ITRANE SOUSS","Marrakech","Rabat","17:00","21:00",80);
        AddVoyageData(db,"CTM","Marrakech","Tanger","13:15","22:30",195);
        // Rabat ------ > others
        AddVoyageData(db,"CTM","Rabat","Agadir","04:45","15:45",295);
        AddVoyageData(db,"JAOUHARAT AGADIR","Rabat","Agadir","08:30","18:00",130);
        AddVoyageData(db,"GLOBUS","Rabat","Agadir","09:00","18:45",140);
        AddVoyageData(db,"STNR","Rabat","Agadir","11:15","20:15",130);
        AddVoyageData(db,"JANA VIAJES","Rabat","Agadir","12:00","21:00",140);
        AddVoyageData(db,"TRANS GHAZALA","Rabat","Casablanca","04:30","05:30",29);
        AddVoyageData(db,"CTM","Rabat","Casablanca","04:45","06:15",45);
        AddVoyageData(db,"LIBRA TOURS","Rabat","Casablanca","06:30","07:30",20);
        AddVoyageData(db,"TRANS ANNAMIR","Rabat","Casablanca","07:15","08:30",20);
        AddVoyageData(db,"MOURIH NAJAT STMN","Rabat","Casablanca","07:30","09:00",20);
        AddVoyageData(db,"TRANS GHAZALA","Rabat","Marrakech","04:30","09:30",100);
        AddVoyageData(db,"CTM","Rabat","Marrakech","04:45","12:00",150);
        AddVoyageData(db,"JAOUHARAT AGADIR","Rabat","Marrakech","08:30","13:30",180);
        AddVoyageData(db,"STNR","Rabat","Marrakech","11:15","16:00",80);
        AddVoyageData(db,"JANA VIAJES","Rabat","Marrakech","12:00","17:00",80);
        AddVoyageData(db,"AL WISSAM ADDAHABI","Rabat","Tanger","10:15","15:30",80);
        AddVoyageData(db,"CTM","Rabat","Tanger","18:30","22:30",80);
        AddVoyageData(db,"TRANS AL HAMD","Rabat","Tanger","19:00","22:30",70);
        AddVoyageData(db,"TASSAOUT","Tanger","Agadir","16:20","06:45",200);
        AddVoyageData(db,"JANA VIAJES","Tanger","Agadir","21:00","08:00",200);
        // Tanger ------ > others
        AddVoyageData(db,"CTM","Tanger","Casablanca","07:30","13:30",90);
        AddVoyageData(db,"TASSAOUT","Tanger","Casablanca","16:20","22:15",80);
        AddVoyageData(db,"AL WISSAM ADDAHABI","Tanger","Casablanca","19:50","01:50",100);
        AddVoyageData(db,"CTM","Tanger","Rabat","07:30","07:30",80);
        AddVoyageData(db,"TASSAOUT","Tanger","Rabat","16:20","20:30",70);
        AddVoyageData(db,"AL WISSAM ADDAHABI","Tanger","Rabat","19:50","19:50",80);
        AddVoyageData(db,"JANA VIAJES","Tanger","Rabat","21:00","00:15",70);
    }
}
