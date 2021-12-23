package com.devrik.freemockwala.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.devrik.freemockwala.Fragment.Homefragment;
import com.devrik.freemockwala.Fragment.Profilefragment;
import com.devrik.freemockwala.Fragment.Resultfragment;
import com.devrik.freemockwala.Fragment.TypingTestfragment;
import com.devrik.freemockwala.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
    Context context = this;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.item_container,new Homefragment()).commit();
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.item_container,new Homefragment()).commit();
                break;
            case R.id.navigation_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.item_container,new Profilefragment()).commit();
                break;
            case R.id.navigation_result:
                getSupportFragmentManager().beginTransaction().replace(R.id.item_container,new Resultfragment()).commit();
                break;
            case R.id.navigation_test:
                getSupportFragmentManager().beginTransaction().replace(R.id.item_container,new TypingTestfragment()).commit();
                break;

        }
        return true;
    }



}