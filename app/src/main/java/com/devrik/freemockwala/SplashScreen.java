package com.devrik.freemockwala;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.devrik.freemockwala.Activity.HomeActivity;
import com.devrik.freemockwala.others.APPCONSTANT;
import com.devrik.freemockwala.others.ShareHelper;

public class SplashScreen extends AppCompatActivity {

    String USERID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        USERID = ShareHelper.getKey(SplashScreen.this, APPCONSTANT.USERID);
        Log.e("kdjfgs",USERID);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (USERID.equals(""))
                {
                    Intent intent = new Intent(SplashScreen.this, SignInActivity.class);
                    startActivity(intent);
                    finish();

                }else
                {
                    Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
            }},2000);
    }
}