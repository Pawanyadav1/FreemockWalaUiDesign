package com.devrik.freemockwala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.devrik.freemockwala.Model.Mymodel;
import com.devrik.freemockwala.others.API;
import com.devrik.freemockwala.others.APPCONSTANT;
import com.devrik.freemockwala.others.ShareHelper;
import com.devrik.freemockwala.others.Show_Exam_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExamCategoryScreen extends AppCompatActivity {
    public Context context = ExamCategoryScreen.this;


    RecyclerView.LayoutManager layoutManager ;
    ArrayList<Mymodel> myModelArrayList = new ArrayList<>();
    RecyclerView rvExamShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_category_screen);
        rvExamShow = findViewById(R.id.rvExamShow);

        show_Exam();

    }

    public void show_Exam() {
        AndroidNetworking.post(API.showExam)
                .setTag("showexam")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("safdadfa", response.toString());
                        try {
                            JSONArray jsonArray = new JSONArray(response.getString("data"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.e("dfzfscc",response.toString());

                                Mymodel myModel = new Mymodel();
                                myModel.setExamLogo(jsonObject.getString("examLogo"));
                                myModel.setPath(jsonObject.getString("path"));
                                myModel.setExamName(jsonObject.getString("examName"));
                                myModel.setId(jsonObject.getString("id"));
                                myModelArrayList.add(myModel);

                            }


                            rvExamShow.setHasFixedSize(true);
                            layoutManager = new LinearLayoutManager(ExamCategoryScreen.this, RecyclerView.VERTICAL, false);
                            rvExamShow.setLayoutManager(layoutManager);
                            Show_Exam_Adapter adapter = new Show_Exam_Adapter(context, myModelArrayList);
                            rvExamShow.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("dgfffdf", e.getMessage());
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("fhsdds", anError.getMessage());

                    }
                });

    }
}