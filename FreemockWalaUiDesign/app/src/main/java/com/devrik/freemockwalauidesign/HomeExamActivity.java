package com.devrik.freemockwala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeExamActivity extends AppCompatActivity {

    ImageView im_go1,im_go,im_practice,bank1,bank2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_exam);

        im_practice = findViewById(R.id.im_practice);
        im_go = findViewById(R.id.im_go);
        im_go1= findViewById(R.id.im_go1);
        bank1 =findViewById(R.id.bank1);
        bank2 = findViewById(R.id.bank2);
        im_practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeExamActivity.this,ExamCategoryScreen.class);
                startActivity(intent);
            }
        });

        im_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeExamActivity.this,ExamCategoryScreen.class);
                startActivity(intent);
            }
        });

        im_go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeExamActivity.this,ExamCategoryScreen.class);
                startActivity(intent);
            }
        });
        bank1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeExamActivity.this,ExamCategoryScreen.class));
            }
        });
        bank2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeExamActivity.this,ExamCategoryScreen.class));
            }
        });


    }
}