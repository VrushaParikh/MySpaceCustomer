package com.example.myspacecustomer.utils;

import android.content.Context;
import android.widget.Toast;

public class Config {

    public static int user_id = -1;
    //public static String url = "http://192.168.2.11/myspace/api/";

    //     public static String url = "http://192.168.137.1/myspace/api/";

    public static String BASE_URL = "http://192.168.0.140/my_space/myspace/";  // Khushaali Failed to Play Concatenation Game.

    public static String url = BASE_URL + "api/";
    public static String bannerUrl = BASE_URL + "banner/";  // It was so Simple...

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
