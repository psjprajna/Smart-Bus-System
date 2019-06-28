package com.ipdd.nmitsmartbus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ipdd.nmitsmartbus.Model.Contact;

public class DriverContact extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference Contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_contact);
        database = FirebaseDatabase.getInstance();
        Contact = database.getReference("Complaints");

        final EditText name = (EditText)findViewById(R.id.name);
        final EditText registration = (EditText)findViewById(R.id.regno);
        final EditText reason = (EditText)findViewById(R.id.reason);
        Button send = (Button)findViewById(R.id.btnsend);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Contact user = new Contact();
                    user.setName(name.getText().toString());
                    user.setPhone(registration.getText().toString());
                    user.setReason(reason.getText().toString());
                    user.setDesignation("Driver");

                    Contact.child(registration.getText().toString())
                            .setValue(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(DriverContact.this, "Message Sent Successfully", Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(DriverContact.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
                    Intent home = new Intent(DriverContact.this, Driver.class);
                    startActivity(home);
                }
        });

    }
}
