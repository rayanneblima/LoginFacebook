package com.example.rayanne.loginfacebook;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    public static void save(Context context, String name, String value) {
        SharedPreferences s = context.getSharedPreferences("status", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = s.edit();
        edt.putString(name, value);
        edt.apply();
    }

    public static String read(Context context, String name, String defaultvalue) {
        SharedPreferences s = context.getSharedPreferences("status", Context.MODE_PRIVATE);
        return s.getString(name, defaultvalue);
    }

    public static void saveUserId(Context context, String name, String value) {
        SharedPreferences s = context.getSharedPreferences("user id", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = s.edit();
        edt.putString(name, value);
        edt.apply();
    }

    public static String readUserId(Context context, String name, String defaultvalue) {
        SharedPreferences s = context.getSharedPreferences("user id", Context.MODE_PRIVATE);
        return s.getString(name, defaultvalue);
    }

    public static void saveUserName(Context context, String name, String value) {
        SharedPreferences s = context.getSharedPreferences("user name", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = s.edit();
        edt.putString(name, value);
        edt.apply();
    }

    public static String readUserName(Context context, String name, String defaultvalue) {
        SharedPreferences s = context.getSharedPreferences("user name", Context.MODE_PRIVATE);
        return s.getString(name, defaultvalue);
    }

    public static void saveUserEmail(Context context, String name, String value) {
        SharedPreferences s = context.getSharedPreferences("user email", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = s.edit();
        edt.putString(name, value);
        edt.apply();
    }

    public static String readUserEmail(Context context, String name, String defaultvalue) {
        SharedPreferences s = context.getSharedPreferences("user email", Context.MODE_PRIVATE);
        return s.getString(name, defaultvalue);
    }

}






