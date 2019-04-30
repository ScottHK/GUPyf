package com.example.pyf;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class BudgetHome extends AppCompatActivity {

    Dialog myDialog;
    TextView transaction;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgethome);

        fileName = getIntent().getStringExtra("fileName");
        transaction = findViewById(R.id.transactionHistoryTextView);
        final SaveFile saveMethod = new SaveFile();
        final loadFile loadMethod = new loadFile();

        ImageButton editbtn = findViewById(R.id.btn_edit);
        ImageButton exportbtn = findViewById(R.id.btn_archive);
        ImageButton newbtn = findViewById(R.id.btn_New);
        ImageButton quickbtn = findViewById(R.id.btn_Quick);
        myDialog = new Dialog(this);
        loadMethod.FileLoader(fileName, transaction, getApplicationContext());
        transaction.setMovementMethod(new ScrollingMovementMethod());




        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent budgetcreationIntent = new Intent(getApplicationContext(), EditBudget.class);

                budgetcreationIntent.putExtra("fileName", fileName);
                startActivity(budgetcreationIntent);
                //TODO File dump
            }
        });

        exportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView close;
                Button device;
                Button gdrive;
                myDialog.setContentView(R.layout.activity_exportarchive);
                close = myDialog.findViewById(R.id.archiveExit);
                device = myDialog.findViewById(R.id.deviceBtn);
                gdrive = myDialog.findViewById(R.id.googleDriveBtn);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();

            }
        });

        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView close;
                Button newFile;
                Button rollOver;
                myDialog.setContentView(R.layout.activity_start_new);
                close = myDialog.findViewById(R.id.startNewExit);
                newFile = myDialog.findViewById(R.id.startNewBtn);
                rollOver = myDialog.findViewById(R.id.rollOverBtn);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        quickbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView close;
                Button spend;
                Button fund;
                myDialog.setContentView(R.layout.activity_quickaddpopup);
            }
        });
    }

}
