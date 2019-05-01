package com.example.pyf;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BudgetPeriod extends AppCompatActivity {


    EditText mStartDate;
    EditText mEndDate;
    DatePickerDialog.OnDateSetListener mStartDateSetListener;
    DatePickerDialog.OnDateSetListener mEndDateSetListener;
    String dateSet;
    String dateEnd;
    String details;
    SaveFile save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_period);
        mStartDate = (EditText) findViewById(R.id.startDatePlainText);
        mEndDate = (EditText) findViewById(R.id.endDatePlainText);

        if(getIntent().getStringExtra("fileName") != null){
            mStartDate.setText(getIntent().getStringExtra("fileName"));
            mEndDate.setText(getIntent().getStringExtra("newEndDate"));
            dateSet = getIntent().getStringExtra("fileName");
            dateEnd = getIntent().getStringExtra("newEndDate");
            details = getIntent().getStringExtra("details");
            save = new SaveFile();
        }
        mStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(BudgetPeriod.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mStartDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(BudgetPeriod.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mEndDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String monthString = (month<10?"0" : "") + month;
                String dayOfMonthString = (dayOfMonth<10?"0" : "") + dayOfMonth;
                String date = dayOfMonthString + "/" + monthString + "/" + year;
                dateSet = dayOfMonthString + "-" + monthString + "-" + year;
                mStartDate.setText(date);
            }
        };

        mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String monthString = (month<10?"0" : "") + month;
                String dayOfMonthString = (dayOfMonth<10?"0" : "") + dayOfMonth;
                String date = dayOfMonthString + "/" + monthString + "/" + year;
                dateEnd = dayOfMonthString + "-" + monthString + "-" + year;
                mEndDate.setText(date);
            }
        };

        Button submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submitIntent = new Intent(getApplicationContext(), budgetCreation.class);
                Intent rollOverIntent = new Intent(getApplicationContext(), BudgetHome.class);
                dateSet = (dateSet + "~" + dateEnd) + ".txt";
                if(getIntent().getStringExtra("fileName") != null) {
                    save.saveFile(getApplicationContext(), dateSet, null, details, false);
                    rollOverIntent.putExtra("fileName", dateSet);
                    startActivity(rollOverIntent);
                } else {
                    submitIntent.putExtra("date", dateSet);
                    submitIntent.putExtra("isBudgetPeriod", true);
                    startActivity(submitIntent);
                }
            }
        });

    }



}
