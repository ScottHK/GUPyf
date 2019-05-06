package com.example.pyf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SetBudget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set layout
        setContentView(R.layout.activity_set_budget);

        //Set elements to variables
        ImageButton datesBtn = findViewById(R.id.datesBtn);
        ImageButton payDayButton = findViewById(R.id.payDayBtn);

        //Set on click listener
        datesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Instantiate intent
                Intent datesIntent = new Intent(getApplicationContext(), BudgetPeriod.class);

                //Execute intent
                startActivity(datesIntent);
            }
        });

        //Set on click listener
        payDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Instantiate intent
                Intent payDayIntent = new Intent(getApplicationContext(), PayPeriod.class);

                //Execute intent
                startActivity(payDayIntent);
            }
        });
        }
    }
