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

    String details = "";
    String clearedDetails = "";
    String workspace = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_entries);
        loadFile loadMethod = new loadFile();
        final SaveFile saveMethod = new SaveFile();
        final String fileName = getIntent().getStringExtra("fileName");
        final ListView li = findViewById(R.id.lv_DeleteList);
        details = loadMethod.FileLoader(fileName, null , getApplicationContext());
        details = details.replaceAll("/", " ");
        String[] array = details.split("\n");
        final ArrayList<String> lst = new ArrayList<>(Arrays.asList(array));
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.listviewstyle, R.id.textView2, lst);

        li.setAdapter(adapter);
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                Log.d("PositionReturn", selected);
                lst.remove(position);
                adapter.notifyDataSetChanged();
                for(int i = 0; i < lst.size(); i++) {
                    workspace = lst.get(i);
                    workspace = workspace.replaceAll("\\s+", "/");
                    clearedDetails += workspace + "\n";
                    Log.d("clearedDetails", clearedDetails);
                }
                saveMethod.saveFile(getApplicationContext(), fileName, null, clearedDetails,
                        false);
                clearedDetails = "";
                Toast toast = Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        ImageView deleteBack = findViewById(R.id.iv_fileListBack);

        deleteBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(), BudgetHome.class);

                backIntent.putExtra("fileName", fileName);

                startActivity(backIntent);
            }
        });
    }
}

