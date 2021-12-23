package com.devrik.freemockwala.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.devrik.freemockwala.Activity.HomeActivity;
import com.devrik.freemockwala.R;
import com.devrik.freemockwala.others.APPCONSTANT;
import com.devrik.freemockwala.others.ShareHelper;

public class Resultfragment extends Fragment {

    String Exam_id = "", Test_id = "", User_id = "", Section_id = "";
    Button btn_done;
    ImageView img_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_fragment, container, false);


        btn_done = view.findViewById(R.id.btn_done);
        img_back = view.findViewById(R.id.img_back);

        Exam_id = ShareHelper.getKey(getContext(), APPCONSTANT.exam_id);
        Test_id = ShareHelper.getKey(getContext(), APPCONSTANT.test_id);
        User_id = ShareHelper.getKey(getContext(), APPCONSTANT.USERID);
        Section_id = ShareHelper.getKey(getContext(), APPCONSTANT.section_id);
        Log.e("dlksgjf", Section_id);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HomeActivity.class));
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HomeActivity.class));
            }
        });



return view;

    }
}

