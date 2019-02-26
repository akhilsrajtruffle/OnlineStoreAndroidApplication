package com.example.farahnstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.farahnstoreapp.Model.GallerySlide;
import com.example.farahnstoreapp.WebService.APIClient;
import com.example.farahnstoreapp.WebService.APIInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.os.Build.VERSION.SDK_INT;

public class Detail_of_product extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Bundle b;
    ImageView img;
    TextView title,price;
    SliderLayout sliderLayout;
    FrameLayout frmImg,frm_Slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (SDK_INT >17){
            getWindow().peekDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        setTitle("");


        title = findViewById(R.id.txt_name_in_detail);
        price = findViewById(R.id.txt_price_in_detail);
        img = findViewById(R.id.img_in_detail);
        sliderLayout = findViewById(R.id.slider_in_detail);
        frmImg = findViewById(R.id.frm_img);
        frm_Slider = findViewById(R.id.frm_slider);

        frm_Slider.setVisibility(View.GONE);


        b = getIntent().getExtras();

        //Toast.makeText(getApplicationContext(),b.get("ID")+"",Toast.LENGTH_SHORT).show();
        getGallerySlides();


        title.setText(b.get("NAME").toString());
        price.setText(b.get("PRICE").toString()+" تومان ");
        Picasso.with(getApplicationContext()).load(b.get("ICON").toString()).into(img);

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
        getMenuInflater().inflate(R.menu.detail_of_product, menu);
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
            Intent i = new Intent(Detail_of_product.this, List_of_Category.class);
            i.putExtra("ITEM","0");
            startActivity(i);
        } else if (id == R.id.nav_profile) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getGallerySlides(){

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<GallerySlide>> call = apiInterface.getGallerySlides(b.get("ID").toString());
        call.enqueue(new Callback<List<GallerySlide>>() {
            @Override
            public void onResponse(Call<List<GallerySlide>> call, Response<List<GallerySlide>> response) {
                if(response.isSuccessful()){
                     Toast.makeText(getApplicationContext(),"ok"+response.body().size(),Toast.LENGTH_SHORT).show();

                     if(response.body().size()>0){
                         setupGallerySlides(response.body());
                     }

                }
            }

            @Override
            public void onFailure(Call<List<GallerySlide>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"عدم ارتباط با سرور"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void setupGallerySlides(List<GallerySlide> gallerySlides){

        frmImg.setVisibility(View.GONE);
        frm_Slider.setVisibility(View.VISIBLE);

        for (int i = 0; i <gallerySlides.size() ; i++) {

            TextSliderView textSliderView = new TextSliderView(this);

            textSliderView.description(gallerySlides.get(i).getDescription())
                    .image(gallerySlides.get(i).getLink())
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);

            sliderLayout.addSlider(textSliderView);
        }

    }

}
