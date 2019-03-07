package com.example.farahnstoreapp.WebService;

import com.example.farahnstoreapp.Model.Category;
import com.example.farahnstoreapp.Model.GallerySlide;
import com.example.farahnstoreapp.Model.Otp;
import com.example.farahnstoreapp.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("new_get_categ_list.php")
    Call<List<Category>> getCategList();

    @GET("new_get_gallery_slide_list.php")
    Call<List<GallerySlide>> getGallerySlides(@Query("id") String id);


    @GET("new_get_categ_products.php")
    Call<List<Product>> getBookProducts();


    @GET("new_get_sub_cat_list.php")
    Call<List<Category>> getSubCategory();


    @GET("new_get_list_of_product_of_category.php")
    Call<List<Product>> getCatProducts(@Query("id") String id);

    @GET("user_set_mobile.php")
    Call<Otp> getOtpStatus(@Query("mobile") String mobile);

    @GET("user_get_otp.php")
    Call<Otp> sendOtp(@Query("id") String id, @Query("otp") String otp);

    @GET("user_register.php")
    Call<Otp> userRegister(@Query("id") String id, @Query("name") String name, @Query("pass") String pass);

}
