package com.example.myspacevendor.data.slot


import com.google.gson.annotations.SerializedName

data class SlotData(
        @SerializedName("shop_id")
        val shopId: String,

        @SerializedName("slot_end")
        val slotEnd: String,

        @SerializedName("slot_id")
        val slotId: Int,

        @SerializedName("slot_start")
        val slotStart: String,

        @SerializedName("vendor_id")
        val vendorId: String,
)