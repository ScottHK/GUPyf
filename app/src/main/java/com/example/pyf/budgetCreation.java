package com.example.pyf;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class budgetCreation extends AppCompatActivity {
    // Global Variables
    Dialog myDialog;
    TextView actionLog;
    String fileLog = "";
    String fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Layout to be used
        setContentView(R.layout.activity_budget_creation);

        //Retrieve packed info
        fileName = getIntent().getStringExtra("date");

        //Set elements to variables
        Button donebtn = findViewById(R.id.doneBtn);
        ImageButton incomeDetails = findViewById(R.id.incomeBtn);
        ImageButton billsDetails = findViewById(R.id.billsBtn);
        ImageButton taxesDetails = findViewById(R.id.taxesBtn);
        ImageButton debtsDetails = findViewById(R.id.debtsBtn);
        ImageButton subscriptionDetails = findViewById(R.id.subscriptionBtn);
        ImageButton savingsDetails = findViewById(R.id.savingsBtn);
        actionLog = findViewById(R.id.actionLogTextView);

        //Set scrolling method on TextView
        actionLog.setMovementMethod(new ScrollingMovementMethod());

        //Set dialog variable
        myDialog = new Dialog(this);

        //Instantiate objects for method use later
        final SaveFile save = new SaveFile();

        //Setting on click listener
        incomeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                TextView txtclose;
                Button submitBtnPop;
                final String category = "Income";

                //Set elements to variables
                myDialog.setContentView(R.layout.custompopup);
                final EditText descriptionET = myDialog.findViewById(R.id.descriptionEditText);
                final EditText amountET = myDialog.findViewById(R.id.amountEditText);
                txtclose = myDialog.findViewById(R.id.exit);
                submitBtnPop = myDialog.findViewById(R.id.submitBtnPop);

                //Setting on click listener
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Setting on click listener
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Variables
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();
                        String string = category + ": " + description + " - £" + amount + "\n";

                        //Append string to actionLog TextView
                        actionLog.append(string);

                        //Concatenate and add to fileLog
                        fileLog += category + "/" + description + "/" + amount + "\n";

                        //Dismiss dialog box
                        myDialog.dismiss();
                    }
                });

                //Show Dialog box
                myDialog.show();
            }
        });

        //Setting on click listener
        billsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                TextView txtclose;
                Button submitBtnPop;
                final String category = "Bills";

                //Set layout
                myDialog.setContentView(R.layout.custompopup);

                //Set elements to variables
                final EditText descriptionET = myDialog.findViewById(R.id.descriptionEditText);
                final EditText amountET = myDialog.findViewById(R.id.amountEditText);
                txtclose = myDialog.findViewById(R.id.exit);
                submitBtnPop = myDialog.findViewById(R.id.submitBtnPop);

                //Setting on click listener
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Setting on click listener
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Variables
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();
                        String string = category + ": " + description + " - £" + amount + "\n";

                        //Append string to actionLog TextView
                        actionLog.append(string);

                        //Concatenate and add to fileLog
                        fileLog += category + "/" + description + "/" + amount + "\n";

                        //Dismiss dialog box
                        myDialog.dismiss();
                    }
                });

                //Show Dialog box
                myDialog.show();
            }
        });

        //Setting on click listener
        taxesDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                TextView txtclose;
                Button submitBtnPop;
                final String category = "Taxes";

                //Set layout
                myDialog.setContentView(R.layout.custompopup);

                //Set elements to variables
                final EditText descriptionET = myDialog.findViewById(R.id.descriptionEditText);
                final EditText amountET = myDialog.findViewById(R.id.amountEditText);
                txtclose = myDialog.findViewById(R.id.exit);
                submitBtnPop = myDialog.findViewById(R.id.submitBtnPop);

                //Setting on click listener
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Setting on click listener
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Variables
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();
                        String string = category + ": " + description + " - £" + amount + "\n";

                        //Append string to actionLog TextView
                        actionLog.append(string);

                        //Concatenate and add to fileLog
                        fileLog += category + "/" + description + "/" + amount + "\n";

                        //Dismiss dialog
                        myDialog.dismiss();
                    }
                });

                //Show dialog
                myDialog.show();
            }
        });

        //Setting on click listener
        debtsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                TextView txtclose;
                Button submitBtnPop;
                final String category = "Debts";

                //Set layout
                myDialog.setContentView(R.layout.custompopup);

                //Set elements to variables
                final EditText descriptionET = myDialog.findViewById(R.id.descriptionEditText);
                final EditText amountET = myDialog.findViewById(R.id.amountEditText);
                txtclose = myDialog.findViewById(R.id.exit);
                submitBtnPop = myDialog.findViewById(R.id.submitBtnPop);

                //Setting on click listener
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Dismiss dialog
                        myDialog.dismiss();
                    }
                });

                //Setting on click listener
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Variables
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();
                        String string = category + ": " + description + " - £" + amount + "\n";

                        //Append string to actionLog TextView
                        actionLog.append(string);

                        //Concatenate and add to fileLog
                        fileLog += category + "/" + description + "/" + amount + "\n";

                        //Dismiss dialog
                        myDialog.dismiss();
                    }
                });

                //Show dialog
                myDialog.show();
            }
        });

        //Setting on click listener
        subscriptionDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                TextView txtclose;
                Button submitBtnPop;
                final String category = "Subscription";

                //Set layout
                myDialog.setContentView(R.layout.custompopup);

                //Set elements to variables
                final EditText descriptionET = myDialog.findViewById(R.id.descriptionEditText);
                final EditText amountET = myDialog.findViewById(R.id.amountEditText);
                txtclose = (myDialog.findViewById(R.id.exit));
                submitBtnPop = myDialog.findViewById(R.id.submitBtnPop);

                //Setting on click listener
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Setting on click listener
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Variables
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();
                        String string = category + ": " + description + " - £" + amount + "\n";

                        //Append string to actionLog TextView
                        actionLog.append(string);

                        //Concatenate and add string to fileLog
                        fileLog += category + "/" + description + "/" + amount + "\n";

                        //Dismiss dialog
                        myDialog.dismiss();
                    }
                });

                //Show dialog
                myDialog.show();
            }
        });

        //Setting on click listener
        savingsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                TextView txtclose;
                Button submitBtnPop;
                final String category = "Savings";

                //Set layout
                myDialog.setContentView(R.layout.custompopup);

                //Set elements to variables
                final EditText descriptionET = myDialog.findViewById(R.id.descriptionEditText);
                final EditText amountET = myDialog.findViewById(R.id.amountEditText);
                txtclose = (myDialog.findViewById(R.id.exit));
                submitBtnPop = myDialog.findViewById(R.id.submitBtnPop);

                //Setting on click listener
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Dismiss dialog
                        myDialog.dismiss();
                    }
                });

                //Setting on click listener
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Variables
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();
                        String string = category + ": " + description + " - £" + amount + "\n";

                        //Append string to actionLog TextView
                        actionLog.append(string);

                        //Concatenate and add string to fileLog
                        fileLog += category + "/" + description + "/" + amount + "\n";

                        //Dismiss dialog
                        myDialog.dismiss();
                    }
                });

                //Show dialog
                myDialog.show();
            }
        });


        //Setting on click listener
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Instantiate Intent
                Intent doneIntent = new Intent(getApplicationContext(), BudgetHome.class);

                //Use save object method saveFile to write given string to file
                save.saveFile(getApplicationContext(), fileName, null, fileLog, false);

                //Add extra information to intent
                doneIntent.putExtra("fileName", fileName);

                //Start intent, starting a new activity
                startActivity(doneIntent);

            }
        });

    }

}
