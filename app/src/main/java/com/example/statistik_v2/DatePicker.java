package com.example.statistik_v2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class DatePicker extends DialogFragment {
    private int mPosition;
    private ArrayList<FolderItem> mFolderList;
    public DatePicker(ArrayList<FolderItem> FolderList, int position){
        mFolderList = FolderList;
        mPosition = position;
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

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity() , year, month, day);
    }
}
