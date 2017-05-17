package com.very.wow.www.powiadamiator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final MainActivity ma = this;
        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Intent intt = new Intent(ma, ActivitiesInDay.class);
                ///oczy bolą jak patrzę na tego hacka ale nie chcę mi się poprawiać :D
              ActivitiesInDay.day= (new Date(year-1900,month,dayOfMonth));
                startActivity(intt);
            }
        });

        FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.fabEdit);
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intt = new Intent(ma, AllSubjectss.class);
                startActivity(intt);
            }
        });
        TextView textView = (TextView) findViewById(R.id.nowLabel);
        ArrayList<Termin> prrrrrrrr=Przedmioty.I.GetScheduleForDay(new Date(System.currentTimeMillis()),Przedmioty.GetWeekDayFromDate(new Date(System.currentTimeMillis())));
        textView.setText("Dzisiaj ("+Przedmioty.GetWeekDayFromDate(new Date(System.currentTimeMillis())).toString()+")\r\n"+Terminy.FormatTerminy(prrrrrrrr));



    }
    protected void onClick(Bundle savedInstanceState)
    {

    }


}
