package com.said.supra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Myprofile extends AppCompatActivity {
    db my_db = new db(Myprofile.this,"supratour",null,1);
    EditText nom,prenom,email,pass,phone;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        SharedPreferences sp = getSharedPreferences("userLogin", MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        //checking if there is an actual session
        if(sp.getInt("UserID",0) == 0){
            switchActivitiesLogout();
        }

        nom=findViewById(R.id.infoNom);
        prenom=findViewById(R.id.infoPrenom);
        email=findViewById(R.id.infoEmail);
        pass = findViewById(R.id.infopass);
        phone = findViewById(R.id.infoPhone);
        btnUpdate = findViewById(R.id.btnUpdate);
        int id = sp.getInt("UserID",0);
        Cursor cur = my_db.getUser(id);
        nom.setText(cur.getString(cur.getColumnIndexOrThrow("nom")));
        prenom.setText(cur.getString(cur.getColumnIndexOrThrow("prenom")));
        email.setText(cur.getString(cur.getColumnIndexOrThrow("email")));
        pass.setText(cur.getString(cur.getColumnIndexOrThrow("password")));
        phone.setText(cur.getString(cur.getColumnIndexOrThrow("telephone")));
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id= sp.getInt("UserID",0);
                my_db.UpdateUsers(id,nom.getText().toString(),prenom.getText().toString(),email.getText().toString(),pass.getText().toString(),Integer.parseInt(phone.getText().toString()));
                Toast.makeText(Myprofile.this, "Your info has been updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void switchActivitiesLogout() {
        Intent switchActivityIntent = new Intent(this, loginActivity.class);
        startActivity((switchActivityIntent));
    }
}