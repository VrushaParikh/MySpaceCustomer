package com.example.myspacecustomer.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myspacecustomer.Network.Api;
import com.example.myspacecustomer.Network.AppConfig;


import com.example.myspacecustomer.databinding.ActivityLoginBinding;
import com.example.myspacecustomer.model.ServerResponse;
import com.example.myspacecustomer.model.User;
import com.example.myspacecustomer.ui.dashboard.CustomerDashActivity;
import com.example.myspacecustomer.utils.Config;
import com.example.myspacecustomer.utils.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private final Context context = this;

    private String user_email, user_pwd;

    private static final String TAG = "LoginActivity";
    private SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sharedPrefManager = new SharedPrefManager(context);

        init();
        clickListener();

    }

    private void init() {


        binding.login.setOnClickListener(view -> {

            user_email = binding.edtEmail.getText().toString().trim();
            user_pwd = binding.edtPwd.getText().toString().trim();

            Log.d(TAG, "init: " + binding.edtEmail.getText().toString().trim() + "----" + binding.edtPwd.getText().toString().trim());


            if (TextUtils.isEmpty(user_email) || TextUtils.isEmpty(user_pwd)) {
                binding.edtPwd.setError("All Fields are Required!!");
                return;
            }

            doLogin(user_email, user_pwd);
        });


    }


    private void clickListener() {

        binding.signup.setOnClickListener(v -> openActivity(RegistrationActivity.class));
    }


    private void openActivity(Class aclass) {
        Intent intent = new Intent(context, aclass);
        startActivity(intent);
    }

    /*---------------------------------------- Do Login ----------------------------------------------------------------*/

    private void doLogin(String user_email, String user_pwd) {

        Retrofit retrofit = AppConfig.getRetrofit();
        Api service = retrofit.create(Api.class);

        Call<ServerResponse> call = service.login(user_email, user_pwd);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {


                if (response.body() != null) {

                    ServerResponse serverResponse = response.body();

                    if (!serverResponse.getError()) {
                        Config.showToast(context, serverResponse.getMessage());
                        sendUserData(serverResponse.getUser());


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


    /*----------------------------------------------- Save User Data -----------------------------------------------*/

    private void sendUserData(User user) {

        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        sharedPrefManager.setInt("id", Integer.parseInt(user.getUserId()));
        sharedPrefManager.setString("name", user.getUserName());

        sharedPref();
    }


    @Override
    protected void onStart() {
        super.onStart();

        sharedPref();

    }

    private void sharedPref() {
        SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
        Config.user_id = sharedPrefManager.getInt("id");

        if (Config.user_id != -1) {

            openActivity(CustomerDashActivity.class);
            finish();
        }

    }

}
