package com.obcompany.myapplication;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id") private int id;
    @SerializedName("name") private String name;
    @SerializedName("email") private String email;
    @SerializedName("phone") private String phone;

    @Override
    public String toString() {
        Log.i("User: ","id: " + id);
        Log.i("User: ","name: " + name);
        Log.i("User: ","email: " + email);
        Log.i("User: ","phone: " + phone);
        Log.i("User: ","-----------------");
        return "";
        //return super.toString();
    }
}
