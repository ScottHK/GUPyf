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
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BudgetHome extends AppCompatActivity {

    Dialog myDialog;
    String details;
    TextView transaction;
    String fileName;
    int incomeMoney;
    int billsMoney;
    int taxesMoney;
    int debtsMoney;
    int leisureMoney;
    int balanceMoney;
    int additionalMoney;
    int subscriptionMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgethome);

        fileName = getIntent().getStringExtra("fileName");
        Log.d("dateEnd", fileName);

        final SaveFile saveMethod = new SaveFile();
        final loadFile loadMethod = new loadFile();

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

        myDialog = new Dialog(this);

        details = loadMethod.FileLoader(fileName, null , getApplicationContext());

        overviewGenerate(income, bills, taxes, debts, leisure, balance, additional, subscription);


        transaction.setMovementMethod(new ScrollingMovementMethod());




        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent budgetcreationIntent = new Intent(getApplicationContext(), EditBudget.class);

                budgetcreationIntent.putExtra("fileName", fileName);
                startActivity(budgetcreationIntent);
                //TODO File dump
            }
        });

        exportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView close;
                Button device;
                Button gdrive;

                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {


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

                myDialog.setContentView(R.layout.activity_exportarchive);
                close = myDialog.findViewById(R.id.archiveExit);
                device = myDialog.findViewById(R.id.deviceBtn);
                gdrive = myDialog.findViewById(R.id.googleDriveBtn);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                device.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Export export = new Export();
                        try {
                            String exportFile = fileName.replace("txt", "csv");
                            export.exportCSV(exportFile, details, getApplicationContext());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                myDialog.show();

            }
        });

        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView close;
                Button newFile;
                Button rollOver;
                myDialog.setContentView(R.layout.activity_start_new);
                close = myDialog.findViewById(R.id.startNewExit);
                newFile = myDialog.findViewById(R.id.startNewBtn);
                rollOver = myDialog.findViewById(R.id.rollOverBtn);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                newFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent newIntent = new Intent(getApplicationContext(), MainActivity.class);
                        saveMethod.saveFile(getApplicationContext(), fileName, null, details, false);
                        startActivity(newIntent);
                    }
                });

                rollOver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dateCalculator dCalc = new dateCalculator();
                        RollOverCleaner cleaner = new RollOverCleaner();
                        Intent rollIntent = new Intent(getApplicationContext(), BudgetPeriod.class);
                        saveMethod.saveFile(getApplicationContext(), fileName, null, details, false);
                        String[] dateparts = fileName.split("~");
                        if(dateparts[2].contains("BP")) {
                            String date1 = dateparts[0];
                            String date2 = dateparts[1];
                            int dayDifference = dCalc.calculateDays(date1, date2);
                            Log.d("dayDifference", Integer.toString(dayDifference));

                            String newEndDate = dCalc.calculateDate(dayDifference, date2).substring(0, 10);
                            Log.d("newEndDate", newEndDate);

                            rollIntent.putExtra("newEndDate", newEndDate);
                            rollIntent.putExtra("fileName", date2);
                        } else if(dateparts[2].contains("PP")) {
                            String date2 = dateparts[1];

                            rollIntent.putExtra("fileName", date2);
                        }
                        cleaner.Cleaner(details);
                        rollIntent.putExtra("details", details);

                        startActivity(rollIntent);
                    }
                });
                myDialog.show();
            }
        });

        quickbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.setContentView(R.layout.activity_quickaddpopup);

                TextView close = myDialog.findViewById(R.id.quickAddExit);
                Button spend = myDialog.findViewById(R.id.spendBtn);
                Button fund = myDialog.findViewById(R.id.addFundsBtn);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                spend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.setContentView(R.layout.activity_spend_pop_up);

                        final String category = "Leisure";
                        TextView close = myDialog.findViewById(R.id.spendExit);
                        final EditText descriptionET = myDialog.findViewById(R.id.spendDescriptionEditText);
                        final EditText amountET = myDialog.findViewById(R.id.spendAmountEditText);
                        Button submitBtn = myDialog.findViewById(R.id.spendSubmitBtnPop);

                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });

                        submitBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String description = descriptionET.getText().toString();
                                String amount = amountET.getText().toString();
                                String string = description + ": -£" + amount + "\n";
                                transaction.append(string);
                                details += category + "/" + description + "/" + amount + "\n";
                                overviewGenerate(income, bills, taxes, debts, leisure, balance, additional, subscription);
                                saveMethod.saveFile(getApplicationContext(), fileName, null, details, false);
                                myDialog.dismiss();
                            }
                    });
                    }
                });

                fund.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.setContentView(R.layout.activity_funds_pop_up);

                        final String category = "Fund";
                        TextView close = myDialog.findViewById(R.id.fundsExit);
                        final EditText descriptionET = myDialog.findViewById(R.id.fundsDescriptionEditText);
                        final EditText amountET = myDialog.findViewById(R.id.fundsAmountEditText);
                        Button submitBtn = myDialog.findViewById(R.id.fundsSubmitBtnPop);

                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });

                        submitBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String description = descriptionET.getText().toString();
                                String amount = amountET.getText().toString();
                                String string = description + ": +£" + amount + "\n";
                                transaction.append(string);
                                details += category + "/" + description + "/" + amount + "\n";
                                overviewGenerate(income, bills, taxes, debts, leisure, balance, additional, subscription);
                                saveMethod.saveFile(getApplicationContext(), fileName, null, details, false);
                                myDialog.dismiss();
                            }
                        });
                    }
                });
                myDialog.show();
            }
        });

        breakdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), details, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    public void overviewGenerate(TextView income, TextView bills, TextView taxes, TextView debts, TextView leisure, TextView balance, TextView additional, TextView subscription) {

        incomeMoney = 0;
        billsMoney = 0;
        taxesMoney = 0;
        debtsMoney = 0;
        balanceMoney = 0;
        leisureMoney = 0;
        additionalMoney = 0;
        subscriptionMoney = 0;


        String[] detailsArr = details.split("\n");
        int detailsLength = detailsArr.length;
        for(int i = 0; i <= detailsLength -1; i++) {
            String[] stringSplitter = detailsArr[i].split("/");

            if (stringSplitter[0].contains("Income")) {
                incomeMoney += Integer.parseInt(stringSplitter[2]);
            } else if (stringSplitter[0].contains("Bills")) {
                billsMoney += Integer.parseInt(stringSplitter[2]);
            } else if (stringSplitter[0].contains("Taxes")) {
                taxesMoney += Integer.parseInt(stringSplitter[2]);
            } else if (stringSplitter[0].contains("Debts")) {
                Log.d("stringSplit", stringSplitter[0]);
                debtsMoney += Integer.parseInt(stringSplitter[2]);
            } else if (stringSplitter[0].contains("Leisure")) {
                Log.d("stringSplit", stringSplitter[0]);
                leisureMoney += Integer.parseInt(stringSplitter[2]);
            } else if (stringSplitter[0].contains("Fund")) {
                Log.d("stringSplit", stringSplitter[0]);
                additionalMoney += Integer.parseInt(stringSplitter[2]);
            } else if (stringSplitter[0].contains("Subscription")) {
                Log.d("stringSplit", stringSplitter[0]);
                subscriptionMoney += Integer.parseInt(stringSplitter[2]);
            }
        }

        income.setText("£");
        bills.setText("£");
        taxes.setText("£");
        debts.setText("£");
        leisure.setText("£");
        balance.setText("£");
        additional.setText("£");
        subscription.setText("£");
        income.append(Integer.toString(incomeMoney));
        bills.append(Integer.toString(billsMoney));
        taxes.append(Integer.toString(taxesMoney));
        debts.append(Integer.toString(debtsMoney));
        leisure.append(Integer.toString(leisureMoney));
        additional.append(Integer.toString(additionalMoney));
        subscription.append((Integer.toString(subscriptionMoney)));
        balance.append(Integer.toString((incomeMoney + additionalMoney) - (billsMoney + taxesMoney + debtsMoney + leisureMoney + subscriptionMoney)));
    }

}
