package com.example.farahnstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.farahnstoreapp.Adapter.FragmentAdapter;
import com.example.farahnstoreapp.FRAGMENTS.FRG_List_Cat;

import static android.os.Build.VERSION.SDK_INT;
import static com.example.farahnstoreapp.MainActivity.MainCategoryList;

public class List_of_Category extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of__category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (SDK_INT >17){
            getWindow().peekDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        setTitle("دسته بندی محصولات");

        b = getIntent().getExtras();
        init();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_category) {

        } else if (id == R.id.nav_profile) {
            Intent i = new Intent(List_of_Category.this, UserActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private  void init(){
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if(viewPager != null){
            setupViewPager(viewPager);
        }
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){

        int catCount=0;
        catCount=MainCategoryList.size();
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        FRG_List_Cat[] listFragments = new FRG_List_Cat[catCount];

        for( int i=0  ; i<catCount  ; i++  ){
            Bundle bundle = new Bundle();
            //int m= i+1;
            bundle.putString("FRG",MainCategoryList.get(i).getId()+"");
            listFragments[i] = new FRG_List_Cat();
            listFragments[i].setArguments(bundle);

            fragmentAdapter.addFragment(listFragments[i],MainCategoryList.get(i).getName());

        }

        

        viewPager.setAdapter(fragmentAdapter);
        viewPager.computeScroll();

        viewPager.setCurrentItem(Integer.parseInt(b.getString("ITEM")));

    }

}
