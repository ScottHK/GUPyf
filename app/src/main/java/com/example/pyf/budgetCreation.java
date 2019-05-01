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
    Dialog myDialog;
    TextView actionLog;
    String fileLog = "";
    boolean editCheck = false;
    boolean isBudgetPeriod;
    String fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_creation);
        fileName = getIntent().getStringExtra("date")+ ".txt";
        actionLog = findViewById(R.id.actionLogTextView);
        actionLog.setMovementMethod(new ScrollingMovementMethod());
        myDialog = new Dialog(this);
        final SaveFile save = new SaveFile();

        if(getIntent().getBooleanExtra("isBudgetPeriod", false)) {
            isBudgetPeriod = true;
        }

        Button donebtn = findViewById(R.id.doneBtn);
        Button incomeDetails = findViewById(R.id.incomeBtn);
        Button billsDetails = findViewById(R.id.billsBtn);
        Button taxesDetails = findViewById(R.id.taxesBtn);
        Button debtsDetails = findViewById(R.id.debtsBtn);


        incomeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtclose;
                Button submitBtnPop;
                final String category = "Income";
                myDialog.setContentView(R.layout.custompopup);
                final EditText descriptionET = myDialog.findViewById(R.id.descriptionEditText);
                final EditText amountET = myDialog.findViewById(R.id.amountEditText);
                txtclose = (TextView) myDialog.findViewById(R.id.exit);
                submitBtnPop = (Button) myDialog.findViewById(R.id.submitBtnPop);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();
                        String string = category + ": " + description + " - £" + amount + "\n";
                        actionLog.append(string);
                        fileLog += category + "/" + description + "/" + amount + "\n";
                        if(!editCheck) {
                            editCheck = true;
                        }
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });


        billsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtclose;
                Button submitBtnPop;
                final String category = "Bills";
                myDialog.setContentView(R.layout.custompopup);
                final EditText descriptionET = myDialog.findViewById(R.id.descriptionEditText);
                final EditText amountET = myDialog.findViewById(R.id.amountEditText);
                txtclose = (TextView) myDialog.findViewById(R.id.exit);
                submitBtnPop = (Button) myDialog.findViewById(R.id.submitBtnPop);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();
                        String string = category + ": " + description + " - £" + amount + "\n";
                        actionLog.append(string);
                        fileLog += category + "/" + description + "/" + amount + "\n";
                        if(!editCheck) {
                            editCheck = true;
                        }
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        taxesDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtclose;
                Button submitBtnPop;
                final String category = "Taxes";
                myDialog.setContentView(R.layout.custompopup);
                final EditText descriptionET = myDialog.findViewById(R.id.descriptionEditText);
                final EditText amountET = myDialog.findViewById(R.id.amountEditText);
                txtclose = myDialog.findViewById(R.id.exit);
                submitBtnPop = myDialog.findViewById(R.id.submitBtnPop);

                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();
                        String string = category + ": " + description + " - £" + amount + "\n";
                        actionLog.append(string);
                        fileLog += category + "/" + description + "/" + amount + "\n";
                        if(!editCheck) {
                            editCheck = true;
                        }
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });


        debtsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtclose;
                Button submitBtnPop;
                final String category = "Debts";
                myDialog.setContentView(R.layout.custompopup);
                final EditText descriptionET = myDialog.findViewById(R.id.descriptionEditText);
                final EditText amountET = myDialog.findViewById(R.id.amountEditText);
                txtclose = (TextView) myDialog.findViewById(R.id.exit);
                submitBtnPop = (Button) myDialog.findViewById(R.id.submitBtnPop);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();
                        String string = category + ": " + description + " - £" + amount + "\n";
                        actionLog.append(string);
                        fileLog += category + "/" + description + "/" + amount + "\n";
                        if(!editCheck) {
                            editCheck = true;
                        }
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doneIntent = new Intent(getApplicationContext(), BudgetHome.class);
                if(editCheck) {

                    save.saveFile(getApplicationContext(), fileName, null, fileLog, false);

                    doneIntent.putExtra("fileName", fileName);
                    if(isBudgetPeriod){
                        doneIntent.putExtra("isBudgetPeriod", true);
                    }
                    Toast toast = Toast.makeText(getApplicationContext(), fileLog, Toast.LENGTH_LONG);
                    toast.show();
                    startActivity(doneIntent);

                } else {
                    Toast toasst = Toast.makeText(getApplicationContext(), "File has not been" +
                            "updated: No changes specified.", Toast.LENGTH_SHORT);
                    toasst.show();
                    startActivity(doneIntent);
                }
            }
        });

    }

}
