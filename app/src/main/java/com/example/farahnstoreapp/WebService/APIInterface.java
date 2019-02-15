package com.example.farahnstoreapp.WebService;

import com.example.farahnstoreapp.Model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("new_get_categ_list.php")
    Call<List<Category>> getCategList();

}
