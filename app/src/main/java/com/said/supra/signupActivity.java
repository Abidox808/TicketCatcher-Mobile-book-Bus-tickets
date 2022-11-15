package com.said.supra;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signupActivity extends AppCompatActivity {
    TextView LoginBack;
    EditText nom,prenom,email,pass,phone;
    Button signupp;
    db newdb= new db(this,"supratour", null , 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        EditText nom,prenom,email,pass,phone;
        Button signup;
        nom=findViewById(R.id.editNom);
        prenom=findViewById(R.id.editPrenom);
        email=findViewById(R.id.editEmail);
        pass=findViewById(R.id.editPass);
        phone=findViewById(R.id.editPhone);


        LoginBack = findViewById(R.id.LoginBack);
        LoginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signupp = findViewById(R.id.btnsignup);
        signupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newdb.addUsers(nom.getText().toString(),prenom.getText().toString(),email.getText().toString(),pass.getText().toString(),Integer.parseInt(phone.getText().toString()));
                Toast.makeText(signupActivity.this, "You've just Signed up, Go back and Login", Toast.LENGTH_SHORT).show();
            }
        });
    }

}