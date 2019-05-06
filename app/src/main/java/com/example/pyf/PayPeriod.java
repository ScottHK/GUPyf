package com.example.pyf;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class PayPeriod extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Global Variables
    Spinner spinner;
    private EditText mStartDate;
    private DatePickerDialog.OnDateSetListener mStartDateSetListener;
    String dateSet;
    boolean itemSelected = false;
    String details;
    SaveFile save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set layout
        setContentView(R.layout.activity_pay_period);

        //Set elements to variables
        Button submitbtn = findViewById(R.id.submitBtn);
        mStartDate = findViewById(R.id.latestPaymentDatePlainText);

        //Control structure for checking if activity loaded for roll over or a new file
        if(getIntent().getStringExtra("fileName") != null){

            //Get extra information from intent
            mStartDate.setText(getIntent().getStringExtra("fileName"));
            dateSet = getIntent().getStringExtra("fileName");
            details = getIntent().getStringExtra("details");

            //Instantiate object for method use
            save = new SaveFile();

        }

        //Set on click listener
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Control structure for checking that a frequency choice has been selected
                if(itemSelected) {

                    //Check if this is a roll over or new file
                    if(getIntent().getStringExtra("fileName") != null) {

                        //Instantiate intent
                        Intent rollOverIntent = new Intent(getApplicationContext(),
                                BudgetHome.class);

                        //Use save objects saveFile method
                        save.saveFile(getApplicationContext(), dateSet, null, details,
                                false);

                        //Add extra information to intent
                        rollOverIntent.putExtra("fileName", dateSet);

                        //Execute intent
                        startActivity(rollOverIntent);
                    } else {

                        //Instantiate intent
                        Intent nextlayout = new Intent(getApplicationContext(),
                                budgetCreation.class);

                        //Add extra information to intent
                        nextlayout.putExtra("date", dateSet);

                        //Execute intent
                        startActivity(nextlayout);
                    }
                } else {

                    //App responsiveness
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please select a choice from below (Weekly, Fortnightly or " +
                                    "Monthly",
                            Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        //Set element to variable
        spinner = findViewById(R.id.spinner);

        //Create adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.frequency_arrays,
                android.R.layout.simple_spinner_item);

        //Edit adapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Set adapter
        spinner.setAdapter(adapter);

        //Set on item selected listener
        spinner.setOnItemSelectedListener(this);
        mStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                //Date picker dialog
                DatePickerDialog dialog = new DatePickerDialog(PayPeriod.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mStartDateSetListener,
                        year, month, day);

                //Set dialog window background to transparent
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        //Set on date set listener
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

                //Set edittext to string
                mStartDate.setText(date);
            }
        };

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Variable set on chosen item
        String selected = (String) parent.getItemAtPosition(position);

        //Debugging
        Log.d("selected", selected);

        //Instantiating object for method use
        dateCalculator dCalc = new dateCalculator();

        //Control structure for setting string according to frequency selected
        if(selected.equals("Weekly")) {
            String newEndDate = dCalc.calculateDate(7, dateSet).substring(0,10);
            dateSet = (dateSet + "~" + newEndDate + "~PP") + ".txt";
            itemSelected = true;
            Log.d("newEndDate", newEndDate);
        } else if(selected.equals("Fortnightly")) {
            String newEndDate = dCalc.calculateDate(14, dateSet).substring(0,10);
            dateSet = (dateSet + "~" + newEndDate + "~PP") + ".txt";
            itemSelected = true;
            Log.d("newEndDate", newEndDate);
        } else if(selected.equals("Monthly")) {
            String newEndDate = dCalc.calculateDate(30, dateSet).substring(0,10);
            dateSet = (dateSet + "~" + newEndDate + "~PP") + ".txt";
            itemSelected = true;
            Log.d("newEndDate", newEndDate);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
