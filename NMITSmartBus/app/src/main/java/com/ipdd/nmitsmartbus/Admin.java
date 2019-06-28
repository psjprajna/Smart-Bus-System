package com.ipdd.nmitsmartbus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ipdd.nmitsmartbus.Model.User;

public class Admin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference Users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        database = FirebaseDatabase.getInstance();
        Users = database.getReference("User");


        Button fabAddStu = (Button) findViewById(R.id.std);
        fabAddStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateStudentLayout();
            }
        });

        Button fabAddDri = (Button) findViewById(R.id.drv);
        fabAddDri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDriverLayout();
            }
        });






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void CreateDriverLayout() {
        AlertDialog.Builder create_driver_dialog = new AlertDialog.Builder(Admin.this);
        create_driver_dialog.setTitle("Add Driver");


        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.create_layout, null);

        final EditText edtName = (EditText) view.findViewById(R.id.name);
        final EditText edtPassword = (EditText) view.findViewById(R.id.edtPasswords);
        final EditText edtRegno = (EditText) view.findViewById(R.id.edtreg);
        final EditText edtphn = (EditText) view.findViewById(R.id.edtphn);
        final EditText edtsal = (EditText) view.findViewById(R.id.edtsal);

        create_driver_dialog.setView(view);
        create_driver_dialog.setIcon(R.drawable.ic_person_add_black_24dp);
        create_driver_dialog.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();


                User user = new User();
                user.setName(edtName.getText().toString());
                user.setPassword(edtPassword.getText().toString());
                user.setRegno(edtRegno.getText().toString());
                user.setPhone(edtphn.getText().toString());
                user.setSalary(edtsal.getText().toString());
                user.setDesignation("Driver");
                user.setStop("");

                Users.child(edtRegno.getText().toString())
                        .setValue(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Admin.this, "Driver added successfully.", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Admin.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

            }


        });

        create_driver_dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        create_driver_dialog.show();
    }

    private void CreateStudentLayout() {
        AlertDialog.Builder create_student_dialog = new AlertDialog.Builder(Admin.this);
        create_student_dialog.setTitle("Add Student");


        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.create_student_layout, null);

        final EditText edtName = (EditText) view.findViewById(R.id.name);
        final EditText edtPassword = (EditText) view.findViewById(R.id.edtPasswords);
        final EditText edtRegno = (EditText) view.findViewById(R.id.edtreg);
        final EditText edtphn = (EditText) view.findViewById(R.id.edtphn);
        final EditText edtstn = (EditText) view.findViewById(R.id.stn);

        create_student_dialog.setView(view);
        create_student_dialog.setIcon(R.drawable.ic_person_add_black_24dp);
        create_student_dialog.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();


                User user = new User();
                user.setName(edtName.getText().toString());
                user.setPassword(edtPassword.getText().toString());
                user.setRegno(edtRegno.getText().toString());
                user.setPhone(edtphn.getText().toString());
                user.setDesignation("Student");
                user.setStop(edtstn.getText().toString());

                Users.child(edtRegno.getText().toString())
                        .setValue(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Admin.this, "Student added successfully.", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Admin.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

            }


        });

        create_student_dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        create_student_dialog.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.ahome) {
            Intent home = new Intent(Admin.this,Admin.class);
            startActivity(home);

        } else if (id == R.id.aabout) {

            Intent abt = new Intent(Admin.this, About.class);
            startActivity(abt);

        } else if (id == R.id.acontact) {
            Intent contact = new Intent(Admin.this,AdminContact.class);
            startActivity(contact);


        } else if (id == R.id.alogout) {
            Toast.makeText(Admin.this, "Logged Out", Toast.LENGTH_LONG).show();
            Intent logout = new Intent(Admin.this, LoginActivity.class);
            logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(logout);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
