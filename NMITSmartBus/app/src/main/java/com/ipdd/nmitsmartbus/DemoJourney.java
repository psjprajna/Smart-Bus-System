package com.ipdd.nmitsmartbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DemoJourney extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_journey);

        Button btnns = (Button) findViewById(R.id.btnns);
        btnns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(DemoJourney.this, "No More Stops", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
