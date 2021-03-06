package com.example.myspacecustomer.data


import com.google.gson.annotations.SerializedName

data class Booking(
        @SerializedName("booking_id")
        val bookingId: String,

        @SerializedName("is_done")
        val isDone: Int,

        @SerializedName("is_verified")
        val isVerified: Int,

        @SerializedName("shop_id")
        val shopId: String,

        @SerializedName("shop_name")
        val shopName: String,

        @SerializedName("slot_end")
        val slotEnd: String,

        @SerializedName("slot_id")
        val slotId: String,

        @SerializedName("slot_start")
        val slotStart: String,

        @SerializedName("user_id")
        val userId: String,

        @SerializedName("user_name")
        val userName: String,
)