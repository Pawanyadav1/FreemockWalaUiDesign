package com.devrik.freemockwala.others;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.devrik.freemockwala.Activity.ViewTestMyPerformance;
import com.devrik.freemockwala.Model.Mymodel;
import com.devrik.freemockwala.Model.TestModel;
import com.devrik.freemockwala.R;
import com.devrik.freemockwala.TestCategoryActivity;

import java.util.ArrayList;

public class Show_Test_Adapter extends RecyclerView.Adapter<Show_Test_Adapter.ViewHolder> {
    Context context;
    ArrayList<TestModel> testModelArrayList;

    public Show_Test_Adapter(Context context, ArrayList<TestModel> testModelArrayList) {
        this.context = context;
        this.testModelArrayList = testModelArrayList;
    }

    @Override
    public Show_Test_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ssctests,parent,false);
        Show_Test_Adapter.ViewHolder viewHolder=new Show_Test_Adapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TestModel  Testmodel = testModelArrayList.get(position);

        if (!Testmodel.equals("")) {
            holder.Txt_test1.setText(Testmodel.getTestName());


            try {
                Glide.with(context).load(Testmodel.getExam_id()+Testmodel.getImage())
                        .placeholder(R.drawable.ssc).override(50, 50)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.img_test);

                holder.Txt_test1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        context.startActivity(new Intent(context, ViewTestMyPerformance.class));
                    }
                });



            } catch (Exception e) {
                e.printStackTrace();
                Log.e("dgfadf", e.getMessage());

            }


        }

    }

    @Override
    public int getItemCount() {
        return testModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_test;
        TextView Txt_test1;
        TextView Text_avl;
        TextView Txt_lang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_test=itemView.findViewById(R.id.img_test);
            Txt_test1=itemView.findViewById(R.id.Txt_test1);
            Text_avl = itemView.findViewById(R.id.Text_avl);
            Txt_lang = itemView.findViewById(R.id.Txt_lang);
        }
    }

}
