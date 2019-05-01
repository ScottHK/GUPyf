package com.example.pyf;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Export {

    String TAG = "PermissionChecks";

    public void exportCSV(String fileName, String exportable, Context context) throws IOException {

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), fileName);

        Log.d("string", exportable);
        FileWriter fw = new FileWriter(file);
        CSVWriter writer = new CSVWriter(fw);

        String[] header = { "Category", "Description", "Amount" };
        writer.writeNext(header);

        String changed = exportable.replace("/", ",");

        Log.d("string", changed);
        String[] parts = changed.split("\n");
        Log.d("string", String.valueOf(parts));
        for(int x = 0; x < parts.length; x++) {
            Log.d("csv Splitter", parts[x]);
            String[] partsLine = parts[x].split(",");
            Log.d("csv Splitter", partsLine[0]);
            writer.writeNext(partsLine);
        }
        writer.close();
    }



}
