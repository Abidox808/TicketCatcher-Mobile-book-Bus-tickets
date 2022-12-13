package com.said.supra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {

    TextView loginSignup;
    EditText user, pass;
    db my_db = new db(loginActivity.this, "supratour", null, 1);

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("userLogin", MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        setContentView(R.layout.activity_login);
        loginSignup = findViewById(R.id.loginSignup);
        user = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean test;
                test = my_db.getSpecifiqueUser(user.getText().toString() , pass.getText().toString());

                if(test == true){
                    Toast.makeText(loginActivity.this," Login Successfull" , Toast.LENGTH_LONG).show();
                    spe.putInt("UserID",my_db.getUserid(user.getText().toString() , pass.getText().toString()));
                    spe.apply();
                    switchActivities2();
                }else{
                    Toast.makeText(loginActivity.this," Some of your info is wrong, try again" , Toast.LENGTH_LONG).show();
                }
            }
        });
        loginSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });

    }
    private void switchActivities2(){
        Intent switchActivityIntent = new Intent(this, NavDrawerActivity.class);
        startActivity((switchActivityIntent));
        finish();
    }
    private void switchActivities(){
        Intent switchActivityIntent = new Intent(loginActivity.this, signupActivity.class);
        startActivity((switchActivityIntent));
    }
}