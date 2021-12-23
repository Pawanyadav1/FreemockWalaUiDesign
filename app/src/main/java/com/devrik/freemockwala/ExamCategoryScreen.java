package com.devrik.freemockwala;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.devrik.freemockwala.Activity.HomeActivity;
import com.devrik.freemockwala.Model.Exammodel;
import com.devrik.freemockwala.others.API;
import com.devrik.freemockwala.others.Show_Exam_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExamCategoryScreen extends AppCompatActivity {
    public Context context = ExamCategoryScreen.this;

    ImageView back_btn;
    RecyclerView.LayoutManager layoutManager ;
    ArrayList<Exammodel> myModelArrayList = new ArrayList<>();
    RecyclerView rvExamShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_category_screen);
        rvExamShow = findViewById(R.id.rvExamShow);
        back_btn = findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExamCategoryScreen.this, HomeActivity.class));
            }
        });

        show_Exam();

    }

    public void show_Exam() {
       AndroidNetworking.post(API.showExam)
       // AndroidNetworking.post("https://www.freemockwala.com/api/process.php?action=showExam")
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
                                Exammodel myModel = new Exammodel();
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