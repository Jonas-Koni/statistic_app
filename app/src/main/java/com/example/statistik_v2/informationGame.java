package com.example.statistik_v2;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class informationGame implements DatePickerDialog.OnDateSetListener {
    private int FolderIndex;
    private Date Date;
    private ArrayList RankingPlayers;
    private ArrayList KniffelGesamtsumme01;
    private ArrayList KniffelGesamtsumme02;
    private ArrayList MadnWuerfe;
    private ArrayList MadnGeworfen;

    public informationGame(int folderIndex){
        FolderIndex = folderIndex;
    }



    public Date getDate() {
        return Date;
    }
    public void setDate(Date date) {
        Date = date;
    }
    public void setRankingPlayers(ArrayList rankingPlayers) {
        RankingPlayers = rankingPlayers;
    }
    public void setKniffelGesamtsumme01(ArrayList kniffelGesamtsumme01) {
        KniffelGesamtsumme01 = kniffelGesamtsumme01;
    }
    public void setKniffelGesamtsumme02(ArrayList kniffelGesamtsumme02) {
        KniffelGesamtsumme02 = kniffelGesamtsumme02;
    }
    public void setMadnWuerfe(ArrayList madnWuerfe) {
        MadnWuerfe = madnWuerfe;
    }
    public void setMadnGeworfen(ArrayList madnGeworfen) {
        MadnGeworfen = madnGeworfen;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        Date = c.getTime();
        //date.get(mPosition).changeDate(c.getTime());

        //String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
    }
}


