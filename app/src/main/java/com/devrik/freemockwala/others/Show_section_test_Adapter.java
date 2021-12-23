package com.devrik.freemockwala.others;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.devrik.freemockwala.Model.SectionModel;
import com.devrik.freemockwala.QuestionScreenActivity;
import com.devrik.freemockwala.R;

import java.util.ArrayList;

public class Show_section_test_Adapter extends RecyclerView.Adapter<Show_section_test_Adapter.ViewHolder> {
    Context context;
    ArrayList<SectionModel> sectionModelArrayList;
    String TestID = "";
    String Section_id = "";
    String Question_id = "";

    public Show_section_test_Adapter(Context context, ArrayList<SectionModel> sectionModelArrayList) {
        this.context = context;
        this.sectionModelArrayList = sectionModelArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.testsection,parent,false);
        Show_section_test_Adapter.ViewHolder viewHolder=new Show_section_test_Adapter.ViewHolder(view);

        TestID= ShareHelper.getKey(context,APPCONSTANT.test_id);
        Section_id= ShareHelper.getKey(context,APPCONSTANT.section_id);
        Question_id= ShareHelper.getKey(context,APPCONSTANT.question_id);
        return new ViewHolder(view);
    }


            @Override
        public void onBindViewHolder (ViewHolder holder,@SuppressLint("RecyclerView")int position){
            SectionModel sectionModel = sectionModelArrayList.get(position);

            if (!sectionModel.equals("")) {
                holder.btn_section.setText(sectionModel.getSectionName());
               /* holder.btn_section.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.getContext().startActivity(new Intent(context, QuestionScreenActivity.class));

                        }
                    });
*/
                holder.btn_section.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShareHelper.putkey(context, APPCONSTANT.section_id,sectionModel.getSection_id());
                        ShareHelper.putkey(context,APPCONSTANT.Sectionname,sectionModel.getSectionName());
                        Section_id=ShareHelper.getKey(context,APPCONSTANT.section_id);
                      // Question_id=ShareHelper.getKey(context,APPCONSTANT.question_id);
                        ShareHelper.putkey(context, APPCONSTANT.Status,"1");
                        ((QuestionScreenActivity)context).showQuestions();
                       // ((QuestionScreenActivity)context).showQuestions(Question_id,Section_id);

                    }
                });

                Log.e("dfdsafdf",sectionModel.getSection_id());

            }
        }

    @Override
    public int getItemCount() {
        return sectionModelArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        Button btn_section;
        public ViewHolder(View itemView) {
            super(itemView);
            btn_section=itemView.findViewById(R.id.btn_section);
        }
    }
}
