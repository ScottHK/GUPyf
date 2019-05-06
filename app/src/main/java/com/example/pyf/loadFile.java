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

        //Variable
        String fileString = "";

        //Instantiate fis object
        FileInputStream fis = null;

        //Error handling
        try {

            //Opens/targets file for reading
            fis = context.openFileInput(fileName);

        } catch (FileNotFoundException e) {

            //Print error to stack trace
            e.printStackTrace();
        }

        //Instantiate objects
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();

        //Instantiate String
        String line = null;

        //While loop to construct string for manipulating and returning
        while (true) {

            //Error Handling
            try {
                if (!((line = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {

                //Print error to stack trace
                e.printStackTrace();
            }

            //Append stringbuilder with line and \n
            sb.append(line + "\n");
        }

        //Control structure so it knows where the string is going
        if(textview != null) {
            textview.append(sb.toString());
        } else if(fileString != null) {
            fileString = sb.toString();
            return fileString;
        }

        //Needs to return something
        return line;
    }
}
