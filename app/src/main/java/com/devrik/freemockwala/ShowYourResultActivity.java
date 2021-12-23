package com.devrik.freemockwala;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.devrik.freemockwala.Activity.HomeActivity;
import com.devrik.freemockwala.Model.Showresultmodel;
import com.devrik.freemockwala.others.API;
import com.devrik.freemockwala.others.APPCONSTANT;
import com.devrik.freemockwala.others.PageAdapter;
import com.devrik.freemockwala.others.ShareHelper;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowYourResultActivity extends AppCompatActivity {

String Exam_id="",Test_id="",User_id="",Section_id="";
    TabLayout tablayout1;
    TabItem tab1,tab2,tab3;
    ViewPager V_pager;
    Button btn_retake,btn_done;
    TextView total_mask,give_mask;
    ImageView img_date,img_time,ing_percent;
    ArrayList<Showresultmodel> myModelArrayList = new ArrayList<>();
      PageAdapter pageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_your_result);

        tablayout1 = (TabLayout)findViewById(R.id.tablayout1);
        tab1 = (TabItem)findViewById(R.id.tab1);
        tab2 = (TabItem)findViewById(R.id.tab2);
        tab3 = (TabItem)findViewById(R.id.tab3);
        total_mask= findViewById(R.id.total_mask);
        give_mask= findViewById(R.id.give_mask);
        img_date = findViewById(R.id.img_date);
        btn_retake=findViewById(R.id.btn_retake);
        btn_done=findViewById(R.id.btn_done);
        img_time = findViewById(R.id.img_time);
        ing_percent = findViewById(R.id.ing_percent);
        V_pager =(ViewPager)findViewById(R.id.V_pager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(),tablayout1.getTabCount());
        V_pager.setAdapter(pageAdapter);

        showResult();

        Exam_id = ShareHelper.getKey(ShowYourResultActivity.this, APPCONSTANT.exam_id);
        Test_id= ShareHelper.getKey(ShowYourResultActivity.this,APPCONSTANT.test_id);
        User_id=ShareHelper.getKey(ShowYourResultActivity.this,APPCONSTANT.USERID);
        Section_id=ShareHelper.getKey(ShowYourResultActivity.this,APPCONSTANT.section_id);
        Log.e("dlksgjf",Section_id );
        btn_retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowYourResultActivity.this,AllTestCategoryActivity.class));
            }
        });
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowYourResultActivity.this, HomeActivity.class));
            }
        });

        tablayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                V_pager.setCurrentItem(tab.getPosition());

                if(tab.getPosition()==0||tab.getPosition()==1|| tab.getPosition()==2)
                    pageAdapter.notifyDataSetChanged();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        V_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout1));
        // listen for  scroll and page change
        solution();

    }
    public void showResult(){
        AndroidNetworking.post(API.showResult)
                .addBodyParameter("exam_id",Exam_id)
                .addBodyParameter("test_id",Test_id)
                .addBodyParameter("user_id",User_id)
                .setPriority(Priority.HIGH)
                .setTag("showResult")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("message").equals("successfull")){

                                JSONArray jsonArray = new JSONArray(response.getString("data"));
                                for (int i = 0; i <jsonArray.length() ; i++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Showresultmodel showresultmodel = new Showresultmodel();
                                    showresultmodel.setObtain_marks(jsonObject.getString("obtain_marks"));
                                    showresultmodel.setTotal_marks(jsonObject.getString("total_marks"));
                                    showresultmodel.setPercentage(jsonObject.getString("percentage"));
                                    showresultmodel.setDate(jsonObject.getString("date"));
                                    showresultmodel.setTime(jsonObject.getString("time"));
                                    showresultmodel.setExam_id(jsonObject.getString("exam_id"));
                                    showresultmodel.setTest_id(jsonObject.getString("test_id"));
                                    showresultmodel.setSignup_id(jsonObject.getString("signup_id"));
                                    Toast.makeText(ShowYourResultActivity.this,"done"+response.getString("massage"),Toast.LENGTH_SHORT).show();
                                }

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("jbsjbv", e.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("fjkjdshg",anError.getMessage());

                    }
                });
    }


    public void solution()
    {
        AndroidNetworking.post(API.showAnswerSheet)
                .addBodyParameter("exam_id",Exam_id)
                .addBodyParameter("test_id",Test_id)
                .addBodyParameter("section_id",Section_id)
                .setPriority(Priority.HIGH)
                .setTag("Solutions")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("message").equals("successfull")){
                                JSONArray jsonArray = new JSONArray(response.getString("data"));
                                for (int i = 0; i <jsonArray.length() ; i++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                                }

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("sklvcx",e.getMessage());
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("jfvjhb",anError.getMessage());
                    }
                });



    }


}