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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;

public class BudgetPeriod extends AppCompatActivity {


    //Global Variables
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

        //Set layout
        setContentView(R.layout.activity_budget_period);

        //Set elements to variables
        mStartDate = findViewById(R.id.startDatePlainText);
        mEndDate = findViewById(R.id.endDatePlainText);

        //Instantiate Object for method use
        final dateCalculator dateCheck = new dateCalculator();


        //Check to see if BudgetPeriod was called on with an already made file or if it's a new file
        if(getIntent().getStringExtra("fileName") != null){

            //Get extra information from intent
            mStartDate.setText(getIntent().getStringExtra("fileName"));
            mEndDate.setText(getIntent().getStringExtra("newEndDate"));
            dateSet = getIntent().getStringExtra("fileName");
            dateEnd = getIntent().getStringExtra("newEndDate");
            details = getIntent().getStringExtra("details");

            //Instantiate object for method use
            save = new SaveFile();
        }

        //Set on click listener
        mStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                //Set datepickerdialog variable
                DatePickerDialog dialog = new DatePickerDialog(BudgetPeriod.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mStartDateSetListener,
                        year,month,day);

                //Set background of the dialog to transparent
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                //Show dialog
                dialog.show();
            }
        });

        //Set on click listener
        mEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                //Set datepickerdialog variable
                DatePickerDialog dialog = new DatePickerDialog(BudgetPeriod.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mEndDateSetListener,
                        year,month,day);

                //Set background of the dialog to transparent
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                //Show dialog
                dialog.show();
            }
        });

        //Set on click listener
        mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                //Variables
                month = month + 1;
                String monthString = (month<10?"0" : "") + month;
                String dayOfMonthString = (dayOfMonth<10?"0" : "") + dayOfMonth;

                //Concatenate and add string to variable
                String date = dayOfMonthString + "/" + monthString + "/" + year;
                dateSet = dayOfMonthString + "-" + monthString + "-" + year;

                //Set text to textview
                mStartDate.setText(date);
            }
        };

        //Set on click listener
        mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Variables
                month = month + 1;
                String monthString = (month<10?"0" : "") + month;
                String dayOfMonthString = (dayOfMonth<10?"0" : "") + dayOfMonth;

                //Concatenate and add string to variable
                String date = dayOfMonthString + "/" + monthString + "/" + year;
                dateEnd = dayOfMonthString + "-" + monthString + "-" + year;

                //Check variable to check for start date higher than end
                int check = dateCheck.calculateDays(dateSet, dateEnd);

                //Control structure to check if end date is lower than start date
                if(check <= 0){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Start Date must be higher than or equal to End Date", Toast.LENGTH_LONG);
                    toast.show();
                } else {

                    //Set text to textview
                    mEndDate.setText(date);
                }
            }
        };

        //Set element to variable
        Button submitBtn = findViewById(R.id.submitBtn);

        //Set on click listener
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Concatenate and add string to variable
                dateSet = (dateSet + "~" + dateEnd + "~BP") + ".txt";

                //If statement to determine if the activity has started with previous information
                //or a new file
                if(getIntent().getStringExtra("fileName") != null) {
                    Intent rollOverIntent = new Intent(getApplicationContext(), BudgetHome.class);
                    save.saveFile(getApplicationContext(), dateSet, null, details, false);
                    rollOverIntent.putExtra("fileName", dateSet);
                    startActivity(rollOverIntent);
                } else {
                    Intent submitIntent = new Intent(getApplicationContext(), budgetCreation.class);
                    submitIntent.putExtra("date", dateSet);
                    submitIntent.putExtra("isBudgetPeriod", true);
                    startActivity(submitIntent);
                }
            }
        });

    }



}
