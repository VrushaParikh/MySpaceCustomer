package com.example.myspacecustomer.ui.dashboard;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myspacecustomer.R;
import com.example.myspacecustomer.databinding.ActivityCustDashBinding;
import com.example.myspacecustomer.model.MySliderList;
import com.example.myspacecustomer.ui.dashboard.slider.SliderAdapter;
import com.example.myspacecustomer.utils.Config;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

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


    /*--------------------------------- Init -----------------------------------------*/

    private void init() {


        toggle = new ActionBarDrawerToggle(activity, binding.drawerLayout, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        setSupportActionBar(binding.includedContent.includedToolbar.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        binding.nav.setNavigationItemSelectedListener(this);

        setSliders();

    }


    /*--------------------------------- On Options Item Selected -----------------------------------------*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


    /*--------------------------------- On Navigation Item Selected -----------------------------------------*/


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                Config.showToast(activity, "Home");
                return true;

            case R.id.nav_gallery:
                Config.showToast(activity, "Edit Profile");
                return true;

            case R.id.nav_slideshow:
                Config.showToast(activity, "Settings");
                return true;

            default:
                return false;
        }
    }



    /*--------------------------------- Slider Beginning -----------------------------------------*/


    private void setSliders() {

        List<MySliderList> mySliderLists = new ArrayList<>();
        mySliderLists.add(new MySliderList(1, "https://homepages.cae.wisc.edu/~ece533/images/watch.png", "Hello", "AirPlane"));
        mySliderLists.add(new MySliderList(2, "https://homepages.cae.wisc.edu/~ece533/images/mountain.png", "Hello", "AirPlane"));
        mySliderLists.add(new MySliderList(3, "https://homepages.cae.wisc.edu/~ece533/images/arctichare.png", "Hello", "AirPlane"));


        SliderAdapter adapter = new SliderAdapter(activity, mySliderLists);

        binding.includedContent.imageSlider.setSliderAdapter(adapter);

        binding.includedContent.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.includedContent.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.includedContent.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        binding.includedContent.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        binding.includedContent.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        binding.includedContent.imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        binding.includedContent.imageSlider.startAutoCycle();

    }


}