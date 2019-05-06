package com.example.pyf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set layout
        setContentView(R.layout.activity_main);


        //Set elements to variables
        Button newBudgetBtn = findViewById(R.id.newBudgetBtn);
        Button openFile = findViewById(R.id.openFileBtn);


        //Set on click listener
        openFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Instantiate intent
                Intent openFileIntent = new Intent(getApplicationContext(), fileList.class);

                //Execute intent
                startActivity(openFileIntent);
            }
        });

        //Set on click listener
        newBudgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Instantiate intent
                Intent startIntent = new Intent(getApplicationContext(), SetBudget.class);

                //Execute intent
                startActivity(startIntent);
            }
        });
    }
}
