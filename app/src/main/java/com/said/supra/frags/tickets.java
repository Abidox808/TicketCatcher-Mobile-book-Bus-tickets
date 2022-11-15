package com.said.supra.frags;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.said.supra.R;
import com.said.supra.db;
import com.said.supra.ticket;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tickets#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tickets extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tickets() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tickets.
     */
    // TODO: Rename and change types and number of parameters
    public static tickets newInstance(String param1, String param2) {
        tickets fragment = new tickets();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    CardView cardReserv;
    ArrayList<ticket> arr_tickets = new ArrayList<ticket>();
    Button test ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tickets,null);
        SharedPreferences sp = getActivity().getSharedPreferences("userLogin", MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        db my_db = new db(getActivity(),"supratour",null,1);
        ListView lisTickets= (ListView) v.findViewById(R.id.listTicket);
        int id = sp.getInt("UserID",0);
        arr_tickets = my_db.GetTickets(id);

        classTickets c = new classTickets(arr_tickets);
        lisTickets.setAdapter(c);



        return v;
    }


    class classTickets extends BaseAdapter {
        ArrayList<ticket> arr_voy = new ArrayList<ticket>();
        public classTickets(ArrayList<ticket> arr_voy){
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
            return 99;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater lif = getLayoutInflater();
            View ExternalView = lif.inflate(R.layout.ticket_view_template, null);
            TextView carName = ExternalView.findViewById(R.id.autocar);
//            ImageView logo = ExternalView.findViewById(R.id.logoView);
            TextView depart = ExternalView.findViewById(R.id.depart);
            TextView arriver = ExternalView.findViewById(R.id.arriver);
            TextView hour = ExternalView.findViewById(R.id.hour);
            TextView date = ExternalView.findViewById(R.id.date);
            Button price = ExternalView.findViewById(R.id.price);
            Button ticketID = ExternalView.findViewById(R.id.idTicket);
            cardReserv = ExternalView.findViewById(R.id.cardReserv);

            carName.setText(arr_voy.get(i).car);
            depart.setText(arr_voy.get(i).depart);
            arriver.setText(arr_voy.get(i).arriver);
            date.setText(arr_voy.get(i).date);
            hour.setText("à " + arr_voy.get(i).hour);
            price.setText(arr_voy.get(i).price+" DH");
            ticketID.setText("#"+arr_voy.get(i).id);


            return ExternalView;
        }
    }
}