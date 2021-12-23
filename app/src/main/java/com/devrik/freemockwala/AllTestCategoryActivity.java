package com.devrik.freemockwala;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.devrik.freemockwala.Model.SectionModel;
import com.devrik.freemockwala.others.API;
import com.devrik.freemockwala.others.APPCONSTANT;
import com.devrik.freemockwala.others.ShareHelper;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AllTestCategoryActivity extends AppCompatActivity {
    public Context context = AllTestCategoryActivity.this;

    ImageView img_back;
    TextView txt_testName,ques,marks,time_min;
    MaterialButton btn_continue;
    String USERID="";
    String Exam_Id="";
    String Test_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_test_category);

        img_back = findViewById(R.id.img_back);
        btn_continue = findViewById(R.id.btn_continue);
        txt_testName = findViewById(R.id.txt_testName);
        ques= findViewById(R.id.ques);
        marks =findViewById(R.id.marks);
        time_min=findViewById(R.id.time_min);

        USERID =ShareHelper.getKey(AllTestCategoryActivity.this,APPCONSTANT.USERID);
        Log.e("kjxhcx",USERID);
        Exam_Id = ShareHelper.getKey(AllTestCategoryActivity.this, APPCONSTANT.exam_id);
        Log.e("kjxhcx",Exam_Id);
        Test_id = ShareHelper.getKey(AllTestCategoryActivity.this,APPCONSTANT.test_id);
        Log.e("kjxhcx",Test_id);
        TestDetails();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllTestCategoryActivity.this,TestCategoryActivity.class));
                finish();
            }
        });
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllTestCategoryActivity.this,QuestionScreenActivity.class));

            }
        });
    }
    public void  TestDetails(){
        Log.e("fgg",USERID);
        AndroidNetworking.post(API.showTestDetails)
                .addBodyParameter("id",USERID)
                .addBodyParameter("exam_id",Exam_Id)
                .addBodyParameter("test_id",Test_id)
                .setPriority(Priority.HIGH)
                .setTag("showTestDetails")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("jcvzkxg",response.toString());
                        try {
                            if (response.getString("message").equals("successfull")) {

                                txt_testName.setText(response.getString("test Name"));
                                ques.setText(response.getString("total_question"));
                                marks.setText(response.getString("total_marks"));
                                time_min.setText(response.getString("time"));

                            }
                            JSONArray jsonArray= new JSONArray(response.getString("sectionName"));
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.optJSONObject(i);
                                SectionModel sectionModel=new SectionModel();
                                sectionModel.setId(jsonObject.getString("id"));
                                sectionModel.setExam_id(jsonObject.getString("exam_id"));
                                sectionModel.setTest_id(jsonObject.getString("test_id"));
                                sectionModel.setSection_id(jsonObject.optString("section_id"));
                               // ShareHelper.putkey(AllTestCategoryActivity.this,APPCONSTANT.section_id,jsonObject.getString("section_id"));
                               // Log.e("jvxlk",jsonObject.getString("section_id"));
                            }


                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("dsjfgkj",e.getMessage());
                        }

                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("ddfsaa",anError.getMessage());

                    }
                });
    }
}