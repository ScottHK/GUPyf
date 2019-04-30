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
        setContentView(R.layout.activity_main);

        Button newBudgetBtn = (Button) findViewById(R.id.newBudgetBtn);
        Button openFile = findViewById(R.id.openFileBtn);

        openFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openFileIntent = new Intent(getApplicationContext(), fileList.class);
                startActivity(openFileIntent);
            }
        });
        newBudgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), SetBudget.class);
                //passing information to another activity
                startActivity(startIntent);
            }
        });
    }
}
