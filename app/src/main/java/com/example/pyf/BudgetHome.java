package com.example.pyf;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

public class BudgetHome extends AppCompatActivity {

    //Global Variables
    Dialog myDialog;
    String details;
    TextView transaction;
    String fileName;
    float incomeMoney;
    float billsMoney;
    float taxesMoney;
    float debtsMoney;
    float leisureMoney;
    float balanceMoney;
    float additionalMoney;
    float subscriptionMoney;
    float savingsMoney;
    float savingsBalanceMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set layout
        setContentView(R.layout.budgethome);

        //Retrieve extra information from previous intent
        fileName = getIntent().getStringExtra("fileName");

        //Instantiate objects for method use
        final SaveFile saveMethod = new SaveFile();
        final loadFile loadMethod = new loadFile();

        //Set elements to variables
        ImageButton editbtn = findViewById(R.id.btn_edit);
        ImageButton exportbtn = findViewById(R.id.btn_archive);
        ImageButton newbtn = findViewById(R.id.btn_New);
        ImageButton quickbtn = findViewById(R.id.btn_Quick);
        ImageButton breakdown = findViewById(R.id.btn_Breakdown);
        transaction = findViewById(R.id.transactionHistoryTextView);
        final TextView income = findViewById(R.id.incomeMoneyTextView);
        final TextView bills = findViewById(R.id.billsMoneyTextView);
        final TextView taxes = findViewById(R.id.taxesMoneyTextView);
        final TextView debts = findViewById(R.id.debtsMoneyTextView);
        final TextView balance = findViewById(R.id.balanceMoneyTextView);
        final TextView leisure = findViewById(R.id.tv_leisureMoney);
        final TextView additional = findViewById(R.id.additionalMoneyTextView);
        final TextView subscription = findViewById(R.id.tv_SubscriptionMoney);
        final TextView savings = findViewById(R.id.tv_savingsMoney);
        final TextView savingsBalance = findViewById(R.id.tv_SavingsBalanceMoney);

        //Set textview scrolling method
        transaction.setMovementMethod(new ScrollingMovementMethod());

        //Set dialog variable
        myDialog = new Dialog(this);

        //Load string from specified saved file. Return string to variable
        details = loadMethod.FileLoader(fileName, null , getApplicationContext());

        //Function to generate string and assign to TextViews on layout
        overviewGenerate(income, bills, taxes, debts, leisure, balance, additional, subscription,
                savings, savingsBalance);

        //Set on click listener
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                TextView close;
                Button editFile;
                Button deleteDetails;

                //Set layout
                myDialog.setContentView(R.layout.edit_pop_up);

                //Set elements to variables
                close = myDialog.findViewById(R.id.editExit);
                editFile = myDialog.findViewById(R.id.btn_editEdit);
                deleteDetails = myDialog.findViewById(R.id.btn_delete);

                //Set on click listener
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Set on click listener
                editFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Instantiate new intent
                        Intent budgetcreationIntent = new Intent(getApplicationContext(),
                                EditBudget.class);

                        //Run object method to save to specified file with specified string
                        saveMethod.saveFile(getApplicationContext(), fileName, null,
                                details, false);

                        //Add extra information to intent
                        budgetcreationIntent.putExtra("fileName", fileName);

