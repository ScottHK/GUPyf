package com.example.pyf;

import android.util.Log;

public class RollOverCleaner {

    public String Cleaner(String details) {

        String cleanedDetails = "";
        String[] detailsArr = details.split("\n");
        int detailsLength = detailsArr.length;
        for(int i = 0; i <= detailsLength -1; i++) {
            String[] stringSplitter = detailsArr[i].split("/");

            if (stringSplitter[0].contains("Income")) {
                cleanedDetails += StringConstruction(stringSplitter);
            } else if (stringSplitter[0].contains("Bills")) {
                cleanedDetails += StringConstruction(stringSplitter);
            } else if (stringSplitter[0].contains("Taxes")) {
                cleanedDetails += StringConstruction(stringSplitter);
            } else if (stringSplitter[0].contains("Debts")) {
                cleanedDetails += StringConstruction(stringSplitter);
            } else if (stringSplitter[0].contains("Leisure")) {
                cleanedDetails += StringConstruction(stringSplitter);
            } else if (stringSplitter[0].contains("Fund")) {
                cleanedDetails += StringConstruction(stringSplitter);
            } else if (stringSplitter[0].contains("Subscription")) {
                cleanedDetails += StringConstruction(stringSplitter);
            }
        }
        return cleanedDetails;
    }

    public String StringConstruction(String[] arr) {

        String string = "";

        string += arr[0] + "/";
        string += arr[1] + "/";
        string += arr[2] + "\n";

        return string;
        }
    }

