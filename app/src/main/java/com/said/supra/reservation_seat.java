package com.said.supra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class reservation_seat extends AppCompatActivity {
    ArrayList <Seats> images = new ArrayList <Seats>();
    int i ;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_seat);
        AddImagesData();
        tv = findViewById(R.id.SeatNumber);
        TextView time = findViewById(R.id.timeText);
        TextView autocar = findViewById(R.id.autocar);
        TextView destination = findViewById(R.id.destination);
        TextView price = findViewById(R.id.price);
        Bundle b2 = getIntent().getExtras();
        time.setText(b2.getString("time"));
        destination.setText(b2.getString("ville"));
        autocar.setText(b2.getString("autocar"));
        price.setText(b2.getString("price"));
        Button reserver = findViewById(R.id.reserver);

        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextintent = new Intent(reservation_seat.this,reservation_payment.class);
                Bundle b3 = new Bundle();
                b3.putString("autocar",b2.getString("autocar"));
                b3.putString("price",b2.getString("price"));
                b3.putString("price2",b2.getString("price2"));
                b3.putString("Hdepart",b2.getString("Hdepart"));
                b3.putString("date",b2.getString("date"));
                b3.putString("vdepart",b2.getString("Vdepart"));
                b3.putString("varriver",b2.getString("Varriver"));
                b3.putString("trajet","Voyage de "+b2.getString("Vdepart")+" Vers "+b2.getString("Varriver")+" le "+b2.getString("date")+" à "+b2.getString("Hdepart"));
                nextintent.putExtras(b3);
                startActivity(nextintent);
            }
        });
    }

    public void AddImagesData(){
        images.add(new Seats("R.id.s1","Booked"));
        images.add(new Seats("R.id.s2","Available"));
        images.add(new Seats("R.id.s3","Booked"));
        images.add(new Seats("R.id.s4","Available"));
        images.add(new Seats("R.id.s5","Available"));
        images.add(new Seats("R.id.s6","Booked"));
        images.add(new Seats("R.id.s7","Booked"));
        images.add(new Seats("R.id.s8","Available"));
        images.add(new Seats("R.id.s9","Available"));
        images.add(new Seats("R.id.s10","Available"));
        images.add(new Seats("R.id.s11","Available"));
        images.add(new Seats("R.id.s12","Booked"));
        images.add(new Seats("R.id.s13","Available"));
        images.add(new Seats("R.id.s14","Booked"));
        images.add(new Seats("R.id.s15","Available"));
        images.add(new Seats("R.id.s16","Available"));
        images.add(new Seats("R.id.s17","Booked"));
        images.add(new Seats("R.id.s18","Booked"));
        images.add(new Seats("R.id.s19","Booked"));
        images.add(new Seats("R.id.s20","Available"));
        images.add(new Seats("R.id.s21","Available"));
        images.add(new Seats("R.id.s22","Available"));
        images.add(new Seats("R.id.s23","Booked"));
        images.add(new Seats("R.id.s24","Available"));
        images.add(new Seats("R.id.s25","Booked"));
        images.add(new Seats("R.id.s26","Available"));
        images.add(new Seats("R.id.s27","Available"));
        images.add(new Seats("R.id.s28","Booked"));
        images.add(new Seats("R.id.s29","Available"));
        images.add(new Seats("R.id.s30","Available"));
        images.add(new Seats("R.id.s31","Booked"));
        images.add(new Seats("R.id.s32","Booked"));
        images.add(new Seats("R.id.s33","Booked"));
        images.add(new Seats("R.id.s34","Available"));
        images.add(new Seats("R.id.s35","Booked"));
        images.add(new Seats("R.id.s36","Available"));
    }
    ImageView v;
    public void ImageClicked(View view) {
        v = findViewById(view.getId());
        switch(view.getId()){
            case R.id.s1:
                tv.setText("s1");
                if(images.get(0).status == "Booked"){
                    break;
                }
                if(images.get(0).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(0).status = "Your Seat";
                }else if(images.get(0).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(0).status = "Available";
                }
                break;
            case R.id.s2:
                tv.setText("s2");
                if(images.get(1).status == "Booked"){
                    break;
                }
                if(images.get(1).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(1).status = "Your Seat";
                }else if(images.get(1).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(1).status = "Available";
                }
                break;
            case R.id.s3:
                tv.setText("s3");
                if(images.get(2).status == "Booked"){
                    break;
                }
                if(images.get(2).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(2).status = "Your Seat";
                }else if(images.get(2).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(2).status = "Available";
                }
                break;
            case R.id.s4:
                tv.setText("s4");
                if(images.get(3).status == "Booked"){
                    break;
                }
                if(images.get(3).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(3).status = "Your Seat";
                }else if(images.get(3).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(3).status = "Available";
                }
                break;
            case R.id.s5:
                tv.setText("s5");
                if(images.get(4).status == "Booked"){
                    break;
                }
                if(images.get(4).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(4).status = "Your Seat";
                }else if(images.get(4).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(4).status = "Available";
                }
                break;
            case R.id.s6:
                tv.setText("s6");
                if(images.get(5).status == "Booked"){
                    break;
                }
                if(images.get(5).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(5).status = "Your Seat";
                }else if(images.get(5).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(5).status = "Available";
                }
                break;
            case R.id.s7:
                tv.setText("s4");
                if(images.get(6).status == "Booked"){
                    break;
                }
                if(images.get(6).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(6).status = "Your Seat";
                }else if(images.get(6).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(6).status = "Available";
                }
                break;
            case R.id.s8:
                tv.setText("s8");
                if(images.get(7).status == "Booked"){
                    break;
                }
                if(images.get(7).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(7).status = "Your Seat";
                }else if(images.get(7).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(7).status = "Available";
                }
                break;
            case R.id.s9:
                tv.setText("s9");
                if(images.get(8).status == "Booked"){
                    break;
                }
                if(images.get(8).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(8).status = "Your Seat";
                }else if(images.get(8).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(8).status = "Available";
                }
                break;
            case R.id.s10:
                tv.setText("s10");
                if(images.get(9).status == "Booked"){
                    break;
                }
                if(images.get(9).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(9).status = "Your Seat";
                }else if(images.get(9).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(9).status = "Available";
                }
                break;
            case R.id.s11:
                tv.setText("s11");
                if(images.get(10).status == "Booked"){
                    break;
                }
                if(images.get(10).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(10).status = "Your Seat";
                }else if(images.get(10).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(10).status = "Available";
                }
                break;
            case R.id.s12:
                tv.setText("s12");
                if(images.get(11).status == "Booked"){
                    break;
                }
                if(images.get(11).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(11).status = "Your Seat";
                }else if(images.get(11).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(11).status = "Available";
                }
                break;
            case R.id.s13:
                tv.setText("s13");
                if(images.get(12).status == "Booked"){
                    break;
                }
                if(images.get(12).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(12).status = "Your Seat";
                }else if(images.get(12).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(12).status = "Available";
                }
                break;
            case R.id.s14:
                tv.setText("s14");
                if(images.get(13).status == "Booked"){
                    break;
                }
                if(images.get(13).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(13).status = "Your Seat";
                }else if(images.get(13).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(13).status = "Available";
                }
                break;
            case R.id.s15:
                tv.setText("s15");
                if(images.get(14).status == "Booked"){
                    break;
                }
                if(images.get(14).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(14).status = "Your Seat";
                }else if(images.get(14).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(14).status = "Available";
                }
                break;

            case R.id.s16:
                tv.setText("s16");
                if(images.get(15).status == "Booked"){
                    break;
                }
                if(images.get(15).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(15).status = "Your Seat";
                }else if(images.get(15).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(15).status = "Available";
                }
                break;
            case R.id.s17:
                tv.setText("s17");
                if(images.get(16).status == "Booked"){
                    break;
                }
                if(images.get(16).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(16).status = "Your Seat";
                }else if(images.get(16).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(16).status = "Available";
                }
                break;
            case R.id.s18:
                tv.setText("s18");
                if(images.get(17).status == "Booked"){
                    break;
                }
                if(images.get(17).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(17).status = "Your Seat";
                }else if(images.get(17).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(17).status = "Available";
                }
                break;
            case R.id.s19:
                tv.setText("s19");
                if(images.get(18).status == "Booked"){
                    break;
                }
                if(images.get(18).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(18).status = "Your Seat";
                }else if(images.get(18).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(18).status = "Available";
                }
                break;
            case R.id.s20:
                tv.setText("s20");
                if(images.get(19).status == "Booked"){
                    break;
                }
                if(images.get(19).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(19).status = "Your Seat";
                }else if(images.get(19).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(19).status = "Available";
                }
                break;
            case R.id.s21:
                tv.setText("s21");
                if(images.get(20).status == "Booked"){
                    break;
                }
                if(images.get(20).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(20).status = "Your Seat";
                }else if(images.get(20).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(20).status = "Available";
                }
                break;
            case R.id.s22:
                tv.setText("s22");
                if(images.get(21).status == "Booked"){
                    break;
                }
                if(images.get(21).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(21).status = "Your Seat";
                }else if(images.get(21).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(21).status = "Available";
                }
                break;
            case R.id.s23:
                tv.setText("s23");
                if(images.get(22).status == "Booked"){
                    break;
                }
                if(images.get(22).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(22).status = "Your Seat";
                }else if(images.get(22).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(22).status = "Available";
                }
                break;
            case R.id.s24:
                tv.setText("s24");
                if(images.get(23).status == "Booked"){
                    break;
                }
                if(images.get(23).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(23).status = "Your Seat";
                }else if(images.get(23).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(23).status = "Available";
                }
                break;
            case R.id.s25:
                tv.setText("s25");
                if(images.get(24).status == "Booked"){
                    break;
                }
                if(images.get(24).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(24).status = "Your Seat";
                }else if(images.get(24).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(24).status = "Available";
                }
                break;
            case R.id.s26:
                tv.setText("s26");
                if(images.get(25).status == "Booked"){
                    break;
                }
                if(images.get(25).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(25).status = "Your Seat";
                }else if(images.get(25).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(25).status = "Available";
                }
                break;
            case R.id.s27:
                tv.setText("s27");
                if(images.get(26).status == "Booked"){
                    break;
                }
                if(images.get(26).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(26).status = "Your Seat";
                }else if(images.get(26).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(26).status = "Available";
                }
                break;
            case R.id.s28:
                tv.setText("s28");
                if(images.get(27).status == "Booked"){
                    break;
                }
                if(images.get(27).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(27).status = "Your Seat";
                }else if(images.get(27).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(27).status = "Available";
                }
                break;
            case R.id.s29:
                tv.setText("s29");
                if(images.get(28).status == "Booked"){
                    break;
                }
                if(images.get(28).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(28).status = "Your Seat";
                }else if(images.get(28).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(28).status = "Available";
                }
                break;
            case R.id.s30:
                tv.setText("s30");
                if(images.get(29).status == "Booked"){
                    break;
                }
                if(images.get(29).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(29).status = "Your Seat";
                }else if(images.get(29).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(29).status = "Available";
                }
                break;
            case R.id.s31:
                tv.setText("s31");
                if(images.get(30).status == "Booked"){
                    break;
                }
                if(images.get(30).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(30).status = "Your Seat";
                }else if(images.get(30).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(30).status = "Available";
                }
                break;
            case R.id.s32:
                tv.setText("s32");
                if(images.get(31).status == "Booked"){
                    break;
                }
                if(images.get(31).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(31).status = "Your Seat";
                }else if(images.get(31).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(31).status = "Available";
                }
                break;
            case R.id.s33:
                tv.setText("s33");
                if(images.get(32).status == "Booked"){
                    break;
                }
                if(images.get(32).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(32).status = "Your Seat";
                }else if(images.get(32).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(32).status = "Available";
                }
                break;
            case R.id.s34:
                tv.setText("s34");
                if(images.get(33).status == "Booked"){
                    break;
                }
                if(images.get(33).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(33).status = "Your Seat";
                }else if(images.get(33).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(33).status = "Available";
                }
                break;
            case R.id.s35:
                tv.setText("s35");
                if(images.get(34).status == "Booked"){
                    break;
                }
                if(images.get(34).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(34).status = "Your Seat";
                }else if(images.get(34).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(34).status = "Available";
                }
                break;
            case R.id.s36:
                tv.setText("s36");
                if(images.get(35).status == "Booked"){
                    break;
                }
                if(images.get(35).status=="Available"){
                    v.setImageResource(R.drawable.your_seat_img);
                    images.get(35).status = "Your Seat";
                }else if(images.get(35).status=="Your Seat"){
                    v.setImageResource(R.drawable.available_img);
                    images.get(35).status = "Available";
                }
                break;
            default:
        }

    }
}