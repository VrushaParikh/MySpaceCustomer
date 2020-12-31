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
import com.example.myspacecustomer.databinding.ActivityRegisterBinding;
import com.example.myspacecustomer.model.ServerResponse;
import com.example.myspacecustomer.utils.Config;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private Context context = this;

    private String username, email, password,mobile;
    private static final String TAG = "RegistrationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        clickListener();

    }


    private void init() {

        binding.register.setOnClickListener(view -> {
            username = binding.edtFname.getText().toString().trim();

            email = binding.edtEmail.getText().toString().trim();

            password = binding.edtPwd.getText().toString().trim();
            mobile = binding.edtPh.getText().toString().trim();



//            Log.d(TAG, "init: " + fname + "----" + email + "----" + category + "----" + dob + "----" + password + "----" + ad_no + "----" + phno);


            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email)  || TextUtils.isEmpty(password) || TextUtils.isEmpty(mobile) ) {

                if (TextUtils.isEmpty(username)) {
                    binding.edtFname.setError("User Name Required!!");
                }

                if (TextUtils.isEmpty(email)) {
                    binding.edtEmail.setError("Email Required!!");
                }

                if (TextUtils.isEmpty(password)) {
                    binding.edtPwd.setError("Password Required!!");
                }

                if (TextUtils.isEmpty(mobile)) {
                    binding.edtPh.setError("Phone Number Required!!");
                }



                return;
            }
            doRegister( username, email, mobile, password);

        });


    }

    private void doRegister( String username, String email, String mobile, String pwd) {

        Retrofit retrofit = AppConfig.getRetrofit();
        Api service = retrofit.create(Api.class);

        Call<ServerResponse> call = service.register( username, email, mobile, pwd );
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Config.showToast(context, response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private void clickListener() {

        binding.already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity(com.example.myspacecustomer.ui.LoginActivity.class);
            }
        });
    }


    private void openActivity(Class aclass) {
        Intent intent = new Intent(context, aclass);
        startActivity(intent);
    }


}