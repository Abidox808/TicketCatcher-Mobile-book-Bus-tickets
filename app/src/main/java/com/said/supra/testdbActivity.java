package com.said.supra;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class testdbActivity extends AppCompatActivity {
    database my_db = new database(this , "supra" , null , 1);
    //SessionManager sm = new SessionManager(testdbActivity.this);
    Button login,display;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText user;
        EditText pass;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdb);
        user =  findViewById(R.id.nom);
        pass =  findViewById(R.id.prenom);
        login = findViewById(R.id.add);
        display = findViewById(R.id.afficher);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean id=false;

                id = my_db.getSpecifiqueUser(user.getText().toString() , pass.getText().toString());

                if(id){
                   my_db.addAdmin(user.getText().toString() , pass.getText().toString());
                    Toast.makeText(testdbActivity.this,user.getText().toString() + " already registered" , Toast.LENGTH_LONG).show();
                    //sm.SaveSession(id);
                }else{
                    my_db.addAdmin(user.getText().toString() , pass.getText().toString());
                    Toast.makeText(testdbActivity.this,user.getText().toString() + " is a new user" , Toast.LENGTH_LONG).show();
                }
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = my_db.getadmins();
                Toast.makeText(testdbActivity.this, String.valueOf(val), Toast.LENGTH_LONG).show();
            }
        });
    }
}