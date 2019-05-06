package com.example.pyf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class DeleteEntries extends AppCompatActivity {

    //Global Variables
    String details = "";
    String clearedDetails = "";
    String workspace = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set layout
        setContentView(R.layout.activity_delete_entries);

        //Instantiate objects for method use
        loadFile loadMethod = new loadFile();
        final SaveFile saveMethod = new SaveFile();

        //Retrieve extra info from intent
        final String fileName = getIntent().getStringExtra("fileName");

        //Create listview variable
        final ListView li = findViewById(R.id.lv_DeleteList);

        //Retrieve string from file using loadMethod method
        details = loadMethod.FileLoader(fileName, null , getApplicationContext());

        //Replace string/regex in string with replacement value
        details = details.replaceAll("/", " ");

        //Split string in to arrays for array variable
        String[] array = details.split("\n");

        //Create arraylist
        final ArrayList<String> lst = new ArrayList<>(Arrays.asList(array));

        //Create adapter for listview
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.listviewstyle, R.id.textView2, lst);

        //Set adapter to listview
        li.setAdapter(adapter);

        //Set on item click listener (Triggers when item clicked)
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Item at clicked position casted as string and placed in String variable
                String selected = (String) parent.getItemAtPosition(position);

                //log tag and result for debugging
                Log.d("PositionReturn", selected);

                //Delete item at position from arraylist
                lst.remove(position);

                //Force listview/adapter to refresh
                adapter.notifyDataSetChanged();

                //For loop to clean details, prevents leaving without ensuring item is deleted
                for(int i = 0; i < lst.size(); i++) {
                    workspace = lst.get(i);
                    workspace = workspace.replaceAll("\\s+", "/");
                    clearedDetails += workspace + "\n";
                    Log.d("clearedDetails", clearedDetails);
                }

                //saveMethod method using specific string and file name to save data
                saveMethod.saveFile(getApplicationContext(), fileName, null, clearedDetails,
                        false);

                //Clearing variable for further work
                clearedDetails = "";

                //Toast for app responsiveness
                Toast toast = Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //Set element to variable
        ImageView deleteBack = findViewById(R.id.iv_fileListBack);

        //Set on click listener
        deleteBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Instantiate intent
                Intent backIntent = new Intent(getApplicationContext(), BudgetHome.class);

                //Add extra information to intent
                backIntent.putExtra("fileName", fileName);

                //Execute intent
                startActivity(backIntent);
            }
        });
    }
}

