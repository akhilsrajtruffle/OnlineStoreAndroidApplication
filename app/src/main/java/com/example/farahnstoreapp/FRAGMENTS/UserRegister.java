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

public class UserRegister extends Fragment {

    Button Register;
    EditText name,Pass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_user_register,container,false);

        Register = view.findViewById(R.id.btn_frguserreg_reg);
        name = view.findViewById(R.id.edt_frguserreg_name);
        Pass = view.findViewById(R.id.edt_frguserreg_pass);

        //Toast.makeText(getContext(),getArguments().get("ID").toString(),Toast.LENGTH_SHORT).show();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().length()<=1 && Pass.getText().length()<=0  ){
                    Toast.makeText(getContext(),"شماره موارد را صحیح وارد کنید.",Toast.LENGTH_SHORT).show();
                }else if(name.getText().length()>1 && Pass.getText().length()>0  )  {
                   //Toast.makeText(getContext(),Pass.getText().toString()+"\n"+name.getText().toString(),Toast.LENGTH_SHORT).show();
                    register(getArguments().get("ID").toString(),name.getText().toString(),Pass.getText().toString());
                }
            }
        });

        return view;
    }

    public void register(final String id, final String name, final String pass){

        APIInterface apiInterface =  APIClient.getClient().create(APIInterface.class);
        Call<Otp> call = apiInterface.userRegister(id,name,pass);
        call.enqueue(new Callback<Otp>() {
            @Override
            public void onResponse(Call<Otp> call, Response<Otp> response) {
                if(response.isSuccessful()){
                    Otp otp =response.body();

                    if(otp.getStatus().equals("updated")){
                        Toast.makeText(getContext(),"ثبت نام با موفقیت انجام شد.",Toast.LENGTH_SHORT).show();
                        UserData userData =  new UserData(getContext());
                        userData.SetID(id);
                        userData.SetName(name);
                        userData.SetPass(pass);

                        Fragment InfoFragment = new UserInfo();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, InfoFragment ); // give your fragment container id in first parameter
                        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                        transaction.commit();

                    }else if(otp.getStatus().equals("fail")){
                        Toast.makeText(getContext(),"ثبت نام نا موفق",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Otp> call, Throwable t) {
                Toast.makeText(getContext(),"عدم ارتباط با سرور",Toast.LENGTH_SHORT).show();
            }
        });


    }


}
