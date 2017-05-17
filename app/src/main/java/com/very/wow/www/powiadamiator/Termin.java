package com.very.wow.www.powiadamiator;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by WOW on 14.05.2017.
 */

public class Termin {
    public String Nazwa;
    Date Data;
    String Komentarz;

    public Termin(Date godzina, String nazwa,String komentarz)
    {
        Data= godzina;
        Nazwa = nazwa;
        this.Komentarz=komentarz;
    }
    public Termin()
    {

    }
    public String ToCsv() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Data)+";"+Nazwa+";"+Komentarz;
    }

}
