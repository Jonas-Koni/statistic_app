package com.example.statistik_v2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class dialogEnterKniffel extends AppCompatDialogFragment {

    private String Gesamtsumme01String;
    private String Gesamtsumme02String;
    private int Gesamtsumme01Int;
    private int Gesamtsumme02Int;
    private ArrayList<FolderItem> mFolderList;
    private ArrayList mPlayerList;
    private ArrayList mKniffelGesamtsumme01;
    private ArrayList mKniffelGesamtsumme02;
    private int mPosition;
    private ArrayList<informationGame> mInformationGamesList;


    public dialogEnterKniffel(ArrayList<FolderItem> FolderList, int position, ArrayList<informationGame> informationGamesList){
        mFolderList = FolderList;
        mPosition = position;
        mPlayerList = mFolderList.get(mPosition).getmPlayerList();
        mInformationGamesList = informationGamesList;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_enter_kniffel, null);

        Spinner spinner = view.findViewById(R.id.SpinnerPlayerList);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mPlayerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final EditText EditTextGesamtsumme01 = view.findViewById(R.id.EditTextGesamtSumme01);
        final EditText EditTextGesamtsumme02 = view.findViewById(R.id.EditTextGesamtSumme02);
        Button ConfirmPlayerStats = view.findViewById(R.id.ButtonConfirmPlayerStats);
        ConfirmPlayerStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mKniffelGesamtsumme01.get()
            }
        });

        builder.setView(view)
                .setTitle("Kniffel")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Gesamtsumme01String = EditTextGesamtsumme01.getText().toString();
                        Gesamtsumme02String = EditTextGesamtsumme02.getText().toString();
                        Gesamtsumme01Int = Gesamtsumme(Gesamtsumme01String);
                        Gesamtsumme02Int = Gesamtsumme(Gesamtsumme02String);

                    }
                });

        return builder.create();
    }
    int Gesamtsumme (String ConvertToInt) {
        try {
            int CheckInt = Integer.parseInt(ConvertToInt);
        } catch (NumberFormatException nfe) {
            Toast errorToast = Toast.makeText(getActivity(), "Keine gültige Eingabe, bitte verwenden Sie nur Zahlen", Toast.LENGTH_SHORT);
            errorToast.show();
            return -1;
        }
        return Integer.parseInt(ConvertToInt);

    }
}


