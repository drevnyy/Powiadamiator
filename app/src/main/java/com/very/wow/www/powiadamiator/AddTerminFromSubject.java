package com.very.wow.www.powiadamiator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTerminFromSubject extends AppCompatActivity {
public static String name;
public static String hour;
    public static DzienTygodnia dt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_termin_from_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView date = (TextView) findViewById(R.id.textView50);
        date.setText(name);

        Calendar date1 = Przedmioty.GetNextDayOfWeek(dt);



        EditText et = (EditText) findViewById(R.id.editText10);
        EditText et2 = (EditText) findViewById(R.id.editText11);
        EditText et4 = (EditText) findViewById(R.id.editText12);
        et2.setText(hour.split(":")[0]);
        et4.setText(hour.split(":")[1]);


        et.setText(new SimpleDateFormat("yyyy-MM-dd").format(date1.getTime()));

        Button fab = (Button) findViewById(R.id.button11);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText) findViewById(R.id.editText10);
                EditText et5 = (EditText) findViewById(R.id.editText15);
                EditText et2 = (EditText) findViewById(R.id.editText11);
                EditText et4 = (EditText) findViewById(R.id.editText12);
                String[] datep =  et.getText().toString().split("-");
                Terminy.I.AddTermin(name,et5.getText().toString(),new Date(Integer.parseInt(datep[0])-1900,Integer.parseInt(datep[1]),Integer.parseInt(datep[2]),
                        Integer.parseInt(et2.getText().toString()),Integer.parseInt(et4.getText().toString())));
                finish();
            }
        });

        Button fab2 = (Button) findViewById(R.id.button21);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
