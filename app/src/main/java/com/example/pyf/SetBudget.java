package com.example.pyf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SetBudget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_budget);

        Button datesBtn = (Button) findViewById(R.id.editBtn);
        datesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent datesIntent = new Intent(getApplicationContext(), BudgetPeriod.class);
                //pass information to another activity
                startActivity(datesIntent);
            }
        });
    }
}