                        //Execute intent, starting next activity
                        startActivity(budgetcreationIntent);
                    }
                });

                //Set on click listener
                deleteDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Instantiate new intent
                        Intent deleteIntent = new Intent(getApplicationContext(),
                                DeleteEntries.class);

                        //Run object method to save to specified file with specified string
                        saveMethod.saveFile(getApplicationContext(), fileName, null,
                                details, false);

                        //Add extra information to intent
                        deleteIntent.putExtra("fileName", fileName);

                        //Execute intent, starting next activity
                        startActivity(deleteIntent);
                    }
                });

                //Show dialog
                myDialog.show();
            }
        });

        //Set on click listener
        exportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                TextView close;
                Button device;
                Button gdrive;

                //Request permissions to write to external storage
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    //Show explanation for permission request
                    if (ActivityCompat.shouldShowRequestPermissionRationale(BudgetHome.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(BudgetHome.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);

                    }
                } else {
                    // Permission has already been granted
                }

                //Set layout
                myDialog.setContentView(R.layout.activity_exportarchive);

                //Set elements to variables
                close = myDialog.findViewById(R.id.archiveExit);
                device = myDialog.findViewById(R.id.deviceBtn);

                //Set on click listener
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Set on click listener
                device.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Instantiate object for method use
                        Export export = new Export();

                        //Error Handling
                        try {

                            //Replace string with other string
                            String exportFile = fileName.replace("txt", "csv");

                            //User export object method to create csv file and write to external
                            //storage
                            export.exportCSV(exportFile, details, getApplicationContext());

                        } catch (IOException e) {
                            //Print error to stack trace
                            e.printStackTrace();
                        }
                    }
                });

                //Show dialog
                myDialog.show();

            }
        });

        //Set on click listener
        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                TextView close;
                Button newFile;
                Button rollOver;

                //Set layout
                myDialog.setContentView(R.layout.activity_start_new);

                //Set elements to variables
                close = myDialog.findViewById(R.id.startNewExit);
                newFile = myDialog.findViewById(R.id.startNewBtn);
                rollOver = myDialog.findViewById(R.id.rollOverBtn);

                //Set on click listener
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Set on click listener
                newFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Instantiate new Intent
                        Intent newIntent = new Intent(getApplicationContext(), MainActivity.class);

                        //Run object method to save to specified file with specified string
                        saveMethod.saveFile(getApplicationContext(), fileName, null,
                                details, false);

                        //Execute intent, starting next activity
                        startActivity(newIntent);
                    }
                });

                //Set on click listener
                rollOver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Instantiate objects for methd use
                        dateCalculator dCalc = new dateCalculator();
                        RollOverCleaner cleaner = new RollOverCleaner();

                        //Instantiate new Intents
                        Intent rollBPIntent = new Intent(getApplicationContext(),
                                BudgetPeriod.class);
                        Intent rollPPIntent = new Intent(getApplicationContext(), PayPeriod.class);

                        //Run object method to save to specified file with specified string
                        saveMethod.saveFile(getApplicationContext(), fileName, null,
                                details, false);

                        //Split string in to array
                        String[] dateparts = fileName.split("~");

                        //If statement to check if string contains BP (Budget Period or
                        //PP (Payment Period)
                        if(dateparts[2].contains("BP")) {

                            //Variables
                            String date1 = dateparts[0];
                            String date2 = dateparts[1];

                            //Object Method returning integer in to variable
                            int dayDifference = dCalc.calculateDays(date1, date2);

                            //Print variable to logcat
                            Log.d("dayDifference", Integer.toString(dayDifference));

                            //Object method returning string to variable
                            String newEndDate = dCalc.calculateDate(dayDifference,
                                    date2).substring(0, 10);

                            //Print variable to logcat
                            Log.d("newEndDate", newEndDate);

                            //Add extra information to intent
                            rollBPIntent.putExtra("newEndDate", newEndDate);
                            rollBPIntent.putExtra("fileName", date2);

                            //Object method returning string to variable
                            details = cleaner.Cleaner(details);

                            //Add extra information to intent
                            rollBPIntent.putExtra("details", details);

                            //Execute intent, starting next activity
                            startActivity(rollBPIntent);

                        } else if(dateparts[2].contains("PP")) {

                            //Variable
                            String date2 = dateparts[1];

                            //Object method returning string to variable
                            details = cleaner.Cleaner(details);

                            //Add extra information to intent
                            rollPPIntent.putExtra("details", details);
                            rollPPIntent.putExtra("fileName", date2);

                            //Execute intent, starting next activity
                            startActivity(rollPPIntent);
                        }

                    }
                });

                //Show dialog
                myDialog.show();
            }
        });

        //Set on click listener
        quickbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Set layout
                myDialog.setContentView(R.layout.activity_quickaddpopup);

                //Set elements to variables
                TextView close = myDialog.findViewById(R.id.quickAddExit);
                Button spend = myDialog.findViewById(R.id.spendBtn);
                Button fund = myDialog.findViewById(R.id.addFundsBtn);

                //Set on click listener
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Set on click listener
                spend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Set layout
                        myDialog.setContentView(R.layout.activity_spend_pop_up);

                        //Variable
                        final String category = "Leisure";

                        //Set elements to variables
                        TextView close = myDialog.findViewById(R.id.spendExit);
                        final EditText descriptionET =
                                myDialog.findViewById(R.id.spendDescriptionEditText);
                        final EditText amountET = myDialog.findViewById(R.id.spendAmountEditText);
                        Button submitBtn = myDialog.findViewById(R.id.spendSubmitBtnPop);

                        //Set on click listener
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Dismiss dialog
                                myDialog.dismiss();
                            }
                        });

                        //Set on click listener
                        submitBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                //Variables
                                String description = descriptionET.getText().toString();
                                String amount = amountET.getText().toString();
                                String string = description + ": -£" + amount + "\n";

                                //Append string to textview
                                transaction.append(string);

                                //Concatenate and add string to variable
                                details += category + "/" + description + "/" + amount + "\n";

                                //Function to generate string and assign to TextViews on layout
                                overviewGenerate(income, bills, taxes, debts, leisure, balance,
                                        additional, subscription, savings, savingsBalance);

                                //Run object method to save to specified file with specified string
                                saveMethod.saveFile(getApplicationContext(), fileName,
                                        null, details, false);

                                //Dismissdialog
                                myDialog.dismiss();
                            }
                    });
                    }
                });

                //Set on click listener
                fund.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Set layout
                        myDialog.setContentView(R.layout.activity_funds_pop_up);

                        //Variables
                        final String category = "Fund";

                        //Set elements to variables
                        TextView close = myDialog.findViewById(R.id.fundsExit);
                        final EditText descriptionET =
                                myDialog.findViewById(R.id.fundsDescriptionEditText);
                        final EditText amountET = myDialog.findViewById(R.id.fundsAmountEditText);
                        Button submitBtn = myDialog.findViewById(R.id.fundsSubmitBtnPop);

                        //Set on click listener
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });

                        //Set on click listener
                        submitBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                //Variables
                                String description = descriptionET.getText().toString();
                                String amount = amountET.getText().toString();
                                String string = description + ": +£" + amount + "\n";

                                //Append string to textview
                                transaction.append(string);

                                //Concatenate and add string to variable
                                details += category + "/" + description + "/" + amount + "\n";

                                //Function to generate string and assign to TextViews on layout
                                overviewGenerate(income, bills, taxes, debts, leisure, balance,
                                        additional, subscription, savings, savingsBalance);

                                //Run object method to save to specified file with specified string
                                saveMethod.saveFile(getApplicationContext(), fileName,
                                        null, details, false);

                                //Dismiss dialog
                                myDialog.dismiss();
                            }
                        });
                    }
                });

                //Show dialog
                myDialog.show();
            }
        });

        //Set on click listener
        breakdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reportIntent = new Intent(getApplicationContext(), Report.class);

                //Add extra information to intent
                reportIntent.putExtra("fileName", fileName);
                reportIntent.putExtra("details", details);

                //Execute intent, starting next activity
                startActivity(reportIntent);
            }
        });
    }

    //Function to generate string and assign to TextViews on layout
    public void overviewGenerate(TextView income, TextView bills, TextView taxes, TextView debts,
                                 TextView leisure, TextView balance, TextView additional,
                                 TextView subscription, TextView savings, TextView savingsBalance) {

        //Resetting variables
        incomeMoney = 0;
        billsMoney = 0;
        taxesMoney = 0;
        debtsMoney = 0;
        balanceMoney = 0;
        leisureMoney = 0;
        additionalMoney = 0;
        subscriptionMoney = 0;
        savingsMoney = 0;
        savingsBalanceMoney = 0;

        //Details split in to array detailsArr
        String[] detailsArr = details.split("\n");

        //Length of array put in to a variable
        int detailsLength = detailsArr.length;

        //For loop to break string down further and organise information to display on overview
        //using if else structure
        for(int i = 0; i <= detailsLength -1; i++) {
            String[] stringSplitter = detailsArr[i].split("/");

            // If and else if structure to organise information from detailsArr array
            if (stringSplitter[0].contains("Income")) {
                incomeMoney += Float.parseFloat(stringSplitter[2]);
            } else if (stringSplitter[0].contains("Bills")) {
                billsMoney += Float.parseFloat(stringSplitter[2]);
            } else if (stringSplitter[0].contains("Taxes")) {
                taxesMoney += Float.parseFloat(stringSplitter[2]);
            } else if (stringSplitter[0].contains("Debts")) {
                Log.d("stringSplit", stringSplitter[0]);
                debtsMoney += Float.parseFloat(stringSplitter[2]);
            } else if (stringSplitter[0].contains("Leisure")) {
                Log.d("stringSplit", stringSplitter[0]);
                leisureMoney += Float.parseFloat(stringSplitter[2]);
            } else if (stringSplitter[0].contains("Fund")) {
                Log.d("stringSplit", stringSplitter[0]);
                additionalMoney += Float.parseFloat(stringSplitter[2]);
            } else if (stringSplitter[0].contains("Subscription")) {
                Log.d("stringSplit", stringSplitter[0]);
                subscriptionMoney += Float.parseFloat(stringSplitter[2]);
            } else if (stringSplitter[0].contains("Savings")) {
                Log.d("stringSplit", stringSplitter[0]);
                savingsMoney += Float.parseFloat(stringSplitter[2]);
            }
        }

        //Reset TextViews
        income.setText("£");
        bills.setText("£");
        taxes.setText("£");
        debts.setText("£");
        leisure.setText("£");
        balance.setText("£");
        additional.setText("£");
        subscription.setText("£");
        savings.setText("£");
        savingsBalance.setText("£");

        //Append, and format floats in to string, in to textview
        income.append(String.format("%.2f", incomeMoney));
        bills.append(String.format("%.2f", billsMoney));
        taxes.append(String.format("%.2f", taxesMoney));
        debts.append(String.format("%.2f", debtsMoney));
        leisure.append(String.format("%.2f", leisureMoney));
        additional.append(String.format("%.2f", additionalMoney));
        subscription.append(String.format("%.2f", subscriptionMoney));
        savings.append(String.format("%.2f", savingsMoney));
        balance.append(String.format("%.2f", ((incomeMoney + additionalMoney) - (billsMoney +
                taxesMoney + debtsMoney + leisureMoney + subscriptionMoney + savingsMoney))));
        savingsBalance.append(String.format("%.2f", (((incomeMoney + additionalMoney) -
                (billsMoney + taxesMoney + debtsMoney + leisureMoney + subscriptionMoney)))));
    }

    //If back pressed on Android toolbar, execute the following
    public void onBackPressed() {

        //Instantiate object for method use
        SaveFile saveMethod = new SaveFile();

        //Saves specific string to specific file
        saveMethod.saveFile(getApplicationContext(), fileName, null, details, false);

        //Execute intent, starting next activity
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

        //End activity
        finish();
    }

}
