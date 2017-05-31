package com.very.wow.www.powiadamiator;

import android.content.Context;
import android.os.Environment;
import android.text.format.DateUtils;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by WOW on 14.05.2017.
 */

public class Przedmioty {
    ArrayList<Przedmiot> lista = new ArrayList<>();

    public static final Przedmioty I = new Przedmioty();
    public Przedmioty()
    {
        GetSubjects();
    }
    public ArrayList<Termin> GetScheduleForDay(Date data, DzienTygodnia dzien)
    {
        ArrayList<Termin> terminy = new ArrayList<>();

        GetSubjects();
        ArrayList<Przedmiot> przedmiotyTegoDnia = new ArrayList<>();

        przedmiotyTegoDnia.addAll(SelectForDay(dzien));
        terminy.addAll(przedmiotToTermin(data,przedmiotyTegoDnia));
        terminy.addAll(AddNotifications(data));
        return terminy;
    }

    private Collection<? extends Termin> przedmiotToTermin(Date data,ArrayList<Przedmiot> przedmiotyTegoDnia) {
        ArrayList<Termin> terminy = new ArrayList<>();

        for(Przedmiot p: przedmiotyTegoDnia)
        {
            terminy.add(new Termin(new Date(data.getYear()-1900,data.getMonth(),data.getDay(),p.Godzina.getHours(),p.Godzina.getMinutes(),0),p.Nazwa," "));
        }

        return terminy;
    }

    private Collection<? extends Termin> AddNotifications(Date data) {
        ArrayList<Termin> t = new ArrayList<>();
        Calendar cdata = Calendar.getInstance();
        cdata.setTime(data);

            Calendar ct = Calendar.getInstance();

            for(Termin ter: Terminy.I.lista) {
                ct.setTime(ter.Data);

                boolean sameDay = ct.get(Calendar.DAY_OF_WEEK) == cdata.get(Calendar.DAY_OF_WEEK) &&
                                cdata.get(Calendar.DAY_OF_MONTH) == ct.get(Calendar.DAY_OF_MONTH);
                if(sameDay)
                {
                        t.add(new Termin(ter.Data, ter.Nazwa, ter.Komentarz));
                }
            }

        return t;
    }

    private Collection<? extends Przedmiot> SelectForDay(DzienTygodnia dzien) {

        ArrayList<Przedmiot> selected = new ArrayList<>();

        for (Przedmiot x : lista)  {
            if(x.dzienTygodnia==dzien)
                selected.add(x);
        }
        return selected;
    }


    public static DzienTygodnia GetWeekDayFromDate(Date day) {
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return DzienTygodnia.Niedziela;
            case 2:
                return DzienTygodnia.Poniedzialek;
            case 3:
                return DzienTygodnia.Wtorek;
            case 4:
                return DzienTygodnia.Sroda;
            case 5:
                return DzienTygodnia.Czwartek;
            case 6:
                return DzienTygodnia.Piatek;
            case 7:
                return DzienTygodnia.Sobota;
        }
        return DzienTygodnia.Wtorek;

    }
    void AddSubject(String nazwa, DzienTygodnia dzienTygodnia, Date godzina)
    {
        lista.add(new Przedmiot(godzina, dzienTygodnia, nazwa));
       SaveChanges();

    }

    private void SaveChanges() {
        // Get the directory for the user's public pictures directory.
        final File path = new File(Environment.getExternalStorageDirectory(),"folder");

        final File file = new File(path, "config.txt");

        try
        {
            if(!file.getParentFile().mkdirs())
            {
                if(file.getParentFile().isDirectory()) {
                    boolean lel = file.canWrite();
                }
            }
            if(file.exists())
                file.delete();
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            for(Przedmiot x : lista)
                myOutWriter.write(x.ToCsv());

            myOutWriter.close();

            fOut.flush();
            fOut.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    void GetSubjects() {
        // Get the directory for the user's public pictures directory.
        final File path = new File(Environment.getExternalStorageDirectory(),"folder");

        // Make sure the path directory exists.
        if (!path.exists()) {
            // Make it, if it doesn't exit
            path.mkdirs();
        }

        final File file = new File(path, "config.txt");


        if (file.exists()) {
            try {
                lista.clear();
                BufferedReader br = new BufferedReader(new FileReader(file));
                try {
                    String line = br.readLine();

                    while (line != null) {
                        if(line.length()>3)
                        lista.add(Przedmiot.FromCSV(line));
                        line = br.readLine();
                    }

                } catch (IOException e) {

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void RemoveSubject(int pi) {
        lista.remove(pi);
        SaveChanges();

    }


    public static Calendar GetNextDayOfWeek(DzienTygodnia dt) {
        Calendar c = Calendar.getInstance();

            if(dt== DzienTygodnia.Niedziela)
                while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                c.add(Calendar.DATE, 1);
            }
        if(dt== DzienTygodnia.Poniedzialek)
            while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                c.add(Calendar.DATE, 1);
            }
        if(dt== DzienTygodnia.Wtorek)
            while (c.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                c.add(Calendar.DATE, 1);
            }
        if(dt== DzienTygodnia.Sroda)
            while (c.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                c.add(Calendar.DATE, 1);
            }
        if(dt== DzienTygodnia.Czwartek)
            while (c.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                c.add(Calendar.DATE, 1);
            }
        if(dt== DzienTygodnia.Piatek)
            while (c.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                c.add(Calendar.DATE, 1);
            }
        if(dt== DzienTygodnia.Sobota)
            while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                c.add(Calendar.DATE, 1);
            }

               return c;

    }
}
