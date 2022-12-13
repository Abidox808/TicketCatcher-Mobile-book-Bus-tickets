package com.said.supra;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class reservation_payment extends AppCompatActivity {
    db my_db = new db(reservation_payment.this, "supratour", null, 1);
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_payment);
        Bundle b = getIntent().getExtras();
        TextView car = findViewById(R.id.autocar);
        TextView depart = findViewById(R.id.depart);
        TextView price = findViewById(R.id.price);
        TextView date = findViewById(R.id.date);
        TextView trajet = findViewById(R.id.trajet);
        TextView email = findViewById(R.id.email);
        Button valider = findViewById(R.id.valider);

        SharedPreferences sp = getSharedPreferences("userLogin", MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();

        email.setText(my_db.GetEmail(sp.getInt("UserID",0)));
        car.setText(b.getString("autocar"));
        date.setText(b.getString("date"));
        price.setText(b.getString("price"));
        trajet.setText(b.getString("trajet"));
        depart.setText(b.getString("Hdepart"));

        CheckBox chk = findViewById(R.id.checkBox);
        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    valider.setEnabled(true);
                }else if(!b){
                    valider.setEnabled(false);
                }
            }
        });

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.confirmation_box);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button Okay = dialog.findViewById(R.id.btn_okay);

        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent back = new Intent(reservation_payment.this , NavDrawerActivity.class);
                startActivity(back);
                finish();
                dialog.dismiss();
            }
        });
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(valider.isEnabled() == true){
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                    Date date = new Date();
                    String dateformatted = dateFormat.format(date);
                    my_db.SaveTicket(b.getString("autocar"),b.getString("vdepart"),b.getString("varriver"),
                            b.getString("date"),dateformatted,b.getString("price2"));
                    dialog.show();
                }
            }
        });
    }
}