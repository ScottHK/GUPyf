package com.example.pyf;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BudgetHome extends AppCompatActivity {

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgethome);

        Button editbtn = findViewById(R.id.editBtn);
        Button exportbtn = findViewById(R.id.exportArchiveBtn);
        Button newbtn = findViewById(R.id.newFileBtn);
        Button quickbtn = findViewById(R.id.quickAddBtn);
        myDialog = new Dialog(this);

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent budgetcreationIntent = new Intent(getApplicationContext(), budgetCreation.class);

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
