package com.example.farahnstoreapp.FRAGMENTS;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.farahnstoreapp.Adapter.SubCatAdapter;
import com.example.farahnstoreapp.Model.Category;
import com.example.farahnstoreapp.R;
import com.example.farahnstoreapp.WebService.APIClient;
import com.example.farahnstoreapp.WebService.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FRG_List_Cat extends Fragment {


    RecyclerView rv;
    List<Category> items;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rv = (RecyclerView) inflater.inflate(R.layout.list_recycler_in_fragment,container,false);

        items = new ArrayList<>();
        setupRecycler(rv);
        return rv;
    }

    private void setupRecycler(final RecyclerView recyclerView) {

        APIInterface apiInterface =  APIClient.getClient().create(APIInterface.class);
        Call<List<Category>> call = apiInterface.getSubCategory();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){

                    setupRec(response.body());

                }
            }

            public void setupRec(List<Category> categoryList){

                for(int i = 0 ; i<categoryList.size() ; i++){

                    if(getArguments().get("FRG").equals(categoryList.get(i).getParent())){
                        items.add(categoryList.get(i));
                    }

                }

                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                SubCatAdapter subCatAdapter = new SubCatAdapter(getContext(),items);
                recyclerView.setAdapter(subCatAdapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });


    }

}
