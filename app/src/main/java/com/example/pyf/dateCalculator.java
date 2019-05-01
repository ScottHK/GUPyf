package com.example.pyf;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.String;
import java.util.Locale;


public class dateCalculator {

    int returnableValue;

    public int calculateDays(String d1, String d2) {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        Date startDate = null;
        Date endDate = null;

        try {
            startDate = format.parse(d1);
            endDate = format.parse(d2);

            DateTime dt1 = new DateTime(startDate);
            DateTime dt2 = new DateTime(endDate);

            returnableValue = Days.daysBetween(dt1, dt2).getDays();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnableValue;
    }

    public String calculateDate(int days, String d1) {

        String oldDate = d1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(oldDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, days);
        String newDate = sdf.format(c.getTime());
        return newDate;
    }
}
