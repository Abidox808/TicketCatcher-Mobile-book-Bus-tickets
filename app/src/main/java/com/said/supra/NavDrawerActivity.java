package com.said.supra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.said.supra.databinding.ActivityNavDrawerBinding;

public class NavDrawerActivity extends AppCompatActivity {
    TextView name;
    ImageView img;

    db my_db = new db(NavDrawerActivity.this,"supratour",null,1);
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavDrawerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("userLogin", MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        //checking if there is an actual session
        if(sp.getInt("UserID",0) == 0){
            switchActivitiesLogout();
        }

        binding = ActivityNavDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavDrawer.materialToolbar);
        binding.appBarNavDrawer.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }});
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_reservation, R.id.nav_mytickets, R.id.nav_nosagences , R.id.nav_infobagages, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //You need to get NavigationView id in your Activity like this
        NavigationView navigationview = (NavigationView) findViewById(R.id.nav_view);
        //now get header in nav drawer
        View header = navigationview.getHeaderView(0);
        // now find widget id
        name =header.findViewById(R.id.username);
        int userid = sp.getInt("UserID",0);
        String str = my_db.Getusername(userid);
        name.setText(str.substring(0,1).toUpperCase()+ str.substring(1));

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {switchActivities();}});

        img = header.findViewById(R.id.imgProfile);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {switchActivities();}});
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, Myprofile.class);
        startActivity((switchActivityIntent));
    }
    private void switchActivitiesLogout() {
        Intent switchActivityIntent = new Intent(this, loginActivity.class);
        startActivity((switchActivityIntent));
        finish();
    }
}