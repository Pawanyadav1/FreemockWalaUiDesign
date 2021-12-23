package com.devrik.freemockwala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.devrik.freemockwala.Model.Mymodel;
import com.devrik.freemockwala.Model.TestModel;
import com.devrik.freemockwala.others.API;
import com.devrik.freemockwala.others.APPCONSTANT;
import com.devrik.freemockwala.others.ShareHelper;
import com.devrik.freemockwala.others.Show_Exam_Adapter;
import com.devrik.freemockwala.others.Show_Test_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TestCategoryActivity extends AppCompatActivity {

    public Context context = TestCategoryActivity.this;
    RecyclerView.LayoutManager layoutManager ;
    ArrayList<TestModel> testModelArrayList = new ArrayList<>();
    RecyclerView rvTest;
    String Exam_Id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_category);

        Exam_Id= ShareHelper.getKey(TestCategoryActivity.this, APPCONSTANT.EXAM_ID);
        Log.e("select", Exam_Id+"");

        rvTest = findViewById(R.id.rvTest);

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
                                testModel.setTestName(jsonObject.getString("testName"));
                                testModel.setId(jsonObject.getString("exam_id"));
                                testModel.setId(jsonObject.getString("image"));
                                testModel.setId(jsonObject.getString("path"));
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
