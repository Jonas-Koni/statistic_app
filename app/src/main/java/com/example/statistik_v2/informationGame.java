package com.example.statistik_v2;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class informationGame implements DatePickerDialog.OnDateSetListener {
    private int FolderIndex;
    private int GameId;
    private String Date;

    public informationGame(){
        FolderIndex = 0;
    }


    String getDate() {
        return Date;
    }
    int getFolderIndex() {return FolderIndex;}

    void setFolderIndex(int folderIndex) {FolderIndex = folderIndex;}
    void setGameId(int gameId) {GameId = gameId;}
    void setDate(String date) {
        Date = date;
    }


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
        for(int numberPlayer = 1; numberPlayer <= mPlayerListSize; numberPlayer++) {
            if(ListStringSavedValues != null && isInteger(ListStringSavedValues.get(numberPlayer).toString())) {
                SetupArrayList.add(ListStringSavedValues.get(numberPlayer).toString());
            } else {
                SetupArrayList.add(null);
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
        Date = c.getTime().toString();
        //date.get(mPosition).changeDate(c.getTime());

        //String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
    }

    informationGameDbHelper informationgamedbhelper;




    public static long insertNewGame(SQLiteDatabase database, ArrayList<informationGame> informationGameArrayList, ArrayList<FolderItem> folderItemArrayList) {
        int GameID = informationGameArrayList.size() - 1;

        ContentValues values = new ContentValues();
        values.put(informationGameContractClass.StatisticTable.COLUMN_DATE, informationGameArrayList.get(GameID).getDate().toString());
        values.put(informationGameContractClass.StatisticTable.COLUMN_GAMETYPE, folderItemArrayList.get(GameID).getGameType());
        long newRowID = database.insert(
                informationGameContractClass.StatisticTable.DATABASE_NAME, null, values);
        return newRowID;
    }



    public static String getSingleGame(SQLiteDatabase database, int userId) {
        String userName = null;
        final String SQL_GET_SINGLE_GAME =
                "SELECT " + informationGameContractClass.StatisticTable.COLUMN_DATE +
                        " FROM " + informationGameContractClass.StatisticTable.DATABASE_NAME +
                        " WHERE " + userId +
                        ");";

        return null;
    }
}


