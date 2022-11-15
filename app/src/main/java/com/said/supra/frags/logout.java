package com.said.supra.frags;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.said.supra.R;
import com.said.supra.loginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link logout#newInstance} factory method to
 * create an instance of this fragment.
 */
public class logout extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public logout() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment logout.
     */
    // TODO: Rename and change types and number of parameters
    public static logout newInstance(String param1, String param2) {
        logout fragment = new logout();
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences sp = getActivity().getSharedPreferences("userLogin", MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        spe.putInt("UserID",0);
        switchActivitiesLogout();



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }
    private void switchActivitiesLogout() {
        Intent switchActivityIntent = new Intent(getActivity(), loginActivity.class);
        startActivity((switchActivityIntent));
        getActivity().finish();
    }
}