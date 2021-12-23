package com.devrik.freemockwala;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.devrik.freemockwala.Model.Questionmodel;
import com.devrik.freemockwala.Model.SectionModel;
import com.devrik.freemockwala.others.API;
import com.devrik.freemockwala.others.APPCONSTANT;
import com.devrik.freemockwala.others.ShareHelper;
import com.devrik.freemockwala.others.Show_section_test_Adapter;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class QuestionScreenActivity extends AppCompatActivity {
    public Context context = QuestionScreenActivity.this;
    RecyclerView.LayoutManager layoutManager ;
    ArrayList<SectionModel> sectionModelArrayList = new ArrayList<>();

    TextView txt_que,choiceA,choiceB,choiceC,choiceD,test_name,que_num,time_txt;
    RadioButton select_a,select_b,select_c,select_d;

   MaterialButton priview_btn,save_next_btn;
   Button submit_btn,clear_btn;
   ImageView back_btn;
    RecyclerView rv_section;
    String ExamID="",TestID="",USERID="",Section_id="",Question_id="",Answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);

        USERID =ShareHelper.getKey(QuestionScreenActivity.this,APPCONSTANT.USERID);
        Log.e("fkvj",USERID);
        ExamID = ShareHelper.getKey(QuestionScreenActivity.this, APPCONSTANT.exam_id);
        Log.e("fkvj",ExamID);
        TestID = ShareHelper.getKey(QuestionScreenActivity.this,APPCONSTANT.test_id);
        Log.e("fkvj",TestID);
        Section_id=ShareHelper.getKey(QuestionScreenActivity.this,APPCONSTANT.section_id);
        Log.e("fkvj",Section_id);
        Question_id=ShareHelper.getKey(QuestionScreenActivity.this,APPCONSTANT.question_id);
        Log.e("fkvj",Question_id);
        Answer=ShareHelper.getKey(QuestionScreenActivity.this,APPCONSTANT.Ans);
        Log.e("fkvj",Answer);

        priview_btn =findViewById(R.id.priview_btn);
        save_next_btn = findViewById(R.id.save_next_btn);
        submit_btn = findViewById(R.id.submit_btn);
        rv_section=findViewById(R.id.rv_section);
        clear_btn = findViewById(R.id.clear_btn);
        back_btn = findViewById(R.id.back_btn);
        txt_que = findViewById(R.id.txt_que);
        choiceA = findViewById(R.id.choiceA);
        choiceB = findViewById(R.id.choiceB);
        choiceC = findViewById(R.id.choiceC);
        choiceD = findViewById(R.id.choiceD);
        que_num = findViewById(R.id.que_num);
        test_name = findViewById(R.id.test_name);
        time_txt = findViewById(R.id.time_txt);
        select_a = findViewById(R.id.select_a);
        select_b = findViewById(R.id.select_b);
        select_c = findViewById(R.id.select_c);
        select_d = findViewById(R.id.select_d);

        // Time is in millisecond so 50sec = 50000 I have used
        // countdown Interveal is 1sec = 1000 I have used
        new CountDownTimer(600000,1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                time_txt.setText(f.format(min) + ":" + f.format(sec));
            }
            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                time_txt.setText("00:00");
            }
        }.start();

        TestDetails();
        showQuestions();

        select_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                select_a.setChecked(true);
                select_b.setChecked(false);
                select_c.setChecked(false);
                select_d.setChecked(false);
            }
        });
        select_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_a.setChecked(false);
                select_b.setChecked(true);
                select_c.setChecked(false);
                select_d.setChecked(false);
            }
        });
        select_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_a.setChecked(false);
                select_b.setChecked(false);
                select_c.setChecked(true);
                select_d.setChecked(false);
            }
        });
        select_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_a.setChecked(false);
                select_b.setChecked(false);
                select_c.setChecked(false);
                select_d.setChecked(true);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionScreenActivity.this,TestCategoryActivity.class);
                startActivity(intent);
            }
        });

        priview_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            priview();

            }
        });
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              clearResponse();
            }
        });
        save_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveAndNext();
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuestionScreenActivity.this,ShowYourResultActivity.class));
            }
        });
    }
    public void TestDetails(){
        AndroidNetworking.post(API.showTestDetails)
                .addBodyParameter("id",USERID)
                .addBodyParameter("exam_id",ExamID)
                .addBodyParameter("test_id",TestID)
                .setPriority(Priority.HIGH)
                .setTag("showTestDetails")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("jcvzffkxg", response.toString());
                        try {
                            if (response.optString("message").equals("successfull")) {
                                JSONArray jsonArray = new JSONArray(response.getString("sectionName"));
                                  for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                                    SectionModel sectionModel = new SectionModel();
                                    sectionModel.setId(jsonObject.optString("id"));
                                    sectionModel.setExam_id(jsonObject.optString("exam_id"));
                                    sectionModel.setTest_id(jsonObject.optString("test_id"));
                                    sectionModel.setSection_id(jsonObject.optString("section_id"));
                                    sectionModel.setSectionName(jsonObject.optString("sectionName"));
                                      time_txt.setText(jsonObject.optString("time"));
                                      que_num.setText(jsonObject.optString("total_question"));
                                    sectionModelArrayList.add(sectionModel);
                                    showQuestions();
                                }
                            rv_section.setHasFixedSize(true);
                            layoutManager = new LinearLayoutManager(QuestionScreenActivity.this, RecyclerView.HORIZONTAL, false);
                            rv_section.setLayoutManager(layoutManager);
                            Show_section_test_Adapter adapter = new Show_section_test_Adapter(context, sectionModelArrayList);
                             rv_section.setAdapter(adapter);
                        }
                    } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("dsjfgskj",e.getMessage());
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("ddfzasaa",anError.getMessage());

                    }
                });
    }
    public void showQuestions() {


        Section_id=ShareHelper.getKey(QuestionScreenActivity.this,APPCONSTANT.section_id);

        Log.e("hjfgjfgjgfj", ExamID);
        Log.e("hjfgjfgjgfj", TestID);
        Log.e("hjfgjfgjgfj", Section_id);
        AndroidNetworking.post(API.showQuestionsAndChoices)
                .addBodyParameter("exam_id",ExamID)
                .addBodyParameter("test_id", TestID)
                .addBodyParameter("section_id",Section_id)
                .setPriority(Priority.HIGH)
                .setTag("showquestion")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("jshghpkf",response.toString());
                        try {
                            if (response.getString("message").equals("successfull")) {

                                JSONArray jsonArray = new JSONArray(response.getString("data"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.optJSONObject(i);

                                    Questionmodel questionmodel = new Questionmodel();
                                    questionmodel.setId(jsonObject.optString("id"));
                                    txt_que.setText(jsonObject.optString("question"));
                                    choiceA.setText(jsonObject.optString("choiceA"));
                                    choiceB.setText(jsonObject.optString("choiceB"));
                                    choiceC.setText(jsonObject.optString("choiceC"));
                                    choiceD.setText(jsonObject.optString("choiceD"));
                                    test_name.setText(jsonObject.optString("testName"));
                                    time_txt.setText(jsonObject.optString("time"));
                                    que_num.setText(jsonObject.optString("total_question"));
                                    questionmodel.setAns(jsonObject.optString("ans"));
                                    ShareHelper.putkey(context, APPCONSTANT.question_id,questionmodel.getId());
                                    Log.e("svkrjc", questionmodel.getId());
                                    ShareHelper.putkey(context, APPCONSTANT.Ans,questionmodel.getAns());
                                    Log.e("svkrjc", questionmodel.getAns());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("fgdfgdfgdfg",e.getMessage() );
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("fgdfggdfg",anError.getMessage());
                    }
                });
    }
    public void clearResponse(){

        Log.e("dfgkhfgkjhdfg",ExamID);
        Log.e("dfgkhfgkjhdfg",TestID);
        Log.e("dfgkhfgkjhdfg",USERID);
        Log.e("dfgkhfgkjhdfg",Section_id);
        Log.e("dfgkhfgkjhdfg",Question_id);

        AndroidNetworking.post(API.clearResponse)
                .addBodyParameter("exam_id", ExamID)
                .addBodyParameter("test_id", TestID)
                .addBodyParameter("user_id",USERID)
                .addBodyParameter("section_id",Section_id)
                .addBodyParameter("question_id",Question_id)
                .setPriority(Priority.HIGH)
                .setTag("clearResponse")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("kjdach", response.toString());
                        try {
                            if (response.getString("message").equals("successfull")){
                                select_a.setChecked(false);
                                select_b.setChecked(false);
                                select_c.setChecked(false);
                                select_d.setChecked(false);
                                JSONArray jsonArray = new JSONArray(response.getString("QuestionData"));
                                for (int i = 0; i <jsonArray.length() ; i++) {
                                    JSONObject jsonObject= jsonArray.optJSONObject(i);
                                    Questionmodel questionmodel = new Questionmodel();
                                    questionmodel.setId(jsonObject.getString("id"));
                                    questionmodel.setExam_id(jsonObject.getString("exam_id"));
                                    questionmodel.setTest_id(jsonObject.getString("test_id"));
                                    questionmodel.setSection_id(jsonObject.getString("section_id"));
                                    questionmodel.setQuestion(jsonObject.getString("question"));
                                    questionmodel.setChoiceA(jsonObject.getString("choiceA"));
                                    questionmodel.setChoiceB(jsonObject.getString("choiceB"));
                                    questionmodel.setChoiceC(jsonObject.getString("choiceC"));
                                    questionmodel.setChoiceD(jsonObject.getString("choiceD"));
                                    questionmodel.setAns(jsonObject.getString("ans"));
                                    questionmodel.setTestName(jsonObject.getString("tastname"));
                                    questionmodel.setTime(jsonObject.getString("time"));
                                    questionmodel.setTotal_question(jsonObject.getString("total_question"));

                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("fjghvjxh",e.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("ghifsh",anError.getMessage());
                    }
                });


    }

    public void  priview(){
        Log.e("bdkj",USERID);
        Log.e("bdkj",ExamID);
        Log.e("bdkj",TestID);
        Log.e("bdkj",Section_id);
        Log.e("bdkj",Question_id);
        Log.e("bdkj",Answer);
        AndroidNetworking.post(API.markForReview)
                .addBodyParameter("exam_id", ExamID)
                .addBodyParameter("test_id", TestID)
                .addBodyParameter("user_id",USERID)
                .addBodyParameter("section_id",Section_id)
                .addBodyParameter("question_id",Question_id)
                .addBodyParameter("ans",Answer)
                .setPriority(Priority.HIGH)
                .setTag("Review")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("dfsdskj",response.toString());
                        try {
                            if (response.getString("message").equals("successfull"))
                            {
                                JSONArray jsonArray = new JSONArray(response.getString("QuestionData"));
                                for(int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.optJSONObject(i);

                                    Questionmodel questionmodel = new Questionmodel();
                                    questionmodel.setId(jsonObject.getString("id"));
                                    questionmodel.setExam_id(jsonObject.getString("exam_id"));
                                    questionmodel.setTest_id(jsonObject.getString("test_id"));
                                    questionmodel.setQuestion(jsonObject.getString("question"));
                                    questionmodel.setChoiceA(jsonObject.getString("choiceA"));
                                    questionmodel.setChoiceB(jsonObject.getString("choiceB"));
                                    questionmodel.setChoiceC(jsonObject.getString("choiceC"));
                                    questionmodel.setChoiceD(jsonObject.getString("choiceD"));
                                    questionmodel.setAns(jsonObject.getString("ans"));
                                    questionmodel.setTime(jsonObject.getString("time"));
                                    questionmodel.setTotal_question(jsonObject.getString("total_question"));
                                    showQuestions();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("gflja",e.getMessage());
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("mfshdfsg", anError.getMessage() );
                    }
                });
    }
    public void saveAndNext() {
        Log.e("bdkji",USERID);
        Log.e("bdkji",ExamID);
        Log.e("bdkji",TestID);
        Log.e("bdkji",Section_id);
        Log.e("bdkji",Question_id);
        Log.e("bdkji",Answer);
        AndroidNetworking.post(API.saveAndNext)
                .addBodyParameter("exam_id", ExamID)
                .addBodyParameter("test_id", TestID)
                .addBodyParameter("section_id",Section_id)
                .addBodyParameter("user_id",USERID)
                .addBodyParameter("question_id",Question_id)
                .addBodyParameter("ans",Answer)
                .setPriority(Priority.HIGH)
                .setTag("save and next")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("fkhddfsk",response.toString());
                        try {
                            if (response.getString("message").equals("successfull")){
                                JSONArray jsonArray = new JSONArray(response.getString("NextQuestionData"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                                    Questionmodel questionmodel= new Questionmodel();

                                    questionmodel.setId(jsonObject.getString("id"));
                                    questionmodel.setExam_id(jsonObject.getString("exam_id"));
                                    questionmodel.setTest_id(jsonObject.getString("test_id"));
                                    questionmodel.setQuestion(jsonObject.getString("question"));
                                    questionmodel.setChoiceA(jsonObject.getString("choiceA"));
                                    questionmodel.setChoiceB(jsonObject.getString("choiceB"));
                                    questionmodel.setChoiceC(jsonObject.getString("choiceC"));
                                    questionmodel.setChoiceD(jsonObject.getString("choiceD"));
                                    questionmodel.setAns(jsonObject.getString("ans"));
                                    questionmodel.setTime(jsonObject.getString("time"));
                                    questionmodel.setTotal_question(jsonObject.getString("total_question"));

                                    showQuestions();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("dfshkjg", e.getMessage());
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("fldsjhg",anError.getMessage() );
                    }
                });
    }

   /* public void showQuestions(String question_id, String section_id) {


    }*/
}