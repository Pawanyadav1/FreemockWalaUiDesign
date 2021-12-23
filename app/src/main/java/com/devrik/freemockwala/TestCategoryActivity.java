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
import com.devrik.freemockwala.Model.TestModel;
import com.devrik.freemockwala.others.API;
import com.devrik.freemockwala.others.APPCONSTANT;
import com.devrik.freemockwala.others.ShareHelper;
import com.devrik.freemockwala.others.Show_Test_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TestCategoryActivity extends AppCompatActivity {
    ImageView back_btn;
    public Context context = TestCategoryActivity.this;

    RecyclerView.LayoutManager layoutManager ;
    ArrayList<TestModel> testModelArrayList = new ArrayList<>();
    TestModel testModel = new TestModel();
    RecyclerView rvTest;
    String Exam_Id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_category);

        rvTest = findViewById(R.id.rvTest);
        back_btn =findViewById(R.id.back_btn);

        Exam_Id = ShareHelper.getKey(TestCategoryActivity.this,APPCONSTANT.exam_id);
        Log.e("examid",Exam_Id);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestCategoryActivity.this, HomeActivity.class));
            }
        });

        show_Test();

    }

    public void show_Test() {
        AndroidNetworking.post(API.showTest)
                .addBodyParameter("exam_id",Exam_Id)
                .setTag("showtest")
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

                                TestModel testModel = new TestModel();
                                testModel.setId(jsonObject.getString("id"));
                                testModel.setTestName(jsonObject.getString("testName"));
                                testModel.setImage(jsonObject.getString("image"));
                                testModel.setPath(jsonObject.getString("path"));
                                testModelArrayList.add(testModel);
                            }

                            rvTest.setHasFixedSize(true);
                            layoutManager = new LinearLayoutManager(TestCategoryActivity.this, RecyclerView.VERTICAL, false);
                            rvTest.setLayoutManager(layoutManager);
                            Show_Test_Adapter adapter = new Show_Test_Adapter(context,testModelArrayList);
                            rvTest.setAdapter(adapter);

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
