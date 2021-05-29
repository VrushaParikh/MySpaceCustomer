package com.example.myspacecustomer.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myspacecustomer.Network.Api;
import com.example.myspacecustomer.Network.AppConfig;
import com.example.myspacecustomer.databinding.ActivityChangePasswordBinding;
import com.example.myspacecustomer.model.ServerResponse;
import com.example.myspacecustomer.ui.dashboard.CustomerDashActivity;
import com.example.myspacecustomer.ui.dashboard.HistoryActivity;
import com.example.myspacecustomer.utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangePasswordActivity extends AppCompatActivity {



    private ActivityChangePasswordBinding binding;
    private Context context = this;
    private String email,old_pwd,new_pwd;



    private static final String TAG = "ChangePasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        init();


    }

    private void init()
    {

        binding.change.setOnClickListener(view -> {

            email = binding.edtEmail.getText().toString().trim();
            old_pwd = binding.edtOldpwd.getText().toString().trim();
            new_pwd = binding.edtNewPwd.getText().toString().trim();

            Log.d(TAG, "init: " + email + "----" + old_pwd + "----"+ new_pwd);


            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(old_pwd) || TextUtils.isEmpty(new_pwd)) {
                binding.edtEmail.setError("All Fields are Required!!");
                return;
            }

            doChange(email, old_pwd,new_pwd);
        });

    }

    /*---------------------------------------- Do Change Password ----------------------------------------------------------------*/

    private void doChange(String email, String old_pwd, String new_pwd ) {

        Retrofit retrofit = AppConfig.getRetrofit();
        Api service = retrofit.create(Api.class);

        Call<ServerResponse> call = service.change(email, old_pwd, new_pwd);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {


                if (response.body() != null) {

                    ServerResponse serverResponse = response.body();

                    if (!serverResponse.getError()) {
                        Config.showToast(context, serverResponse.getMessage());
                        openActivity(CustomerDashActivity.class);
                        finish();



                    } else {
                        Config.showToast(context, serverResponse.getMessage());

                    }
                }


            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }


    private void openActivity(Class aclass) {
        Intent intent = new Intent(context, aclass);
        startActivity(intent);

    }


}
