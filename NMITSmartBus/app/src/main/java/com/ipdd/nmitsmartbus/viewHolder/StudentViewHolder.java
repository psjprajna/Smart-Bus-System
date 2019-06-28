package com.ipdd.nmitsmartbus.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ipdd.nmitsmartbus.R;


public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView student_name,student_reg,student_phn;
    public Button send_msg,call;






    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);

        student_name = (TextView)itemView.findViewById(R.id.student_name);
        student_reg = (TextView)itemView.findViewById(R.id.student_reg);
        send_msg=(Button)itemView.findViewById(R.id.send_msg);

        student_phn = (TextView)itemView.findViewById(R.id.student_phn);
        call=(Button)itemView.findViewById(R.id.call);

    }

    @Override
    public void onClick(View v) {


    }


}
