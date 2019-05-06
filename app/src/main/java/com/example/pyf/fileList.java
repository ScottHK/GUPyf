package com.example.pyf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;

public class fileList extends AppCompatActivity {

    //Global Variables
    boolean fileDelete = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Sets layout
        setContentView(R.layout.activity_file_list);

        //Set elements to variables
        final ImageButton delete = findViewById(R.id.ib_delete);
        final ImageView backBtn = findViewById(R.id.iv_fileListBack);
        final ListView li = findViewById(R.id.lv_Files);

        //Variable holding array of file names
        String[] array = fileList();

        //Create arraylist
        ArrayList<String> lst = new ArrayList<>(Arrays.asList(array));

        //Create adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.listviewstyle, R.id.textView2, lst);

        //Sets listview adapter
        li.setAdapter(adapter);

        //Sets on click listener
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Sets clicked items name to string variable
                String selected = (String) parent.getItemAtPosition(position);

                //Checks if file delete is toggled true or false
                if (fileDelete) {

                    //Instantiates file object for deleting
                    File file = new File(getFilesDir() + selected);

                    //Deletes specified file
                    deleteFile(selected);

                    //For app responsiveness, displays toast when file deleted or not
                    if (!file.exists()) {
                        adapter.remove(adapter.getItem(position));
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "File deleted successfully", Toast.LENGTH_SHORT);
                        toast.show();
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Failed to delete the file", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {

                    //Instantiate Intent
                    Intent openFile = new Intent(getApplicationContext(), BudgetHome.class);

                    //Places extra information in intent
                    openFile.putExtra("fileName", selected);

                    //Executes intent
                    startActivity(openFile);
                }
            }
        });

        //Set on click listener
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //If statement structure for handling delete mode toggle
                if(fileDelete) {

                    //App responsiveness
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Delete Mode: Off", Toast.LENGTH_SHORT);
                    toast.show();

                    //Toggle False
                    fileDelete = false;
                } else {

                    //App responsiveness
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Delete Mode: On", Toast.LENGTH_SHORT);
                    toast.show();

                    //Toggle True
                    fileDelete = true;
                }
            }
        });

        //Set on click listener
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Instantiates intent
                Intent moveBackIntent = new Intent(getApplicationContext(), MainActivity.class);

                //Executes intent
                startActivity(moveBackIntent);
            }
        });
    }
}