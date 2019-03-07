package com.example.farahnstoreapp.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class UserData {

    private Context context;

    public UserData(Context context){

        this.context = context;
    }

    public void SetID(String id){
        SharedPreferences shp = context.getSharedPreferences("USER",Context.MODE_PRIVATE);
        SharedPreferences.Editor shpedit = shp.edit();
        shpedit.putString("ID",id);
        shpedit.commit();
    }

    public String GetId(){
        SharedPreferences shp = context.getSharedPreferences("USER",Context.MODE_PRIVATE);
        return shp.getString("ID","-");
    }


    public void SetTel(String tel){
        SharedPreferences shp = context.getSharedPreferences("USER",Context.MODE_PRIVATE);
        SharedPreferences.Editor shpedit = shp.edit();
        shpedit.putString("TEL",tel);
        shpedit.commit();
    }

    public String GetTel(){
        SharedPreferences shp = context.getSharedPreferences("USER",Context.MODE_PRIVATE);
        return shp.getString("TEL","-");
    }


    public void SetName(String name){
        SharedPreferences shp = context.getSharedPreferences("USER",Context.MODE_PRIVATE);
        SharedPreferences.Editor shpedit = shp.edit();
        shpedit.putString("NAME",name);
        shpedit.commit();
    }

    public String GetName(){
        SharedPreferences shp = context.getSharedPreferences("USER",Context.MODE_PRIVATE);
        return shp.getString("NAME","-");
    }


    public void SetPass(String pass){
        SharedPreferences shp = context.getSharedPreferences("USER",Context.MODE_PRIVATE);
        SharedPreferences.Editor shpedit = shp.edit();
        shpedit.putString("PASS",pass);
        shpedit.commit();
    }

    public String GetPass(){
        SharedPreferences shp = context.getSharedPreferences("USER",Context.MODE_PRIVATE);
        return shp.getString("PASS","-");
    }



}
