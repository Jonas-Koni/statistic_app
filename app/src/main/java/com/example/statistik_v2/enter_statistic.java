package com.example.statistik_v2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class enter_statistic extends AppCompatDialogFragment implements DataAdapter.OnItemClickListener {
    private ArrayList<DataItem> mDataList;

    private int mGameType;

    private String mTitle;
    //private DialogDataListener listener;
    private int mPosition;
    private ArrayList<FolderItem> mFolderList;

    public static final String EXTRA_TEXT = "com.example.statistik_v2.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.statistik_v2.EXTRA_NUMBER";
    public static final String EXTRA_ARRAY = "com.example.statistik_v2.EXTRA_ARRAY";

    //private RecyclerView.Adapter mAdapter;
    public enter_statistic(int GameType, ArrayList Playerlist, String Title, int ImageResource, int position, ArrayList<FolderItem> FolderList) {
        mTitle = Title;
        mGameType = GameType;
        mPosition = position;
        mFolderList = FolderList;


    }



    @SuppressLint("ClickableViewAccessibility")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_enter_statistic, null);

        builder.setView(view)
                .setTitle(mTitle)
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //listener.applyTexts4(mPlayerList, mPosition, -1);
                    }
                });

        createDataList();
        buildRecyclerview(view);

        return builder.create();
    }

    public void createDataList(){
        mDataList = new ArrayList<>();

        mDataList.add(new DataItem(R.drawable.calender, "Kalender"));//0: Platzierung; 1: Kniffel; 2: Mädn; 3: Monopoly; 4: Wikinger Schach; 5: Zeit und Anzahl

        switch (mGameType){
            case 0:
                mDataList.add(new DataItem(R.drawable.platzierung, "Platzierung"));
                break;
            case 1:
                mDataList.add(new DataItem(R.drawable.platzierung, "Platzierung"));
                mDataList.add(new DataItem(R.drawable.kniffel, "Kniffel"));
                break;
            case 2:
                mDataList.add(new DataItem(R.drawable.platzierung, "Platzierung"));
                mDataList.add(new DataItem(R.drawable.figure_2, "Mensch ärger dich nicht"));
                break;
            case 3:
                mDataList.add(new DataItem(R.drawable.platzierung, "Platzierung"));
                mDataList.add(new DataItem(R.drawable.monopoly, "Monopoly"));
                break;
            case 4:
                mDataList.add(new DataItem(R.drawable.platzierung, "Platzierung"));
                mDataList.add(new DataItem(R.drawable.wikinger_schach, "Wikinger Schach"));
                break;
            case 5:
                mDataList.add(new DataItem(R.drawable.stoppuhr, "Zeit und Anzahl"));
                break;

        }
    }
    public void buildRecyclerview(View view){
        RecyclerView mRecyclerView = view.findViewById(R.id.recycleViewData);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        DataAdapter mAdapter = new DataAdapter(mDataList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(enter_statistic.this);
    }


    @Override
    public void onItemClick(int position) { //0: Date; 1: Platzierung; 2: Kniffel; 3: Mädn; 4: Monopoly; 5: Wikinger Schach; 6: Zeit und Anzahl
       switch (mDataList.get(position).getTitle()) {
           case "Kalender":
               DialogFragment datePicker = new com.example.statistik_v2.DatePicker(mFolderList, mPosition);
               datePicker.show(getFragmentManager(),"date picker");
               break;
           case "Platzierung":
               open_EnterPlacement();
                break;
           case "Kniffel":
               open_EnterKniffel();
               break;
           case "Mensch ärger dich nicht":
               open_EnterMadn();
               break;
           default:
               throw new IllegalStateException("Unexpected value: " + position);

       }

    }
    public void open_EnterMadn() {
        EnterMadn enterMadn = new EnterMadn(mPosition, mFolderList);
        enterMadn.show(getFragmentManager(), "enterMadn");
    }

    public void open_EnterKniffel(){
        EnterKniffel enterKniffel = new EnterKniffel(mFolderList,  mPosition);
        enterKniffel.show(getFragmentManager(), "enterKniffel");
    }

    public void open_EnterPlacement() {
        EnterPlacement enterPlacement = new EnterPlacement(mPosition, mFolderList);
        enterPlacement.show(getFragmentManager(),"EnterPlacementDialog");
    }

}
