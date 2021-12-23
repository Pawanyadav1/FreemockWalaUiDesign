package com.devrik.freemockwala.others;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.devrik.freemockwala.Model.Exammodel;
import com.devrik.freemockwala.R;
import com.devrik.freemockwala.TestCategoryActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class showhome_examAdapter extends RecyclerView.Adapter<showhome_examAdapter.ViewHolder> {
    Context context;
    ArrayList<Exammodel> myModelArrayList;

    public showhome_examAdapter(Context context, ArrayList<Exammodel> myModelArrayList) {
        this.context = context;
        this.myModelArrayList = myModelArrayList;
    }
    @Override
    public showhome_examAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.showhomeexam,parent,false);
        showhome_examAdapter.ViewHolder viewHolder=new showhome_examAdapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull showhome_examAdapter.ViewHolder holder, int position) {

        Exammodel myModel = myModelArrayList.get(position);

        if (!myModel.equals("")) {
            holder.exam_name.setText(myModel.getExamName());
            try {
                Glide.with(context).load(myModel.getPath()+myModel.getExamLogo())
                        .placeholder(R.drawable.bank1).override(50, 50)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.img_exam);
                holder.card_exam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShareHelper.putkey(context,APPCONSTANT.exam_id,myModel.getId());
                        context.startActivity(new Intent(context, TestCategoryActivity.class));
                    }
                });
                Log.e("hjhtyhjhhj",myModel.getPath()+myModel.getExamLogo());
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
        TextView exam_name;
        MaterialCardView card_exam;
        CircleImageView img_exam;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_exam=itemView.findViewById(R.id.img_exam);
            exam_name=itemView.findViewById(R.id.exam_name);
            card_exam = itemView.findViewById(R.id.card_exam);
        }
    }
}