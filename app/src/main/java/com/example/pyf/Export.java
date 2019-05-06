package com.example.pyf;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Export {

    public void exportCSV(String fileName, String exportable, Context context) throws IOException {

        //Instantiate file object. Get's filepath for external storage
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), fileName);

        //printer for tag and variable for debugging
        Log.d("string", exportable);

        //Instantiate objects
        FileWriter fw = new FileWriter(file);
        CSVWriter writer = new CSVWriter(fw);

        //String for assigning column headings in csv (For spreadsheets)
        String[] header = { "Category", "Description", "Amount" };

        //CSVWriter command. Rights line then starts new line
        writer.writeNext(header);

        //Replace / with a comma for CSV spacing requirements
        String changed = exportable.replace("/", ",");

        //Returns variable in logcat for debugging
        Log.d("string", changed);

        //Split string in to array by new line (\n)
        String[] parts = changed.split("\n");

        //Returns variable in logcat for debugging
        Log.d("string", String.valueOf(parts));

        //For loop for constructing strings for CSV file
        for(int x = 0; x < parts.length; x++) {
            Log.d("csv Splitter", parts[x]);
            String[] partsLine = parts[x].split(",");
            Log.d("csv Splitter", partsLine[0]);
            writer.writeNext(partsLine);
        }

        //Closes off writer
        writer.close();
    }



}
