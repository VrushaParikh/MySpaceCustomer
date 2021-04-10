package com.example.myspacecustomer.model

import com.example.myspacecustomer.data.Banner
import com.example.myspacecustomer.data.Booking
import com.example.myspacevendor.data.Shop
import com.example.myspacevendor.data.slot.SlotData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ServerResponse {
    @SerializedName("error")
    @Expose
    var error: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("user")
    @Expose
    var user: User? = null

    @SerializedName("shop")
    @Expose
    var shop: Shop? = null

    @SerializedName("shops")
    @Expose
    var shopList: List<Shop>? = null

    @SerializedName("bookings")
    @Expose
    var bookingList: List<Booking>? = null

    @SerializedName("images")
    @Expose
    var imageList: List<Banner>? = null

    @SerializedName("slots")
    @Expose
    var slotList: List<SlotData>? = null
}