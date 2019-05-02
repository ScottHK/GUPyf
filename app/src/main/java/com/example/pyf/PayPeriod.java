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

    Spinner spinner;

    private EditText mStartDate;
    private DatePickerDialog.OnDateSetListener mStartDateSetListener;
    String dateSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_period);

        Button submitbtn = findViewById(R.id.submitBtn);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextlayout = new Intent(getApplicationContext(), budgetCreation.class);
                //TODO Passing Information
                startActivity(nextlayout);
            }
        });

        mStartDate = (EditText) findViewById(R.id.latestPaymentDatePlainText);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.frequency_arrays,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(PayPeriod.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mStartDateSetListener,
                        year, month, day);
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

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selected = (String) parent.getItemAtPosition(position);
        dateCalculator dCalc = new dateCalculator();

        if(selected == "Weekly") {
            String newEndDate = dCalc.calculateDate(7, dateSet).substring(0,10);
            Log.d("newEndDate", newEndDate);
        } else if(selected == "Fortnightly") {
            String newEndDate = dCalc.calculateDate(14, dateSet).substring(0,10);
        } else {
            String newEndDate = dCalc.calculateDate(30, dateSet).substring(0,10);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
