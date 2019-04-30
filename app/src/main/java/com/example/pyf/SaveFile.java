package com.example.pyf;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFile {

    public void saveFile(Context context, String fileName, @Nullable TextView string, @Nullable String string2, boolean append) {
        File file = new File(context.getFilesDir(), fileName);

        //details = new String[arrayCounter];
        //Creating a filewriter object
        try {
            FileWriter writer = new FileWriter(file, append);

            if(string != null) {
                writer.write(System.getProperty("line.separator"));
                writer.write(string.getText().toString());
            } else if(string2 != null){
                writer.write(string2);
            } else {
                Toast toast = Toast.makeText(context, "File not saved: Nothing to add!",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
            writer.flush();
            writer.close();

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
