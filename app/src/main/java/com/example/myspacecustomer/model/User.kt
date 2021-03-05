package com.example.myspacecustomer.model


import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("user_email")
        val userEmail: String,

        @SerializedName("user_id")
        val userId: String,

        @SerializedName("user_mobile")
        val userMobile: String,

        @SerializedName("user_name")
        val userName: String,

        @SerializedName("user_pwd")
        val userPwd: String
)