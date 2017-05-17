package com.very.wow.www.powiadamiator;

import android.text.style.TtsSpan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Przedmiot {
public DzienTygodnia dzienTygodnia;
    public String Nazwa;
    public Date Godzina;

    public Przedmiot(Date date, DzienTygodnia wtorek, String nazwa) {
        dzienTygodnia=wtorek;
        Godzina=date;
        Nazwa = nazwa;
    }
    public Przedmiot() {
    }
    public String ToCsv() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Godzina)+";"+dzienTygodnia+";"+Nazwa;
    }

    public static Przedmiot FromCSV(String line) {
        Przedmiot p = new Przedmiot();
        SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String[] params = line.split(";");
        try {
            p.Godzina = parser.parse(params[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        p.dzienTygodnia=EnumFromString(params[1]);
        p.Nazwa=params[2];
    return p;
    }

    public static DzienTygodnia EnumFromString(String param) {
       return DzienTygodnia.valueOf(param);
    }
}
