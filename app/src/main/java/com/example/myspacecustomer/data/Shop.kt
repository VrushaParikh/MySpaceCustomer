package com.example.myspacevendor.data


import com.google.gson.annotations.SerializedName

data class Shop(
        @SerializedName("shop_id")
        val shopId: Int,

        @SerializedName("shop_acc_no")
        val shopAccNo: String,

        @SerializedName("shop_add")
        val shopAdd: String,

        @SerializedName("shop_bank_name")
        val shopBankName: String,

        @SerializedName("shop_email")
        val shopEmail: String,

        @SerializedName("shop_gst")
        val shopGst: String,

        @SerializedName("shop_ifsc")
        val shopIfsc: String,

        @SerializedName("shop_lic_no")
        val shopLicNo: String,

        @SerializedName("shop_name")
        val shopName: String,

        @SerializedName("shop_pan")
        val shopPan: String,

        @SerializedName("shop_pincode")
        val shopPincode: String,

        @SerializedName("shop_sqft")
        val shopSqft: String,

        @SerializedName("shop_timing")
        val shopTiming: String
)