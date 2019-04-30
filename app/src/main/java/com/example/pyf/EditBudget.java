package com.example.pyf;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EditBudget extends AppCompatActivity {

    Dialog myDialog;
    TextView actionLog;
    String fileLog = "";
    boolean editCheck = false;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);

        fileName = getIntent().getStringExtra("fileName");
        actionLog = findViewById(R.id.editactionLogTextView);
        actionLog.setMovementMethod(new ScrollingMovementMethod());
        myDialog = new Dialog(this);
        final SaveFile save = new SaveFile();
        final loadFile load = new loadFile();

        Button donebtn = findViewById(R.id.editdoneBtn);
        Button incomeDetails = findViewById(R.id.editincomeBtn);
        Button billsDetails = findViewById(R.id.editbillsBtn);
        Button taxesDetails = findViewById(R.id.edittaxesBtn);
        Button debtsDetails = findViewById(R.id.editdebtsBtn);

        fileLog = load.FileLoader(fileName, null, getApplicationContext());
        Toast toast = Toast.makeText(getApplicationContext(), fileLog, Toast.LENGTH_LONG);
        toast.show();

        incomeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtclose;
                Button submitBtnPop;
                final String category = "Income";
                myDialog.setContentView(R.layout.custompopup);
                final EditText descriptionET = myDialog.findViewById(R.id.descriptionEditText);
                final EditText amountET = myDialog.findViewById(R.id.amountEditText);
                txtclose = myDialog.findViewById(R.id.exit);
                submitBtnPop = myDialog.findViewById(R.id.submitBtnPop);
                Toast toast = Toast.makeText(getApplicationContext(), fileLog, Toast.LENGTH_LONG);
                toast.show();
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
                        fileLog += category + " " + description + " " + amount + "\n";
                        if(!editCheck) {
                            editCheck = true;
                        }
                        Toast toast = Toast.makeText(getApplicationContext(), fileLog, Toast.LENGTH_LONG);
                        toast.show();
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
                        fileLog += category + " " + description + " " + amount + "\n";
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
                        fileLog += category + " " + description + " " + amount + "\n";
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
                        fileLog += category + " " + description + " " + amount + "\n";
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
                    Toast toast = Toast.makeText(getApplicationContext(), fileLog, Toast.LENGTH_LONG);
                    toast.show();
                    startActivity(doneIntent);

                } else {
                    Toast toasst = Toast.makeText(getApplicationContext(), "File has not been" +
                            "updated: No changes specified.", Toast.LENGTH_LONG);
                    toasst.show();
                    startActivity(doneIntent);
                }
            }
        });
    }
}
