package com.example.pyf;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class budgetCreation extends AppCompatActivity {
    Dialog myDialog;
    TextView actionLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_creation);
        actionLog = findViewById(R.id.actionLogTextView);
        actionLog.setMovementMethod(new ScrollingMovementMethod());
        myDialog = new Dialog(this);


        Button incomeDetails = findViewById(R.id.incomeBtn);
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
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        Button billsDetails = (Button) findViewById(R.id.billsBtn);
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
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        Button taxesDetails = (Button) findViewById(R.id.taxesBtn);
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
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        Button debtsDetails = (Button) findViewById(R.id.debtsBtn);
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
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

    } //OnCreate

}
