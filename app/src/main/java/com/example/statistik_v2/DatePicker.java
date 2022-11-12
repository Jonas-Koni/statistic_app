package com.example.statistik_v2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class DatePicker extends DialogFragment {
    private int mPosition;
    private ArrayList<FolderItem> mFolderList;
    private ArrayList<informationGame> mInformationGamesList;
    private DatePickerDialog.OnDateSetListener mDatePickListener;
    public DatePicker(ArrayList<FolderItem> FolderList, int position, ArrayList<informationGame> informationGame, DatePickerDialog.OnDateSetListener datePickListener){
        mFolderList = FolderList;
        mPosition = position;
        mInformationGamesList = informationGame;
        mDatePickListener = datePickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        if(mFolderList.get(mPosition).getDate() != null) {
            calendar.setTime(mFolderList.get(mPosition).getDate());
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //Date date = new Date(year, month, day);
        //mInformationGamesList.get(mPosition).setDate(0, date);
        return  new DatePickerDialog(getActivity(), mDatePickListener, year, month, day);
        //return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }
}
