package com.said.supra.frags;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.said.supra.R;
import com.said.supra.db;
import com.said.supra.reservation_seat;
import com.said.supra.voyage;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reservation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reservation extends Fragment {
    Button pickdate;
    Button recherche;
    TextView selectedDate;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public reservation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reservation.
     */
    // TODO: Rename and change types and number of parameters
    public static reservation newInstance(String param1, String param2) {
        reservation fragment = new reservation();
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
    String spinner1Text,spinner2Text;
    ArrayList<voyage> arr_vo = new ArrayList<voyage>();
    TextView dateSelected ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db my_db = new db(getActivity(),"supratour",null,1);
        int day,month,year;
        View v = inflater.inflate(R.layout.fragment_reservation,null);
        Spinner spinner1 = v.findViewById(R.id.spinner1);
        Spinner spinner2 = v.findViewById(R.id.spinner2);
        pickdate = v.findViewById(R.id.pickDate);
        selectedDate = v.findViewById(R.id.selectedDate);
        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(container.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    selectedDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);}}, year, month, day);
                datePickerDialog.show();
            }
        });
        ListView listvoyages= (ListView) v.findViewById(R.id.listViewRes);
        LinearLayout linearPar = v.findViewById(R.id.linearPar); // get listview layout
        recherche = v.findViewById(R.id.btnRecherche);
        TextView dateSelected = v.findViewById(R.id.selectedDate);
        String [] villes = getResources().getStringArray(R.array.villes);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner1Text=villes[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner2Text=villes[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dateSelected.getText().toString() == ""){
                    Toast.makeText(getContext(), "Veuillez choisir une date", Toast.LENGTH_SHORT).show();
                }else{
                    arr_vo = my_db.GetVoyageData(spinner1Text,spinner2Text);
                    arr_vo.get(0).addDate(dateSelected.getText().toString());
                    linearPar.setVisibility(View.VISIBLE); // once button clicked show listview
                    classVoyages c = new classVoyages(arr_vo);
                    listvoyages.setAdapter(c);
                }
            }
        });
        return v;
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
            LayoutInflater lif = getLayoutInflater();
            View ExternalView = lif.inflate(R.layout.card_view_template,null);
            TextView carName = ExternalView.findViewById(R.id.carName);
            ImageView logo = ExternalView.findViewById(R.id.logoView);
            TextView depart= ExternalView.findViewById(R.id.depart);
            TextView arriver= ExternalView.findViewById(R.id.arriver);
            TextView departHour= ExternalView.findViewById(R.id.departHour);
            TextView arriverHour= ExternalView.findViewById(R.id.arriverHour);
            TextView price = ExternalView.findViewById(R.id.price);

            cardReserv = ExternalView.findViewById(R.id.cardReserv);

            carName.setText(arr_voy.get(i).car);
            depart.setText(arr_voy.get(i).depart);
            arriver.setText(arr_voy.get(i).arriver);
            departHour.setText(arr_voy.get(i).departHour);
            arriverHour.setText(arr_voy.get(i).arriverHour);
            price.setText(Integer.toString(arr_voy.get(i).price));
            cardReserv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent switchIntent = new Intent(getContext(), reservation_seat.class);
                    Bundle b1 = new Bundle();
                    b1.putString("autocar",carName.getText().toString());
                    b1.putString("time",departHour.getText() + "  -  " + arriverHour.getText());
                    b1.putString("ville",depart.getText().toString() + "  -  " + arriver.getText().toString());
                    b1.putString("price",price.getText().toString()+" DH");
                    b1.putString("price2",price.getText().toString());
                    b1.putString("Vdepart",depart.getText().toString());
                    b1.putString("Hdepart",departHour.getText().toString());
                    b1.putString("Varriver",arriver.getText().toString());
                    b1.putString("date",arr_voy.get(0).date);
                    switchIntent.putExtras(b1);
                    startActivity((switchIntent));
                }
            });
            switch(arr_voy.get(i).car){
                case "CTM":
                    logo.setImageResource(R.drawable.ctm);
                    break;
                case "Globus":
                    logo.setImageResource(R.drawable.globus);
                    break;
                case "ITRANE SOUSS":
                    logo.setImageResource(R.drawable.itrane_souss);
                    break;
                case "JAOUHARAT AGADIR":
                    logo.setImageResource(R.drawable.jaouharate_agadir);
                    break;
                case "JANA VIAJES":
                    logo.setImageResource(R.drawable.jana_viages);
                    break;
                case "SUPRATOURS":
                    logo.setImageResource(R.drawable.supratour);
                    break;
                case "CHARAF BUS":
                    logo.setImageResource(R.drawable.charaf_bus);
                    break;
                case "ITRANE VOYAGE":
                    logo.setImageResource(R.drawable.itrane);
                    break;
                case "ISMAILIA CAR":
                    logo.setImageResource(R.drawable.ismailiya);
                    break;
                case "TASSAOUT":
                    logo.setImageResource(R.drawable.trans_tassaout);
                    break;
                case "TRS AL YAMAMA":
                    logo.setImageResource(R.drawable.al_yamama);
                    break;
                case "TRANS GHAZALA":
                    logo.setImageResource(R.drawable.ghazala);
                    break;
                case "STNR":
                    logo.setImageResource(R.drawable.stnr);
                    break;
                case "TRANS AL HAMD":
                    logo.setImageResource(R.drawable.alhamd);
                    break;
                case "SAT":
                    logo.setImageResource(R.drawable.sat);
                    break;
                case "AL WISSAM ADDAHABI":
                    logo.setImageResource(R.drawable.alwissam);
                    break;
                case "EL BACHIRI":
                    logo.setImageResource(R.drawable.bachiri);
                    break;
                case "PRESTIGE REDA":
                    logo.setImageResource(R.drawable.reda);
                    break;
                case "LIBRA TOURS":
                    logo.setImageResource(R.drawable.libra);
                    break;
                case "TRANS ANNAMIR":
                    logo.setImageResource(R.drawable.annamir);
                    break;
                case "MOURIH NAJAT STMN":
                    logo.setImageResource(R.drawable.mourih);
                    break;
                default:
            }

            return ExternalView;
        }
    }

}