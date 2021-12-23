package com.devrik.freemockwala.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.devrik.freemockwala.R;
import com.devrik.freemockwala.others.API;
import com.devrik.freemockwala.others.APPCONSTANT;
import com.devrik.freemockwala.others.ShareHelper;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

public class changepasswordActivity extends AppCompatActivity {
    ImageView back_btn;
    EditText current_password,new_password,confpassword;
    MaterialButton sp_button;
    String USERID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        back_btn= findViewById(R.id.back_btn);
        current_password= findViewById(R.id.current_password);
        new_password= findViewById(R.id.new_password);
        confpassword= findViewById(R.id.confpassword);
        sp_button= findViewById(R.id.sp_button);

        USERID = ShareHelper.getKey(changepasswordActivity.this, APPCONSTANT.USERID);
        Log.e("kdjdfsfgs",USERID);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(changepasswordActivity.this, HomeActivity.class));
            }
        });

        sp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValidateDetail();
            }
        });

    }
    private void ValidateDetail() {
        if (current_password.getText().toString().equals("")) {
            current_password.setError("please enter your current password");
            current_password.requestFocus();

        } else if(new_password.getText().toString().equals("")){
            new_password.setError("enter your new password");
            new_password.requestFocus();

        }else if(confpassword.getText().toString().equals("")){
            confpassword.setError("please enter confirm password");
            confpassword.requestFocus();

        }
        else {

            changePassword();

        }
    }

    public void changePassword() {
        AndroidNetworking.post(API.changePassword)
                .addBodyParameter("id",USERID)
                .addBodyParameter("old_password", current_password.getText().toString().trim())
                .addBodyParameter("new_password", new_password.getText().toString().trim())
                .addBodyParameter("confirm_password", confpassword.getText().toString().trim())
                .setTag("changePassword")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("show_error", response.toString() );
                        try {
                            if (response.getString("messgae").equals("Changed Successfully")) {

                                JSONObject jsonObject = new JSONObject(response.getString("data"));
                                for (int i = 0; i < jsonObject.length(); i++) {

                                    startActivity(new Intent(changepasswordActivity.this,HomeActivity.class));
                                    Toast.makeText(changepasswordActivity.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();

                                }
                                }

                            } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("unsucedsss", anError.getMessage());

                    }
                });
    }

}