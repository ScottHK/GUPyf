package com.example.pyf;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class budgetCreation extends AppCompatActivity {
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_creation);
        myDialog = new Dialog(this);
        Button incomeDetails = (Button) findViewById(R.id.editBtn);
        incomeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtclose;
                Button submitBtnPop;
                myDialog.setContentView(R.layout.custompopup);
                txtclose = (TextView) myDialog.findViewById(R.id.exit);
                submitBtnPop = (Button) myDialog.findViewById(R.id.submitBtnPop);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        Button billsDetails = (Button) findViewById(R.id.newBudgetBtn);
        billsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtclose;
                Button submitBtnPop;
                myDialog.setContentView(R.layout.custompopup);
                txtclose = (TextView) myDialog.findViewById(R.id.exit);
                submitBtnPop = (Button) myDialog.findViewById(R.id.submitBtnPop);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        Button taxesDetails = (Button) findViewById(R.id.exportArchiveBtn);
        taxesDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtclose;
                Button submitBtnPop;
                myDialog.setContentView(R.layout.custompopup);
                txtclose = (TextView) myDialog.findViewById(R.id.exit);
                submitBtnPop = (Button) myDialog.findViewById(R.id.submitBtnPop);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

        Button debtsDetails = (Button) findViewById(R.id.quickAddBtn);
        debtsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtclose;
                Button submitBtnPop;
                myDialog.setContentView(R.layout.custompopup);
                txtclose = (TextView) myDialog.findViewById(R.id.exit);
                submitBtnPop = (Button) myDialog.findViewById(R.id.submitBtnPop);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });

    }

}
