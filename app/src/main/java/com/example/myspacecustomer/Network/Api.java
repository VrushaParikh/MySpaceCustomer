package com.example.myspacecustomer.Network;


import com.example.myspacecustomer.model.ServerResponse;
import com.example.myspacecustomer.model.MySliderList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    String my_url = "Cust_Api.php?apicall=";

    @FormUrlEncoded
    @POST(my_url + "login")
    Call<ServerResponse> login(
            @Field("user_email") String user_email,
            @Field("user_pwd") String user_pwd);


    @FormUrlEncoded
    @POST(my_url + "login_data")
    Call<ServerResponse> loginData(
            @Field("user_email") String user_email,
            @Field("user_pwd") String user_pwd);


    @FormUrlEncoded
    @POST(my_url + "register")
    Call<ServerResponse> register(
            @Field("user_name") String username,
            @Field("user_email") String email,
            @Field("user_mobile") String mobile,
            @Field("user_pwd") String pwd);


    @GET("Vendor_Api.php")
    Call<List<MySliderList>> getonbordingdata();

    @GET(my_url + "get_shop_name")
    Call<ServerResponse> getShopName();


}