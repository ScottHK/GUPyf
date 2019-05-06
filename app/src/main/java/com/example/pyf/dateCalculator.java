package com.example.pyf;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.String;



public class dateCalculator {

    //Global Variable
    int returnableValue;

    public int calculateDays(String d1, String d2) {

        //Set formatter's format
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        //Instantiate date variables
        Date startDate = null;
        Date endDate = null;

        //Error Handling
        try {

            //Variables
            startDate = format.parse(d1);
            endDate = format.parse(d2);

            //Instantiate Joda Time objects
            DateTime dt1 = new DateTime(startDate);
            DateTime dt2 = new DateTime(endDate);

            //Capture value to be returned
            returnableValue = Days.daysBetween(dt1, dt2).getDays();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Return number of days between
        return returnableValue;
    }

    public String calculateDate(int days, String d1) {

        //Variable
        String oldDate = d1;
        Calendar c = Calendar.getInstance();

        //Set formatter's format
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        //Error Handling
        try {

            //Set the formatted time
            c.setTime(sdf.parse(oldDate));
        } catch (ParseException e) {

            //Print error to stack trace
            e.printStackTrace();
        }

        //Add date to variable c
        c.add(Calendar.DAY_OF_MONTH, days);

        //Format string to be returned
        String newDate = sdf.format(c.getTime());

        //Return string
        return newDate;
    }
}
