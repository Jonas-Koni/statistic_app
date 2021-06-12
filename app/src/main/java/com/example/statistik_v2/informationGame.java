package com.example.statistik_v2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    private ArrayList MonopolyGeldBar;
    private ArrayList MonopolyGeldHaus;
    private ArrayList MonopolyGeldGrundstueck;

    public informationGame(int folderIndex){
        FolderIndex = folderIndex;
    }



    Date getDate() {
        return Date;
    }
    ArrayList getKniffelGesamtsumme01() {return KniffelGesamtsumme01;}
    ArrayList getKniffelGesamtsumme02() {return KniffelGesamtsumme02;}
    ArrayList getRankingPlayers() {return RankingPlayers;}
    ArrayList getMadnWuerfe() {return MadnWuerfe;}
    ArrayList getMadnGeworfen() {return MadnGeworfen;}
    ArrayList getMonopolyGeldBar() {return MonopolyGeldBar;}
    ArrayList getMonopolyGeldHaus() {return MonopolyGeldHaus;}
    ArrayList getMonopolyGeldGrundstueck() {return MonopolyGeldGrundstueck;}
    void setDate(Date date) {
        Date = date;
    }
    void setRankingPlayers(ArrayList rankingPlayers) {
        RankingPlayers = rankingPlayers;
    }
    void setKniffelGesamtsumme01(ArrayList kniffelGesamtsumme01) {
        KniffelGesamtsumme01 = kniffelGesamtsumme01;
    }
    void setKniffelGesamtsumme02(ArrayList kniffelGesamtsumme02) {
        KniffelGesamtsumme02 = kniffelGesamtsumme02;
    }
    void setMadnWuerfe(ArrayList madnWuerfe) {
        MadnWuerfe = madnWuerfe;
    }
    void setMadnGeworfen(ArrayList madnGeworfen) {
        MadnGeworfen = madnGeworfen;
    }
    void setMonopolyGeldBar(ArrayList geldBar) {MonopolyGeldBar = geldBar;}
    void setMonopolyGeldHaus(ArrayList geldHaus) {MonopolyGeldHaus = geldHaus;}
    void setMonopolyGeldGrundstueck(ArrayList geldGrundstueck) {MonopolyGeldGrundstueck = geldGrundstueck;}

    public int changeSelection(ArrayList stringEditText, ArrayList stringSavedValues, int SelectedPlayerSpinner, int position, Context context, Spinner spinner){
        if(SelectedPlayerSpinner != position) {
            for (int i = 0; i < stringEditText.size(); i ++){
                if (stringEditText.get(i).toString() != stringSavedValues.get(i).toString() && stringEditText.get(i).toString().length() > 0) {
                    Toast toast = Toast.makeText(context, "Ungespeicherte Daten", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    SelectedPlayerSpinner = position;
                }
                spinner.setSelection(SelectedPlayerSpinner);
            }
        }
        return SelectedPlayerSpinner;
    }

    public ArrayList setupArrayListSavedValues(int mPlayerListSize, ArrayList ListStringSavedValues){
        ArrayList SetupArrayList = new ArrayList();
        for(int numberPlayer = 0; numberPlayer < mPlayerListSize; numberPlayer++) {
            if(ListStringSavedValues != null && isInteger(ListStringSavedValues.get(numberPlayer).toString())) {
                SetupArrayList.add(ListStringSavedValues.get(numberPlayer).toString());
            } else {
                SetupArrayList.add("not defined yet");
            }
        }
        return SetupArrayList;
    }

    public boolean setValuesSuccessful(ArrayList newValue, int SelectedPlayerSpinner, Context context, EditText editText, int mPlayerListSize){
        if(mPlayerListSize < 1 || getInteger(editText.getText().toString(), context) == -1) {return false;}
        return true;

    }


    public boolean isInteger (String StringIsInteger) {
        try {
            int Checkint = Integer.parseInt(StringIsInteger);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    int getInteger (String ConvertToInt, Context context) {
        try {
            int CheckInt = Integer.parseInt(ConvertToInt);
        } catch (NumberFormatException nfe) {
            Toast errorToast = Toast.makeText(context, "Keine gültige Eingabe, bitte verwenden Sie nur Zahlen", Toast.LENGTH_SHORT);
            errorToast.show();
            return -1;
        }
        return Integer.parseInt(ConvertToInt);
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


