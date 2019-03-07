package com.example.farahnstoreapp.FRAGMENTS;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.farahnstoreapp.Model.UserData;
import com.example.farahnstoreapp.R;

public class UserInfo extends Fragment {

    TextView name,mobile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_user_info,container,false);

        name = view.findViewById(R.id.edt_frguser_login_name);
        mobile = view.findViewById(R.id.edt_frguser_login_tel);

        UserData userData =  new UserData(getContext());
        name.setText("نام :"+ userData.GetName());
        mobile.setText("موبایل :"+ userData.GetTel());


        return view;
    }
}
