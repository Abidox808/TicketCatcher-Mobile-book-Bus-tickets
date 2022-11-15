package com.said.supra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class testActivity extends AppCompatActivity {
    ListView listVoyages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        listVoyages = findViewById(R.id.listViewRes);
        ArrayList<voyage> arr_voy = new ArrayList<voyage>();


        classVoyages c = new classVoyages(arr_voy);
        listVoyages.setAdapter(c);
    }
    class classVoyages extends BaseAdapter {
        public ArrayList<voyage> arr_voy = new ArrayList<voyage>();
        public classVoyages(ArrayList<voyage> arr_voy){
            this.arr_voy= arr_voy;
        }

        @Override
        public int getCount() {

            return arr_voy.size();
        }

        @Override
        public Object getItem(int i) {
            return arr_voy.get(i);
        }

        @Override
        public long getItemId(int i) {
            return arr_voy.get(i).price;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater li = getLayoutInflater();
            View ExternalView = li.inflate(R.layout.card_view_template,null);

            TextView depart= ExternalView.findViewById(R.id.depart);
            TextView arriver= ExternalView.findViewById(R.id.arriver);
            TextView departHour= ExternalView.findViewById(R.id.departHour);
            TextView arriverHour= ExternalView.findViewById(R.id.arriverHour);
            TextView price = ExternalView.findViewById(R.id.price);
            depart.setText(arr_voy.get(i).depart);
            arriver.setText(arr_voy.get(i).arriver);
            departHour.setText(arr_voy.get(i).departHour);
            arriverHour.setText(arr_voy.get(i).arriverHour);
            price.setText(Integer.toString(arr_voy.get(i).price));
            return ExternalView;
        }
    }
}