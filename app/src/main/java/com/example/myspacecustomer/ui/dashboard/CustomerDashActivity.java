package com.example.myspacecustomer.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myspacecustomer.Network.Api;
import com.example.myspacecustomer.Network.AppConfig;
import com.example.myspacecustomer.R;
import com.example.myspacecustomer.adapters.ShopNameAdapter;

import com.example.myspacecustomer.data.Banner;
import com.example.myspacecustomer.databinding.ActivityCustDashBinding;
import com.example.myspacecustomer.model.MySliderList;
import com.example.myspacecustomer.model.ServerResponse;
import com.example.myspacecustomer.ui.ChangePasswordActivity;
import com.example.myspacecustomer.ui.ShopProfileActivity;
import com.example.myspacecustomer.ui.auth.LoginActivity;
import com.example.myspacecustomer.ui.dashboard.slider.SliderAdapter;
import com.example.myspacecustomer.utils.SharedPrefManager;
import com.example.myspacevendor.data.Shop;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CustomerDashActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ShopNameAdapter.ShopInterface {

    private ActivityCustDashBinding binding;

    private ActionBarDrawerToggle toggle;
    private final Context context = this;
    private final Activity activity = this;
    private SharedPrefManager sharedPrefManager;
    private SliderAdapter adapter;


    private List<Shop> shopList = new ArrayList<>();

    // banner's var
    private List<Banner> bannerList = new ArrayList<>();


    private ShopNameAdapter shopNameAdapter;

    private static final String TAG = "CustomerDashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCustDashBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        sharedPrefManager = new SharedPrefManager(context);


        init();
        clickListener();
    }


    private void clickListener() {

        binding.includedContent.editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input


                Log.d(TAG, "afterTextChanged: " + editable.toString());
                shopNameAdapter.getFilter().filter(editable.toString());


            }
        });

    }




    /*--------------------------------- Init -----------------------------------------*/

    private void init() {

        setSupportActionBar(binding.includedContent.includedToolbar.customToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toggle);


        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.includedContent.includedToolbar.customToolbar, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        binding.nav.setNavigationItemSelectedListener(this);

        adapter = new SliderAdapter(activity, bannerList);

        handleImageListHere();
        setSliders();
        setShops();

        manageHeaderView();

    }


    private void setShops() {


        Retrofit retrofit = AppConfig.getRetrofit();
        Api service = retrofit.create(Api.class);

        Call<ServerResponse> call = service.getShopName();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {


                if (response.body() != null) {

                    ServerResponse response1 = response.body();

                    shopList.clear();
                    shopList.addAll(response1.getShopList());

                    shopNameAdapter = new ShopNameAdapter(shopList, CustomerDashActivity.this);
                    binding.includedContent.shna.setAdapter(shopNameAdapter);

                    //names.addAll(response1.getShopList());
                    shopNameAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    /*--------------------------------- Manage header View -----------------------------------------*/


    private void manageHeaderView() {

        View header = binding.nav.getHeaderView(0);
        TextView tv = header.findViewById(R.id.header_user_name);
        tv.setText(sharedPrefManager.getString("name"));

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
                openActivity(CustomerDashActivity.class);
                return true;

            case R.id.nav_history:
                openActivity(HistoryActivity.class);
                return true;


            case R.id.nav_logout:
                sharedPrefManager.clear();
                openActivity(LoginActivity.class);
                return true;

            case R.id.nav_setting:
                openActivity(ChangePasswordActivity.class);
                return true;

            default:
                return false;
        }
    }



    /*--------------------------------- Dynamic Slider Beginning -----------------------------------------*/


    private void setSliders() {

        Retrofit retrofit = AppConfig.getRetrofit();
        Api service = retrofit.create(Api.class);

        Call<ServerResponse> call = service.fetchBanners();
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {


                if (response.body() != null) {

                    ServerResponse response1 = response.body();

                    bannerList.clear();
                    bannerList.addAll(response1.getImageList());
                    adapter.notifyDataSetChanged();
//                    handleImageListHere();

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }


    /*--------------------------------- Handling Offer Banners --------------------------------*/

    private void handleImageListHere() {


        binding.includedContent.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.includedContent.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.includedContent.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        binding.includedContent.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        binding.includedContent.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        binding.includedContent.imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        binding.includedContent.imageSlider.startAutoCycle();


        binding.includedContent.imageSlider.setSliderAdapter(adapter);


    }




    /*--------------------------------- Open Activity -----------------------------------------*/

    private void openActivity(Class aclass) {
        Intent intent = new Intent(context, aclass);
        startActivity(intent);
    }


    /*----------------------------- Handle Interface ---------------------------------*/

    @Override
    public void onClick(Shop shop) {

        Intent intent = new Intent(context, ShopProfileActivity.class);
        intent.putExtra("shop_id", shop.getShopId());
        startActivity(intent);

    }
}