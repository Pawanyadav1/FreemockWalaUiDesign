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

import com.devrik.freemockwala.Model.Exammodel;
import com.devrik.freemockwala.R;
import com.devrik.freemockwala.TestCategoryActivity;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Show_Exam_Adapter extends RecyclerView.Adapter<Show_Exam_Adapter.ViewHolder> {
        Context context;
        ArrayList<Exammodel> myModelArrayList;

        public Show_Exam_Adapter(Context context, ArrayList<Exammodel> myModelArrayList) {
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

            Exammodel myModel = myModelArrayList.get(position);
            Picasso.get().load(myModel.getPath()+myModel.getExamLogo()).into(holder.img_exam);

            if (!myModel.equals("")) {
                holder.exam_name.setText(myModel.getExamName());
                try {

                    Log.e("dfgkjhjfgk",myModel.getPath()+"");
                    Log.e("dfgkjhjfgk",myModel.getExamLogo()+"");
                   // Glide.with(context).load(myModel.getPath()+myModel.getExamLogo()).into(holder.img_exam);

                    holder.card_exam.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                          ShareHelper.putkey(context,APPCONSTANT.exam_id,myModel.getId());
                            context.startActivity(new Intent(context,TestCategoryActivity.class));
                        }
                    });
                    Log.e("dfcghdicd",myModel.getId());
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
            ImageView show_exam;
            TextView exam_name;
            MaterialCardView card_exam;
            CircleImageView img_exam;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                img_exam=itemView.findViewById(R.id.img_exam);
                show_exam = itemView.findViewById(R.id.show_exam);
                exam_name=itemView.findViewById(R.id.exam_name);
                card_exam = itemView.findViewById(R.id.card_exam);
            }
        }
}