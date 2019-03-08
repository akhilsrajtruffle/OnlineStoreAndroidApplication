package com.example.farahnstoreapp.FRAGMENTS;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farahnstoreapp.Model.Otp;
import com.example.farahnstoreapp.Model.Product;
import com.example.farahnstoreapp.Model.UserData;
import com.example.farahnstoreapp.R;
import com.example.farahnstoreapp.WebService.APIClient;
import com.example.farahnstoreapp.WebService.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLogin extends Fragment {

    Button Register,login;
    EditText Tel,Pass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_user_login,container,false);

        Register = view.findViewById(R.id.btn_frguser_login_register);
        login = view.findViewById(R.id.btn_frguser_login_login);
        Tel = view.findViewById(R.id.edt_frguser_login_tel);
        Pass = view.findViewById(R.id.edt_frguser_login_pass);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Tel.getText().length()<=10 || Tel.getText().length()>11 ){
                    Toast.makeText(getContext(),"شماره تلفن همراه را صحیح وارد کنید.",Toast.LENGTH_SHORT).show();
                }else {
                    //Toast.makeText(getContext(),"ممنون",Toast.LENGTH_SHORT).show();
                    getOtpStatus(Tel.getText().toString());
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((Tel.getText().length()<=10 || Tel.getText().length()>11)&&(Pass.getText().length()<=0) ){
                    Toast.makeText(getContext(),"شماره تلفن همراه یا رمز عبور را صحیح وارد کنید.",Toast.LENGTH_SHORT).show();
                }else if((Tel.getText().length()==11 )&&(Pass.getText().length()>0) ) {
                    //Toast.makeText(getContext(),"ممنون",Toast.LENGTH_SHORT).show();
                    //getOtpStatus(Tel.getText().toString());
                    Login(Tel.getText().toString(),Pass.getText().toString());
                }
            }
        });

        return view;
    }

    public void getOtpStatus(final String mobile){
        APIInterface apiInterface =  APIClient.getClient().create(APIInterface.class);
        Call<Otp> call = apiInterface.getOtpStatus(mobile);
        call.enqueue(new Callback<Otp>() {
            @Override
            public void onResponse(Call<Otp> call, Response<Otp> response) {
                if(response.isSuccessful()){
                        Otp otp =response.body();
                        if(otp.getStatus().equals("exist")){
                            Toast.makeText(getContext(),"شما قبلا ثبت نام کرده اید",Toast.LENGTH_LONG).show();
                        }else if(otp.getStatus().equals("added")){
                            Fragment OtpFragment = new UserGetOTP();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();

                            Bundle b = new Bundle();
                            b.putString("ID",otp.getId());
                            b.putString("MOBILE",mobile);
                            OtpFragment.setArguments(b);

                            transaction.replace(R.id.container, OtpFragment ); // give your fragment container id in first parameter
                            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                            transaction.commit();

                        }
                }
            }

            @Override
            public void onFailure(Call<Otp> call, Throwable t) {
                Toast.makeText(getContext(),"عدم اتصال به سرور",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Login(final String mobile , final String pass){
        APIInterface apiInterface =  APIClient.getClient().create(APIInterface.class);
        Call<Otp> call = apiInterface.userLogin(mobile , pass);
        call.enqueue(new Callback<Otp>() {
            @Override
            public void onResponse(Call<Otp> call, Response<Otp> response) {
                if(response.isSuccessful()){
                    Otp otp =response.body();
                    if(otp.getStatus().equals("wrong")){
                        Toast.makeText(getContext(),"نام کاربری یا رمز عبور اشتباه است",Toast.LENGTH_LONG).show();
                    }else if(otp.getStatus().equals("login")){

                        Toast.makeText(getContext(),otp.getName()+"خوش آمدید",Toast.LENGTH_LONG).show();
                        UserData userData = new UserData(getContext());
                        userData.SetID(otp.getId());
                        userData.SetTel(mobile);
                        userData.SetPass(pass);
                        userData.SetName(otp.getName());

                        Fragment InfoFragment = new UserInfo();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, InfoFragment ); // give your fragment container id in first parameter
                        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                        transaction.commit();
                    }
                }
            }

            @Override
            public void onFailure(Call<Otp> call, Throwable t) {
                Toast.makeText(getContext(),"عدم اتصال به سرور",Toast.LENGTH_LONG).show();
            }
        });
    }

}
