package com.example.farahnstoreapp.WebService;

import com.example.farahnstoreapp.Model.Category;
import com.example.farahnstoreapp.Model.GallerySlide;
import com.example.farahnstoreapp.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("new_get_categ_list.php")
    Call<List<Category>> getCategList();

    @GET("new_get_gallery_slide_list.php")
    Call<List<GallerySlide>> getGallerySlides();

    @GET("new_get_categ_products.php")
    Call<List<Product>> getBookProducts();


    @GET("new_get_sub_cat_list.php")
    Call<List<Category>> getSubCategory();


    @GET("getget.php")
    Call<List<Product>> getCatProducts(@Query("id") String id);


}
