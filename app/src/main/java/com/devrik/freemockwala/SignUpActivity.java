package com.devrik.freemockwala;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.devrik.freemockwala.others.API;
import com.devrik.freemockwala.others.APPCONSTANT;
import com.devrik.freemockwala.others.ShareHelper;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

MaterialButton sp_button;
EditText firstname,lastname,Emailid,Mobile,password,confpassword;
ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        Emailid = findViewById(R.id.Emailid);
        Mobile = findViewById(R.id.Mobile);
        password = findViewById(R.id.password);
        confpassword = findViewById(R.id.confpassword);
        progressbar =findViewById(R.id.progressbar);
        sp_button = findViewById(R.id.sp_button);

        sp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValidateDetail();
            }
        });

    }

    private void ValidateDetail() {
        if (firstname.getText().toString().equals("")) {
            firstname.setError("please enter your first name");
            firstname.requestFocus();

        } else if(lastname.getText().toString().equals("")){
            lastname.setError("enter your last name");
            lastname.requestFocus();

        }else if(Emailid.getText().toString().equals("")){
            Emailid.setError("please enter your email");
            Emailid.requestFocus();

        }else if (Mobile.getText().toString().equals("")) {
            Mobile.setError("enter mobile number");
            Mobile.requestFocus();

        } else if (password.getText().toString().equals("")) {
            password.setError("please enter password");
            password.requestFocus();

        } else if(confpassword.getText().toString().equals("")) {
            confpassword.setError("please enter confirm password");
            confpassword.requestFocus();

        } else {

            sign_up();

        }
    }
    public void sign_up() {
        AndroidNetworking.post(API.signup)
                .addBodyParameter("firstName", firstname.getText().toString().trim())
                .addBodyParameter("lastName", lastname.getText().toString().trim())
                .addBodyParameter("mobile", Mobile.getText().toString().trim())
                .addBodyParameter("email", Emailid.getText().toString().trim())
                .addBodyParameter("password", password.getText().toString().trim())
                .addBodyParameter("confirmPassword", confpassword.getText().toString().trim())
                .setTag("signup")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("show_error", response.toString() );
                        progressbar.setVisibility(View.GONE);
                        try {
                            if (response.getString("message").equals("Registered Successfully")) {
                                JSONArray array=new JSONArray(response.getString("data"));
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject jsonObject = array.optJSONObject(i);

                                        Toast.makeText(SignUpActivity.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
                                        ShareHelper.putkey(SignUpActivity.this, APPCONSTANT.USERID,jsonObject.getString("id"));
                                    Log.e("gfrgfgfdgfg", jsonObject.getString("id") );

                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("uccess", e.getMessage());
                            progressbar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("unsucess", anError.getMessage());
                        progressbar.setVisibility(View.GONE);
                    }
                });
    }
}