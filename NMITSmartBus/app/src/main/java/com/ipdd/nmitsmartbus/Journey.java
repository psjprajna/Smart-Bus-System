package com.ipdd.nmitsmartbus;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.ipdd.nmitsmartbus.Model.Student;
import com.ipdd.nmitsmartbus.viewHolder.StudentViewHolder;

public class Journey extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference studentList;


    FirebaseRecyclerAdapter<Student, StudentViewHolder> adapter;

    int stopnumber;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey);

        database = FirebaseDatabase.getInstance();
        studentList = database.getReference("Student");

        stopnumber = 1;



        recyclerView = (RecyclerView) findViewById(R.id.listStudents);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        // recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setLayoutManager(layoutManager);


        LoadStudent(String.valueOf(stopnumber));
        Button next=(Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Journey.this, "Next Stop", Toast.LENGTH_SHORT).show();
                stopnumber++;
                Toast.makeText(Journey.this, "Stop:  "+stopnumber, Toast.LENGTH_SHORT).show();
                LoadStudent(String.valueOf(stopnumber));
            }
        });
    }


    private void LoadStudent(String stopnumber) {


        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();

        adapter = new FirebaseRecyclerAdapter<Student, StudentViewHolder>(Student.class, R.layout.student_item, StudentViewHolder.class, studentList.orderByChild("stop").equalTo(stopnumber)) {
            @Override
            protected void populateViewHolder(final StudentViewHolder viewHolder, final Student model, final int position) {

                viewHolder.student_name.setText(model.getName());
                viewHolder.student_reg.setText(model.getRegno());
                viewHolder.student_phn.setText(model.getPhone());
                Log.d("TAG", model.getName());

                viewHolder.send_msg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Journey.this, "Message", Toast.LENGTH_SHORT).show();
                    }
                });

                viewHolder.call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String phno = model.getPhone();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        String temp = "tel:" + phno;
                        intent.setData(Uri.parse(temp));

                        startActivity(intent);
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
        Toast.makeText(this, "HIIIIIIIIIIIIIII", Toast.LENGTH_SHORT).show();
    }
}