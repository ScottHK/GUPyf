package com.example.pyf;

import android.util.Log;

public class RollOverCleaner {

    public String Cleaner(String details) {

        //Variables
        String cleanedDetails = "";

        //Split string in to array
        String[] detailsArr = details.split("\n");

        //Get array length as int
        int detailsLength = detailsArr.length;

        //For loop to construct cleaned string
        for(int i = 0; i <= detailsLength -1; i++) {
            String[] stringSplitter = detailsArr[i].split("/");

            if (stringSplitter[0].contains("Income")) {
                cleanedDetails += StringConstruction(stringSplitter);
                Log.d("CleanerSplitter", cleanedDetails);
            } else if (stringSplitter[0].contains("Bills")) {
                cleanedDetails += StringConstruction(stringSplitter);
            } else if (stringSplitter[0].contains("Taxes")) {
                cleanedDetails += StringConstruction(stringSplitter);
            } else if (stringSplitter[0].contains("Debts")) {
                cleanedDetails += StringConstruction(stringSplitter);
            } else if (stringSplitter[0].contains("Subscription")) {
                cleanedDetails += StringConstruction(stringSplitter);
            }
        }
        return cleanedDetails;
    }

    public String StringConstruction(String[] arr) {

        String string = "";

        //Formats string to desired format
        string += arr[0] + "/";
        string += arr[1] + "/";
        string += arr[2] + "\n";

        return string;
        }
    }

