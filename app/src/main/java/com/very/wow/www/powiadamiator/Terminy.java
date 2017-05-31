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
 * Created by WOW on 17.05.2017.
 */

public class Terminy {
    public ArrayList<Termin> lista = new ArrayList<>();
    public static final Terminy I = new Terminy();
    public static String FormatTerminy(ArrayList<Termin> prrrrrrrr) {
        String retVar = "";
        for(Termin x : prrrrrrrr)
        {
            retVar+=x.Nazwa+"   "+ new SimpleDateFormat("HH:mm").format(x.Data)+"\r\n   "+(x.Komentarz==null? "Brak powiadomień":x.Komentarz)+"\r\n";
        }
        return retVar;
    }

    public Terminy()
    {
        Gettetrminy();
    }

    void AddTermin(String nazwa, String komentarz, Date godzina)
    {
        lista.add(new Termin(godzina, nazwa, komentarz));
        SaveChanges();

    }

    private void SaveChanges() {
        // Get the directory for the user's public pictures directory.

        final File path = new File(Environment.getExternalStorageDirectory(),"folder");

        // Make sure the path directory exists.
        if(!path.exists())
        {
            // Make it, if it doesn't exit
            path.mkdirs();
        }

        final File file = new File(path, "terminy.txt");



        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            for(Termin x : lista)
                myOutWriter.write(x.ToCsv());

            myOutWriter.close();

            fOut.flush();
            fOut.close();
        }
        catch (IOException e)
        {

        }
    }


    void Gettetrminy() {
        // Get the directory for the user's public pictures directory.

        final File path = new File(Environment.getExternalStorageDirectory(),"folder");
        // Make sure the path directory exists.
        if (!path.exists()) {
            // Make it, if it doesn't exit
            path.mkdirs();
        }

        final File file = new File(path, "terminy.txt");


        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                try {
                    lista.clear();
                    String line = br.readLine();

                    while (line != null) {
                        lista.add(FromCSV(line));
                        line = br.readLine();
                    }

                } catch (IOException e) {

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private static Termin FromCSV(String line) {

        Termin p = new Termin();
        SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String[] params = line.split(";");
        try {
            p.Data = parser.parse(params[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        p.Komentarz=(params[2]);
        p.Nazwa=params[1];
        return p;

    }

    public void RemoveSubject(int pi) {
        lista.remove(pi);
        SaveChanges();

    }
}
