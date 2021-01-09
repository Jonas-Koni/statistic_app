package com.example.statistik_v2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
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

public class enter_statistic extends AppCompatDialogFragment implements DataAdapter.OnItemClickListener, DatePickerDialog.OnDateSetListener {
    private ArrayList<DataItem> mDataList;
    private RecyclerView mRecyclerView;
    private DataAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int mGameType;
    private ArrayList mPlayerList = new ArrayList();

    private String mTitle;
    private int mImageResource;
    private DialogDataListener listener;
    private int mPosition;

    //private RecyclerView.Adapter mAdapter;
    public enter_statistic(int GameType, ArrayList Playerlist, String Title, int ImageResource, int position) {
        mTitle = Title;
        mGameType = GameType;
        mPlayerList = Playerlist;
        mImageResource = ImageResource;
        mPosition = position;


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
                        listener.applyTexts4(mPlayerList, mPosition);
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
        mRecyclerView = view.findViewById(R.id.recycleViewData);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new DataAdapter(mDataList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(enter_statistic.this);
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DialogDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogDataListener");
        }
    }

    @Override
    public void onItemClick(int position) { //0: Date; 1: Platzierung; 2: Kniffel; 3: Mädn; 4: Monopoly; 5: Wikinger Schach; 6: Zeit und Anzahl
        switch (position){
            case 0:
                DialogFragment datePicker = new com.example.statistik_v2.DatePicker();
                datePicker.show(getFragmentManager(), "date picker");
                break;
            case 1:
                if(mGameType <= 4){
                    //1
                } else {
                    //6
                }
            case 2:
                    //mGameType+1

        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
    }

    public interface DialogDataListener {
        void applyTexts4(ArrayList PlayerList, int position);
    }
}
