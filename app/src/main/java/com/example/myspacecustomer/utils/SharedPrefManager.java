package com.example.myspacecustomer.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private final Context context;
    private final String PREFS_NAME = "login";
    private final SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public SharedPrefManager ( Context context ) {
        this.context = context;
        SharedPreferences prefs = context.getSharedPreferences ( PREFS_NAME , 0 );
        editor = prefs.edit ( );
    }

    public boolean sharedPreferenceExist ( String key ) {
        SharedPreferences prefs = context.getSharedPreferences ( PREFS_NAME , 0 );
        return !prefs.contains ( key );
    }

    public void setInt ( String key , int value ) {
        editor.putInt ( key , value );
        editor.apply ( );
    }

    public int getInt ( String key ) {
        SharedPreferences prefs = context.getSharedPreferences ( PREFS_NAME , 0 );
        return prefs.getInt ( key , -1 );
    }

    public void setString ( String key , String value ) {
        editor.putString ( key , value );
        editor.apply ( );
    }

    public String getString ( String key ) {
        SharedPreferences prefs = context.getSharedPreferences ( PREFS_NAME , 0 );
        return prefs.getString ( key , "DNF" );
    }

    public void setBoolean ( String key , boolean value ) {
        editor.putBoolean ( key , value );
        editor.apply ( );
    }

    public boolean getBoolean ( String key ) {
        SharedPreferences prefs = context.getSharedPreferences ( PREFS_NAME , 0 );
        return prefs.getBoolean ( key , false );
    }

    public void clear () {
        editor.clear ( );
        editor.apply ( );
    }
}