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
    boolean fileDelete = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
        final ImageButton delete = findViewById(R.id.ib_delete);
        final ImageView backBtn = findViewById(R.id.iv_fileListBack);
        final ListView li = findViewById(R.id.lv_Files);
        String[] array = fileList();
        ArrayList<String> lst = new ArrayList<>(Arrays.asList(array));
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.listviewstyle, R.id.textView2, lst);

        li.setAdapter(adapter);
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);

                if (fileDelete) {

                    File file = new File(getFilesDir() + selected);
                    deleteFile(selected);

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
                    Intent openFile = new Intent(getApplicationContext(), BudgetHome.class);
                    openFile.putExtra("fileName", selected);
                    Toast toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                    toast.show();
                    startActivity(openFile);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fileDelete) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Delete Mode: Off", Toast.LENGTH_SHORT);
                    toast.show();
                    fileDelete = false;
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Delete Mode: On", Toast.LENGTH_SHORT);
                    toast.show();
                    fileDelete = true;
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveBackIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(moveBackIntent);
            }
        });
    }
}