package com.devrik.freemockwala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.devrik.freemockwala.others.API;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {
TextView  ragister;
EditText Emailid,password;
Button sin_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Emailid = findViewById(R.id.Emailid);
        password = findViewById(R.id.password);
        ragister = findViewById(R.id.ragister);
        sin_button = findViewById(R.id.sin_button);


        ragister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        sin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    sign_in();
            }
        });

    }

  public void sign_in(){
        AndroidNetworking.post(API.signIn)
                .addBodyParameter("email",Emailid.getText().toString().trim())
                .addBodyParameter("password",password.getText().toString().trim())
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("fghghg", response.toString());

                        try {
                            if(response.getString("message").equals("Signin Successfully"))
                            {
                                startActivity(new Intent(SignInActivity.this,HomeExamActivity.class));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("fghghg", e.getMessage());
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("fghghg", anError.getMessage());

                    }
                });


    }
}