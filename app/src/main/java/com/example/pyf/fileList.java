package com.example.pyf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class fileList extends AppCompatActivity {
    TextView transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
        ListView li = findViewById(R.id.lv_Files);
        String[] array = fileList();
        ArrayList<String> lst = new ArrayList<>(Arrays.asList(array));
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, lst);

        li.setAdapter(adapter);
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);

                /*ArrayList<String> records = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(selected))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] values = line.split("/");
                        records.addAll(Arrays.asList(values));
                    }
                }catch (FileNotFoundException e) {
                    Toast toast = Toast.makeText(getApplicationContext(), "File name " +
                            selected + " does not exist!", Toast.LENGTH_SHORT);
                    toast.show();
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                Intent openFile = new Intent(getApplicationContext(), BudgetHome.class);
                openFile.putExtra("fileName", selected);
                Toast toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                toast.show();
                startActivity(openFile);
            }
        });
    }
}