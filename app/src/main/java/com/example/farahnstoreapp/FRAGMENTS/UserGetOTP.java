package com.example.farahnstoreapp.FRAGMENTS;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farahnstoreapp.Model.Otp;
import com.example.farahnstoreapp.Model.UserData;
import com.example.farahnstoreapp.R;
import com.example.farahnstoreapp.WebService.APIClient;
import com.example.farahnstoreapp.WebService.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserGetOTP extends Fragment {

    EditText otpEdt;
    Button send;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_user_get_otp,container,false);

        otpEdt = view.findViewById(R.id.edt_frguser_login_pass);
        send = view.findViewById(R.id.btn_frguser_login_login);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otpEdt.getText().length()>5 && otpEdt.getText().length()<=4 ){
                    Toast.makeText(getContext(),"کد فعال سازی را صحیح وارد کنید.",Toast.LENGTH_SHORT).show();
                }else {
                    sendOtp(getArguments().get("ID").toString(),otpEdt.getText().toString());
                }
            }
        });

        return view;
    }

    public void sendOtp(String id,String otp){
        APIInterface apiInterface =  APIClient.getClient().create(APIInterface.class);
        Call<Otp> call = apiInterface.sendOtp(id,otp);
        call.enqueue(new Callback<Otp>() {
            @Override
            public void onResponse(Call<Otp> call, Response<Otp> response) {
                if(response.isSuccessful()){
                    Otp otp = response.body();
                    if(otp.getStatus().equals("ok")){


                        UserData userData =  new UserData(getContext());
                        userData.SetTel(getArguments().get("MOBILE").toString());

                        Fragment RegFragment = new UserRegister();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();

                        Bundle b = new Bundle();
                        b.putString("ID",getArguments().get("ID").toString());

                        RegFragment.setArguments(b);

                        transaction.replace(R.id.container, RegFragment ); // give your fragment container id in first parameter
                        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                        transaction.commit();
                    }else if(otp.getStatus().equals("wrong")) {
                        Toast.makeText(getContext(),"کد فعال سازی وارد شده صحیح نیست",Toast.LENGTH_LONG).show();
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
