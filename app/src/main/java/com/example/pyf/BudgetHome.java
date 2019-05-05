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
import java.text.DecimalFormat;

public class BudgetHome extends AppCompatActivity {

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
        setContentView(R.layout.budgethome);

        fileName = getIntent().getStringExtra("fileName");

        final SaveFile saveMethod = new SaveFile();
        final loadFile loadMethod = new loadFile();

        ImageButton editbtn = findViewById(R.id.btn_edit);
        ImageButton exportbtn = findViewById(R.id.btn_archive);
        ImageButton newbtn = findViewById(R.id.btn_New);
        ImageButton quickbtn = findViewById(R.id.btn_Quick);


        ImageButton breakdown = findViewById(R.id.btn_Breakdown);

        transaction = findViewById(R.id.transactionHistoryTextView);
        transaction.setMovementMethod(new ScrollingMovementMethod());

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

        myDialog = new Dialog(this);


        details = loadMethod.FileLoader(fileName, null , getApplicationContext());

        overviewGenerate(income, bills, taxes, debts, leisure, balance, additional, subscription,
                savings, savingsBalance);


        transaction.setMovementMethod(new ScrollingMovementMethod());




        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView close;
                Button editFile;
                Button deleteDetails;
                myDialog.setContentView(R.layout.edit_pop_up);
                close = myDialog.findViewById(R.id.editExit);
                editFile = myDialog.findViewById(R.id.btn_editEdit);
                deleteDetails = myDialog.findViewById(R.id.btn_delete);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                editFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent budgetcreationIntent = new Intent(getApplicationContext(), EditBudget.class);
                        saveMethod.saveFile(getApplicationContext(), fileName, null,
                                details, false);
                        budgetcreationIntent.putExtra("fileName", fileName);
                        startActivity(budgetcreationIntent);
                    }
                });

                deleteDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent deleteIntent = new Intent(getApplicationContext(),
                                DeleteEntries.class);
                        saveMethod.saveFile(getApplicationContext(), fileName, null,
                                details, false);
                        deleteIntent.putExtra("fileName", fileName);
                        startActivity(deleteIntent);
                    }
                });
                myDialog.show();
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
                        saveMethod.saveFile(getApplicationContext(), fileName, null,
                                details, false);
                        startActivity(newIntent);
                    }
                });

                rollOver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dateCalculator dCalc = new dateCalculator();
                        RollOverCleaner cleaner = new RollOverCleaner();
                        Intent rollBPIntent = new Intent(getApplicationContext(),
                                BudgetPeriod.class);
                        Intent rollPPIntent = new Intent(getApplicationContext(), PayPeriod.class);
                        saveMethod.saveFile(getApplicationContext(), fileName, null,
                                details, false);
                        String[] dateparts = fileName.split("~");
                        if(dateparts[2].contains("BP")) {
                            String date1 = dateparts[0];
                            String date2 = dateparts[1];
                            int dayDifference = dCalc.calculateDays(date1, date2);
                            Log.d("dayDifference", Integer.toString(dayDifference));

                            String newEndDate = dCalc.calculateDate(dayDifference,
                                    date2).substring(0, 10);
                            Log.d("newEndDate", newEndDate);

                            rollBPIntent.putExtra("newEndDate", newEndDate);
                            rollBPIntent.putExtra("fileName", date2);
                            details = cleaner.Cleaner(details);
                            rollBPIntent.putExtra("details", details);
                            startActivity(rollBPIntent);
                        } else if(dateparts[2].contains("PP")) {
                            String date2 = dateparts[1];

                            details = cleaner.Cleaner(details);
                            rollPPIntent.putExtra("details", details);
                            rollPPIntent.putExtra("fileName", date2);
                            startActivity(rollPPIntent);
                        }

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
                        final EditText descriptionET =
                                myDialog.findViewById(R.id.spendDescriptionEditText);
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
                                overviewGenerate(income, bills, taxes, debts, leisure, balance,
                                        additional, subscription, savings, savingsBalance);
                                saveMethod.saveFile(getApplicationContext(), fileName,
                                        null, details, false);
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
                        final EditText descriptionET =
                                myDialog.findViewById(R.id.fundsDescriptionEditText);
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
                                overviewGenerate(income, bills, taxes, debts, leisure, balance,
                                        additional, subscription, savings, savingsBalance);
                                saveMethod.saveFile(getApplicationContext(), fileName,
                                        null, details, false);
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
                Intent reportIntent = new Intent(getApplicationContext(), Report.class);
                reportIntent.putExtra("fileName", fileName);
                reportIntent.putExtra("details", details);

                startActivity(reportIntent);
            }
        });
    }

    public void overviewGenerate(TextView income, TextView bills, TextView taxes, TextView debts,
                                 TextView leisure, TextView balance, TextView additional,
                                 TextView subscription, TextView savings, TextView savingsBalance) {

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

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        String[] detailsArr = details.split("\n");
        int detailsLength = detailsArr.length;
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

    public void onBackPressed() {
        SaveFile saveMethod = new SaveFile();
        saveMethod.saveFile(getApplicationContext(), fileName, null, details, false);

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

}
