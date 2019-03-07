package com.example.farahnstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.farahnstoreapp.Adapter.CatTitlesListAdapter;
import com.example.farahnstoreapp.Adapter.ProductListAdapter;
import com.example.farahnstoreapp.Model.Category;
import com.example.farahnstoreapp.Model.GallerySlide;
import com.example.farahnstoreapp.Model.Product;
import com.example.farahnstoreapp.Model.UserData;
import com.example.farahnstoreapp.WebService.APIClient;
import com.example.farahnstoreapp.WebService.APIInterface;
import com.facebook.shimmer.ShimmerFrameLayout;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.os.Build.VERSION.SDK_INT;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView rcCateg,recProduct;
    public static List<Category> MainCategoryList;
    SliderLayout sliderLayout;
    ShimmerFrameLayout SHSlider,SHCat,SHPr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (SDK_INT >17){
            getWindow().peekDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        setTitle("فروشگاه فرهنگ");
        rcCateg = findViewById(R.id.rec_main_cat);
        recProduct = findViewById(R.id.rec_main_book_product);
        SHCat = findViewById(R.id.shimmer_cat);
        //SHSlider = findViewById(R.id.shimmer_slider);
        //shimmerLayout= findViewById(R.id.shimmer);
        SHPr= findViewById(R.id.shimmer_pr);
        sliderLayout = findViewById(R.id.slider);

        getGallerySlides();
        getCateg();
        GetProduct();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_category) {
            Intent i = new Intent(MainActivity.this, List_of_Category.class);
            i.putExtra("ITEM","0");
            startActivity(i);
        } else if (id == R.id.nav_profile) {
            Intent i = new Intent(MainActivity.this, UserActivity.class);
            startActivity(i);
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getCateg(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<Category>> call = apiInterface.getCategList();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    SHCat.setVisibility(View.GONE);
                    MainCategoryList=response.body();
                    setupCatRec(MainCategoryList);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"عدم ارتباط با سرور"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void setupCatRec(List<Category> categoryList){

        CatTitlesListAdapter catTitlesListAdapter = new CatTitlesListAdapter(this,categoryList);
        rcCateg.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rcCateg.setAdapter(catTitlesListAdapter);

    }

    public void getGallerySlides(){

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<GallerySlide>> call = apiInterface.getGallerySlides("0");
        call.enqueue(new Callback<List<GallerySlide>>() {
            @Override
            public void onResponse(Call<List<GallerySlide>> call, Response<List<GallerySlide>> response) {
                if(response.isSuccessful()){
                   // Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
//                    SHSlider.setVisibility(View.GONE);
                    setupGallerySlides(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<GallerySlide>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"عدم ارتباط با سرور"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void setupGallerySlides(List<GallerySlide> gallerySlides){

        for (int i = 0; i <gallerySlides.size() ; i++) {

            TextSliderView textSliderView = new TextSliderView(this);

            textSliderView.description(gallerySlides.get(i).getDescription())
                    .image(gallerySlides.get(i).getLink())
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);

            sliderLayout.addSlider(textSliderView);
        }

    }

    public void GetProduct(){

        APIInterface apiInterface =  APIClient.getClient().create(APIInterface.class);
        Call<List<Product>> call = apiInterface.getBookProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    SHPr.setVisibility(View.GONE);
                    setupBookProductRec(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"عدم اتصال به سرور",Toast.LENGTH_LONG).show();
            }
        });


    }
    public void setupBookProductRec(List<Product> ProductList){

        ProductListAdapter productListAdapter = new ProductListAdapter(this,ProductList);
        recProduct.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recProduct.setAdapter(productListAdapter);

    }


}
