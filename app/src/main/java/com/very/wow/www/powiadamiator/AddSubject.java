package com.very.wow.www.powiadamiator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;


public class AddSubject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);


        Button fab = (Button) findViewById(R.id.button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText) findViewById(R.id.editText);
                EditText et2 = (EditText) findViewById(R.id.editText2);
                EditText et3 = (EditText) findViewById(R.id.editText3);
                EditText et4 = (EditText) findViewById(R.id.editText4);
               Przedmioty.I.AddSubject(et.getText().toString(),Przedmiot.EnumFromString(et2.getText().toString()),new  Date(2000,01,01,Integer.parseInt(et3.getText().toString()),Integer.parseInt(et4.getText().toString()),0));
                finish();
            }
        });

        Button fab2 = (Button) findViewById(R.id.button2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
