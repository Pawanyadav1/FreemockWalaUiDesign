package com.devrik.freemockwala;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.devrik.freemockwala.Activity.HomeActivity;
import com.devrik.freemockwala.others.API;
import com.devrik.freemockwala.others.APPCONSTANT;
import com.devrik.freemockwala.others.ShareHelper;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {
TextView  ragister;
String ID="";
EditText Emailid,password;
MaterialButton sin_button;

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
             Log.e("paads",toString());

                    sign_in();

            }
        });

    }

  public void sign_in(){
      Log.e("dsjn", String.valueOf(Emailid));
      Log.e("djah", String.valueOf(password));

        AndroidNetworking.post(API.signIn)
                .addBodyParameter("id",ID)
                .addBodyParameter("email",Emailid.getText().toString().trim())
                .addBodyParameter("password",password.getText().toString().trim())
                .setPriority(Priority.HIGH)
                .setTag("signin")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("fghhghg", response.toString());
                        try {
                            if(response.getString("message").equals("Signin Successfully"))
                            {
                                JSONObject jsonObject = new JSONObject(response.getString("data"));
                                for (int i = 0; i < jsonObject.length(); i++) {


                                    Toast.makeText(SignInActivity.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignInActivity.this, HomeActivity.class));

                                    ShareHelper.putkey(SignInActivity.this, APPCONSTANT.USERID, jsonObject.getString("id"));
                                    Log.e("fgjilh", response.getString("id"));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("fghghg", e.getMessage());
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("fghgjjhg", anError.getMessage());
                    }
                });
    }
}