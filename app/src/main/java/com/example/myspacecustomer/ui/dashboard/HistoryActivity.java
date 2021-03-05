package com.example.myspacecustomer.ui.dashboard;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myspacecustomer.databinding.ActivityHistoryBinding;

public class HistoryActivity extends AppCompatActivity {


    private ActivityHistoryBinding binding;
    private Context context = this;


    private static final String TAG = "HistoryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        clickListener();


    }

    private void clickListener() {
//        binding.back.setOnClickListener(view -> openActivity(CustomerDashActivity.class));

    }

    private void openActivity(Class aclass) {
        Intent intent= new Intent(context,aclass);
        startActivity(intent);

    }



    private void init()
    {

    }


}
