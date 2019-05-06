package com.example.pyf;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set layout
        setContentView(R.layout.activity_report);

        //Variable
        String details = getIntent().getStringExtra("details");
        boolean pieChartGenerate = false;

        //Instantiate Intent
        final Intent budgetHome = new Intent(getApplicationContext(), BudgetHome.class);

        //add extra information to intent
        budgetHome.putExtra("fileName", getIntent().getStringExtra("fileName"));
        budgetHome.putExtra("details", details);

        //Set elements to variables
        ImageView backBtn = findViewById(R.id.iv_reportBackBtn);
        TextView leisureEntry = findViewById(R.id.tv_LeisureEntries);
        TextView fundsEntry = findViewById(R.id.tv_FundsEntries);
        TextView subscriptionsEntry = findViewById(R.id.tv_SubscriptionsEntries);
        TextView incomeEntry = findViewById(R.id.tv_IncomeEntries);
        TextView billsEntry = findViewById(R.id.tv_BillsEntries);
        TextView taxesEntry = findViewById(R.id.tv_TaxesEntries);
        TextView debtsEntry = findViewById(R.id.tv_DebtsEntries);
        TextView savingsEntry = findViewById(R.id.tv_SavingsEntries);

        //Initialized Textviews
        leisureEntry.append("");
        fundsEntry.append("");
        subscriptionsEntry.append("");
        incomeEntry.append("");
        billsEntry.append("");
        taxesEntry.append("");
        debtsEntry.append("");

        //Creating arrays and variables
        String[] detailsArr = details.split("\n");
        int detailsLength = detailsArr.length;
        String[] incomeDescriptions = new String[detailsLength];
        String[] incomeValues = new String[detailsLength];
        String[] billsDescriptions = new String[detailsLength];
        String[] billsValues = new String[detailsLength];
        String[] taxesDescriptions = new String[detailsLength];
        String[] taxesValues = new String[detailsLength];
        String[] debtsDescriptions = new String[detailsLength];
        String[] debtsValues = new String[detailsLength];
        String[] leisureDescriptions = new String[detailsLength];
        String[] leisureValues = new String[detailsLength];
        String[] fundDescriptions = new String[detailsLength];
        String[] fundValues = new String[detailsLength];
        String[] subscriptionDescriptions = new String[detailsLength];
        String[] subscriptionValues = new String[detailsLength];
        String[] savingsDescriptions = new String[detailsLength];
        String[] savingsValues = new String[detailsLength];
        float workSpace = 0;

        //For loop to organise information in to correct categories, extract descriptions and
        //add values
        for(int i = 0; i <= detailsLength -1; i++) {

            //Splitting string in to array
            String[] stringSplitter = detailsArr[i].split("/");

            //Control structure for checking category
            if (stringSplitter[0].contains("Income")) {

                //Checks if the description already exists. If it does, take value, delete duplicate
                //description
                if(Arrays.asList(incomeDescriptions).contains(stringSplitter[1])) {
                    workSpace = Float.parseFloat(incomeValues[Arrays.asList(incomeDescriptions).indexOf(stringSplitter[1])]) +
                            Float.parseFloat(stringSplitter[2]);
                    incomeValues[Arrays.asList(incomeDescriptions).indexOf(stringSplitter[1])] =
                            Float.toString(workSpace);

                    //Otherwise add unique description to list
                } else {
                    incomeDescriptions[i] = stringSplitter[1];
                    incomeValues[i] = stringSplitter[2];
                }

                //Control structure for checking category
            } else if (stringSplitter[0].contains("Bills")) {

                //Checks if the description already exists. If it does, take value, delete duplicate
                //description
                if(Arrays.asList(billsDescriptions).contains(stringSplitter[1])) {
                    workSpace = Float.parseFloat(billsValues[Arrays.asList(billsDescriptions).indexOf(stringSplitter[1])]) +
                            Float.parseFloat(stringSplitter[2]);
                    billsValues[Arrays.asList(billsDescriptions).indexOf(stringSplitter[1])] =
                            Float.toString(workSpace);

                    //Otherwise add unique description to list
                } else {
                    billsDescriptions[i] = stringSplitter[1];
                    billsValues[i] = stringSplitter[2];
                }

                //Control structure for checking category
            } else if (stringSplitter[0].contains("Taxes")) {

                //Checks if the description already exists. If it does, take value, delete duplicate
                //description
                if(Arrays.asList(taxesDescriptions).contains(stringSplitter[1])) {
                    workSpace = Float.parseFloat(taxesValues[Arrays.asList(taxesDescriptions).indexOf(stringSplitter[1])]) +
                            Float.parseFloat(stringSplitter[2]);
                    taxesValues[Arrays.asList(taxesDescriptions).indexOf(stringSplitter[1])] =
                            Float.toString(workSpace);

                    //Otherwise add unique description to list
                } else {
                    taxesDescriptions[i] = stringSplitter[1];
                    taxesValues[i] = stringSplitter[2];
                }

                //Control structure for checking category
            } else if (stringSplitter[0].contains("Debts")) {

                //Checks if the description already exists. If it does, take value, delete duplicate
                //description
                if(Arrays.asList(debtsDescriptions).contains(stringSplitter[1])) {
                    workSpace = Float.parseFloat(debtsValues[Arrays.asList(debtsDescriptions).indexOf(stringSplitter[1])]) +
                            Float.parseFloat(stringSplitter[2]);
                    debtsValues[Arrays.asList(debtsDescriptions).indexOf(stringSplitter[1])] =
                            Float.toString(workSpace);

                    //Otherwise add unique description to list
                } else {
                    debtsDescriptions[i] = stringSplitter[1];
                    debtsValues[i] = stringSplitter[2];
                }

                //Control structure for checking category
            } else if (stringSplitter[0].contains("Leisure")) {

                //If a Leisure entry exists, generate pie chart
                pieChartGenerate = true;

                //Checks if the description already exists. If it does, take value, delete duplicate
                //description
                if(Arrays.asList(leisureDescriptions).contains(stringSplitter[1])) {
                    workSpace = Float.parseFloat(leisureValues[Arrays.asList(leisureDescriptions).indexOf(stringSplitter[1])]) +
                            Float.parseFloat(stringSplitter[2]);
                    leisureValues[Arrays.asList(leisureDescriptions).indexOf(stringSplitter[1])] =
                            Float.toString(workSpace);

                    //Otherwise add unique description to list
                } else {
                    leisureDescriptions[i] = stringSplitter[1];
                    leisureValues[i] = stringSplitter[2];
                }

                //Control structure for checking category
            } else if (stringSplitter[0].contains("Fund")) {

                //Checks if the description already exists. If it does, take value, delete duplicate
                //description
                if(Arrays.asList(fundDescriptions).contains(stringSplitter[1])) {
                    workSpace = Float.parseFloat(fundValues[Arrays.asList(fundDescriptions).indexOf(stringSplitter[1])]) +
                            Float.parseFloat(stringSplitter[2]);
                    fundValues[Arrays.asList(fundDescriptions).indexOf(stringSplitter[1])] =
                            Float.toString(workSpace);

                    //Otherwise add unique description to list
                } else {
                    fundDescriptions[i] = stringSplitter[1];
                    fundValues[i] = stringSplitter[2];
                }

                //Control structure for checking category
            } else if (stringSplitter[0].contains("Subscription")) {

                //Checks if the description already exists. If it does, take value, delete duplicate
                //description
                if(Arrays.asList(subscriptionDescriptions).contains(stringSplitter[1])) {
                    workSpace = Float.parseFloat(subscriptionValues[Arrays.asList(subscriptionDescriptions).indexOf(stringSplitter[1])]) +
                            Float.parseFloat(stringSplitter[2]);
                    subscriptionValues[Arrays.asList(subscriptionDescriptions).indexOf(stringSplitter[1])] =
                            Float.toString(workSpace);

                    //Otherwise add unique description to list
                } else {
                    subscriptionDescriptions[i] = stringSplitter[1];
                    subscriptionValues[i] = stringSplitter[2];
                }

                //Control structure for checking category
            } else if (stringSplitter[0].contains("Savings")) {

                //Checks if the description already exists. If it does, take value, delete duplicate
                //description
                if (Arrays.asList(savingsDescriptions).contains(stringSplitter[1])) {
                    workSpace = Float.parseFloat(savingsValues[Arrays.asList(savingsDescriptions).indexOf(stringSplitter[1])]) +
                            Float.parseFloat(stringSplitter[2]);
                    savingsValues[Arrays.asList(savingsDescriptions).indexOf(stringSplitter[1])] =
                            Float.toString(workSpace);

                    //Otherwise add unique description to list
                } else {
                    savingsDescriptions[i] = stringSplitter[1];
                    savingsValues[i] = stringSplitter[2];
                }
            }
        }

        //Append textviews with returned string from viewBuilder()
        incomeEntry.append(viewBuilder(incomeDescriptions, incomeValues));
        billsEntry.append(viewBuilder(billsDescriptions, billsValues));
        taxesEntry.append(viewBuilder(taxesDescriptions, taxesValues));
        debtsEntry.append(viewBuilder(debtsDescriptions, debtsValues));
        fundsEntry.append(viewBuilder(fundDescriptions, fundValues));
        leisureEntry.append(viewBuilder(leisureDescriptions, leisureValues));
        subscriptionsEntry.append(viewBuilder(subscriptionDescriptions, subscriptionValues));
        savingsEntry.append(viewBuilder(savingsDescriptions, savingsValues));

        //Control structure to specify if pie chart needs to be constructed
        if(pieChartGenerate) {
            pieConstructor(leisureDescriptions, leisureValues);
        }

        //Set on click listener
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Execute intent
                startActivity(budgetHome);
            }
        });

    }

    public static String viewBuilder( String[] description, String[] values) {

        //Instantiate objects
        List<String> stringList = new ArrayList<>();
        List<String> intList = new ArrayList<>();

        //Variable
        String result = "";

        //For loop to construct list
        for(String s : description) {
            if(s != null && s.length() > 0) {
                stringList.add("• " + s);
            }
        }

        //For loop to construct string arraylist
        for(String s : values) {
            if(s != null && s.length() > 0) {
                intList.add("£" + s);
            }
        }

        //For loop to concatenate string to be returned
        for(int i = 0; i < stringList.size(); i++) {
            result += stringList.get(i) + " - " + intList.get(i) + "\n";
        }

        return result;
    }

    public void pieConstructor(String[] description, String[] values) {

        //Instantiate arraylists
        List<String> stringList = new ArrayList<>();
        List<String> intList = new ArrayList<>();
        List<SliceValue> pieData = new ArrayList<>();

        //Set element to variable
        PieChartView pieChartView = findViewById(R.id.chart);

        //Variables
        float maxValue = 0;
        String result = "";
        int[] colors = {Color.BLACK, Color.DKGRAY,
                Color.GREEN, Color.BLUE, Color.GRAY};

        //For loop to construct list
        for(String s : description) {
            if(s != null && s.length() > 0) {
                stringList.add(s);
            }
        }

        //For loop to construct list
        for(String s : values) {
            if(s != null && s.length() > 0) {
                intList.add(s);
            }
        }

        //For loop to get maximum value
        for(int i = 0; i < intList.size(); i++) {
            maxValue += Float.parseFloat(intList.get(i));
        }

        //For loop to get entry percentage of maximum value
        for(int z = 0; z < stringList.size(); z++) {
            pieData.add(new SliceValue(((Float.parseFloat(intList.get(z))/ maxValue)*100),
                    colors[z%5]).setLabel(stringList.get(z) + " " +
                    String.format("%.2f", ((Float.parseFloat(intList.get(z))/ maxValue)*100)) + "%"));
        }
        //Instantiate object
        PieChartData pieChartData = new PieChartData(pieData);

        //Set pie section label
        pieChartData.setHasLabels(true).setValueLabelTextSize(12);

        //Create transparent centre with label
        pieChartData.setHasCenterCircle(true).setCenterText1("Leisure")
                .setCenterText1FontSize(16)
                .setCenterText1Color(Color.parseColor("#FFFFFF"));

        //Set pie chart
        pieChartView.setPieChartData(pieChartData);
    }
}
