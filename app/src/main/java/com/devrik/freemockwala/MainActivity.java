package com.devrik.freemockwala;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    NavigationView nev_view;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nev_view =(NavigationView) findViewById(R.id.nev_view);
        drawerLayout =(DrawerLayout)findViewById(R.id.home_bar);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nev_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                try {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            startActivity(new Intent(MainActivity.this,HomeExamActivity.class));
                            Toast.makeText(getApplicationContext(), "home penal open", Toast.LENGTH_LONG).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;


                        case R.id.navigation_profile:
                            Toast.makeText(getApplicationContext(), "profile penal open", Toast.LENGTH_LONG).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.navigation_result:
                            startActivity(new Intent(MainActivity.this,ShowYourResultActivity.class));
                            Toast.makeText(getApplicationContext(), "result penal open", Toast.LENGTH_LONG).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.navigation_test:
                            startActivity(new Intent(MainActivity.this,ExamCategoryScreen.class));
                            Toast.makeText(getApplicationContext(), "Typing test penal open", Toast.LENGTH_LONG).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                    }
                    return true;

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("fsggx",e.getMessage());
                }
                return false;
            }
        });



    }

    private void setSupportActionBar(Toolbar toolbar) {

    }
}