package com.very.wow.www.powiadamiator;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class SubjectDetails extends AppCompatActivity {


    public static Przedmiot p;
    public static int pi;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three


        final SubjectDetails sd = this;
        TextView t1 = (TextView) findViewById(R.id.textDetails);
        t1.setText("(brak)");
        TextView t2 = (TextView) findViewById(R.id.textHour);
        t2.setText(new SimpleDateFormat("HH:mm").format(p.Godzina));
        TextView t3 = (TextView) findViewById(R.id.textName);
        t3.setText(p.Nazwa);
        TextView t4 = (TextView) findViewById(R.id.TextDay);
        t4.setText(p.dzienTygodnia.toString());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Przedmioty.I.RemoveSubject(pi);
                sd.onBackPressed();
            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intt = new Intent(sd, AddTerminFromSubject.class);

                TextView et5 = (TextView) findViewById(R.id.textName);
                TextView et6 = (TextView) findViewById(R.id.textHour);
                AddTerminFromSubject.dt =  SubjectDetails.p.dzienTygodnia;
                AddTerminFromSubject.name = et5.getText().toString();
                AddTerminFromSubject.hour = et6.getText().toString();
                startActivity(intt);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subject_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

}
