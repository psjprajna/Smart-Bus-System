package com.ipdd.nmitsmartbus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ipdd.nmitsmartbus.Model.User;

public class LoginActivity extends AppCompatActivity {

    EditText edtreg,edtPasswords;
    Button btnSignIn;

    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtreg=(EditText)findViewById(R.id.edtreg);
        edtPasswords=(EditText)findViewById(R.id.edtPasswords);

        btnSignIn=(Button)findViewById(R.id.login);

        db=FirebaseDatabase.getInstance();
        users = db.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser(edtreg.getText().toString(),edtPasswords.getText().toString());
            }
        });
    }

    private void signInUser(String regno, final String password) {

        final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
        mDialog.setMessage("Please wait..");
        mDialog.show();

        final String localPhone = regno;
        final String localPassword = password;
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child (localPhone).exists())
                {
                    mDialog.dismiss();
                    User user = dataSnapshot.child(localPhone).getValue(User.class);
                    user.setRegno(localPhone);
                    if (user.getDesignation().equals("Student"))
                    {
                        if(user.getPassword().equals(edtPasswords.getText().toString()))
                        {
                            Toast.makeText(LoginActivity.this, "Sign In Successfull", Toast.LENGTH_LONG).show();

                            Intent StudentIntent = new Intent(LoginActivity.this, Student.class);

                            startActivity(StudentIntent);
                            finish();
                        }
                        else
                            Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_LONG).show();
                    }
                    else if (user.getDesignation().equals("Admin"))
                    {
                        if(user.getPassword().equals(edtPasswords.getText().toString()))
                        {
                            Toast.makeText(LoginActivity.this, "Sign In Successfull", Toast.LENGTH_LONG).show();

                            Intent StudentIntent = new Intent(LoginActivity.this,Admin.class);

                            startActivity(StudentIntent);
                            finish();
                        }
                        else
                            Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_LONG).show();
                    }
                    else if (user.getDesignation().equals("Driver"))
                    {
                        if(user.getPassword().equals(edtPasswords.getText().toString()))
                        {
                            Toast.makeText(LoginActivity.this, "Sign In Successfull", Toast.LENGTH_LONG).show();

                            Intent StudentIntent = new Intent(LoginActivity.this,Driver.class);

                            startActivity(StudentIntent);
                            finish();
                        }
                        else
                            Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(LoginActivity.this, "You are not Privileged", Toast.LENGTH_LONG).show();
                }
                else
                {
                    mDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "User doesn't exists", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
