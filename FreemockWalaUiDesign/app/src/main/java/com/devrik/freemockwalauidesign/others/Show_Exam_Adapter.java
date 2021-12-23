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
import com.devrik.freemockwala.ExamCategoryScreen;
import com.devrik.freemockwala.Model.Mymodel;
import com.devrik.freemockwala.R;
import com.devrik.freemockwala.TestCategoryActivity;

import java.util.ArrayList;

public class Show_Exam_Adapter extends RecyclerView.Adapter<Show_Exam_Adapter.ViewHolder> {
        Context context;
        ArrayList<Mymodel> myModelArrayList;

        public Show_Exam_Adapter(Context context, ArrayList<Mymodel> myModelArrayList) {
            this.context = context;
            this.myModelArrayList = myModelArrayList;
        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.show_exams,parent,false);
            ViewHolder viewHolder=new ViewHolder(view);
            return viewHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            Mymodel  myModel = myModelArrayList.get(position);

            if (!myModel.equals("")) {
                holder.exam_name.setText(myModel.getExamName());


                try {
                    Glide.with(context).load(myModel.getPath()+myModel.getExamLogo())
                            .placeholder(R.drawable.bank1).override(50, 50)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.img_exam);

                    holder.exam_name.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            context.startActivity(new Intent(context,TestCategoryActivity.class));
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
            return myModelArrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView img_exam;
            TextView exam_name;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                img_exam=itemView.findViewById(R.id.img_exam);
                exam_name=itemView.findViewById(R.id.exam_name);


            }
        }
}