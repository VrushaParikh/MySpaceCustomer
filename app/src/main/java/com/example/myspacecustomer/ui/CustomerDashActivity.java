package com.example.myspacecustomer.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myspacecustomer.R;
import com.example.myspacecustomer.databinding.ActivityCustDashBinding;
import com.example.myspacecustomer.utils.Config;
import com.google.android.material.navigation.NavigationView;

public class CustomerDashActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityCustDashBinding binding;
    private ActionBarDrawerToggle toggle;
    private final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCustDashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {


        toggle = new ActionBarDrawerToggle(activity, binding.drawerLayout, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        setSupportActionBar(binding.includedContent.includedToolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        binding.nav.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                Config.showToast(activity, "Home");
                return true;

            case R.id.nav_gallery:
                Config.showToast(activity, "gallery");
                return true;

            case R.id.nav_slideshow:
                Config.showToast(activity, "Slide");
                return true;

            default:
                return false;
        }

    }
}