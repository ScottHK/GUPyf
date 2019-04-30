package com.example.pyf;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class loadFile {

    public String FileLoader(String fileName, @Nullable TextView textview, Context context) {
        String fileString = "";
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while (true) {
            try {
                if (!((line = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(line);
        }
        if(textview != null) {
            textview.append(sb.toString());
        } else if(fileString != null) {
            fileString = sb.toString();
            return fileString;
        }
        return line;
    }
}
