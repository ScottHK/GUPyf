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

    // Global Variables
    Dialog myDialog;
    TextView actionLog;
    String fileLog = "";
    boolean editCheck = false;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Layout to be used
        setContentView(R.layout.activity_edit_budget);

        //Retrieve packed info
        fileName = getIntent().getStringExtra("fileName");

        //Set elements to variables
        actionLog = findViewById(R.id.editactionLogTextView);
        Button donebtn = findViewById(R.id.editdoneBtn);
        ImageButton incomeDetails = findViewById(R.id.editincomeBtn);
        ImageButton billsDetails = findViewById(R.id.editbillsBtn);
        ImageButton taxesDetails = findViewById(R.id.edittaxesBtn);
        ImageButton debtsDetails = findViewById(R.id.editdebtsBtn);
        ImageButton subscriptionDetails = findViewById(R.id.editsubscriptionBtn);
        ImageButton savingsDetails = findViewById(R.id.editsavingsBtn);

        //Set scrolling method to textview
        actionLog.setMovementMethod(new ScrollingMovementMethod());

        //Instantiate dialog
        myDialog = new Dialog(this);

        //Instantiate objects for method use
        final SaveFile save = new SaveFile();
        final loadFile load = new loadFile();

        //Return string from object method
        fileLog = load.FileLoader(fileName, null, getApplicationContext());

        //Set on click listener
        incomeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Variables
                TextView txtclose;
                Button submitBtnPop;
                final String category = "Income";

                //Set layout
                myDialog.setContentView(R.layout.custompopup);

                //Set elements to variables
                final EditText descriptionET = myDialog.findViewById(R.id.descriptionEditText);
                final EditText amountET = myDialog.findViewById(R.id.amountEditText);
                txtclose = myDialog.findViewById(R.id.exit);
                submitBtnPop = myDialog.findViewById(R.id.submitBtnPop);

                //Set on click listener
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Set on click listener
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Variables
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();

                        //Concatenate and add string to variable
                        String string = category + ": " + description + " - £" + amount + "\n";
                        fileLog += category + "/" + description + "/" + amount + "\n";

                        //Append string to textview
                        actionLog.append(string);

                        //If statement to change edit check control from false to true
                        if(!editCheck) {
                            editCheck = true;
                        }

                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        //Set on click listener
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

                //Set on click listener
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Set on click listener
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Variables
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();

                        //Concatenate and add string to variable
                        String string = category + ": " + description + " - £" + amount + "\n";
                        fileLog += category + "/" + description + "/" + amount + "\n";

                        //Append string to textview
                        actionLog.append(string);

                        //If statement to change edit check control from false to true
                        if(!editCheck) {
                            editCheck = true;
                        }
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        //Set on click listener
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

                //Set on click listener
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Set on click listener
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Variables
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();

                        //Concatenate and add string to variable
                        String string = category + ": " + description + " - £" + amount + "\n";
                        fileLog += category + "/" + description + "/" + amount + "\n";

                        //Append string to textview
                        actionLog.append(string);

                        //If statement to change edit check control from false to true
                        if(!editCheck) {
                            editCheck = true;
                        }
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        //Set on click listener
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

                //Set on click listener
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Set on click listener
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Variables
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();

                        //Concatenate and add string to variable
                        String string = category + ": " + description + " - £" + amount + "\n";
                        fileLog += category + "/" + description + "/" + amount + "\n";

                        //Append string to textview
                        actionLog.append(string);

                        //If statement to change edit check control from false to true
                        if(!editCheck) {
                            editCheck = true;
                        }
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        //Set on click listener
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

                //Set on click listener
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Set on click listener
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Variables
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();

                        //Concatenate and add string to variable
                        String string = category + ": " + description + " - £" + amount + "\n";
                        fileLog += category + "/" + description + "/" + amount + "\n";

                        //Append string to textview
                        actionLog.append(string);

                        //If statement to change edit check control from false to true
                        if(!editCheck) {
                            editCheck = true;
                        }
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        //Set on click listener
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

                //Set on click listener
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                //Set on click listener
                submitBtnPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Variables
                        String description = descriptionET.getText().toString();
                        String amount = amountET.getText().toString();

                        //Concatenate and add string to variable
                        String string = category + ": " + description + " - £" + amount + "\n";
                        fileLog += category + "/" + description + "/" + amount + "\n";

                        //Append string to textview
                        actionLog.append(string);

                        //If statement to change edit check control from false to true
                        if(!editCheck) {
                            editCheck = true;
                        }
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        //Set on click listener
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Instantiate Intent
                Intent doneIntent = new Intent(getApplicationContext(), BudgetHome.class);

                //Uses edit check to verify if any additions have been done
                if(editCheck) {

                    //Object method savefile to save details using file name and string
                    save.saveFile(getApplicationContext(), fileName, null, fileLog,
                            false);

                    //Add extra information to intent
                    doneIntent.putExtra("fileName", fileName);

                    //Execute intent
                    startActivity(doneIntent);

                } else {

                    //Toast for app responsiveness and early development error handling
                    Toast toasst = Toast.makeText(getApplicationContext(), "File has not been " +
                            "updated: No changes specified.", Toast.LENGTH_LONG);

                    //Show toast
                    toasst.show();

                    //Add extra information to intent
                    doneIntent.putExtra("fileName", fileName);

                    //Execute intent
                    startActivity(doneIntent);
                }
            }
        });
    }
}
