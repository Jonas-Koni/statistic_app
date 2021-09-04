package com.example.statistik_v2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.io.Serializable;
import java.util.ArrayList;

public class dialogEnterKniffel extends AppCompatDialogFragment {

    private int SelectedPlayerSpinner;
    private ArrayList<FolderItem> mFolderList;
    private ArrayList mPlayerList;
    private ArrayList mKniffelTotalUpperSection = new ArrayList<Serializable>();
    private ArrayList mKniffelTotalDownerSection = new ArrayList<Serializable>();
    private int mPosition;
    private int mGamePosition;
    private ArrayList<informationGame> mInformationGamesList;
    private ArrayList<informationGamePlayerStats> InformationGamePlayerStatsArray;


    public dialogEnterKniffel(ArrayList<FolderItem> FolderList, int position, ArrayList<informationGame> informationGamesList, int gamePosition, ArrayList<informationGamePlayerStats> informationGamePlayerStatsArray){
        mFolderList = FolderList;
        mPosition = position;
        mPlayerList =  (ArrayList) mFolderList.get(mPosition).getmPlayerList().clone();
        mInformationGamesList = informationGamesList;
        mGamePosition = gamePosition;
        InformationGamePlayerStatsArray = informationGamePlayerStatsArray;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_enter_kniffel, null);

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
                        for(int players = 0; players < InformationGamePlayerStatsArray.size(); players ++){
                            if(!mInformationGamesList.get(InformationGamePlayerStatsArray.get(0).getGameId()).isInteger(mKniffelTotalUpperSection.get(players).toString())
                                    || !mInformationGamesList.get(InformationGamePlayerStatsArray.get(0).getGameId()).isInteger(mKniffelTotalDownerSection.get(players).toString())){
                                Toast errorToast = Toast.makeText(getActivity(), "Keine gültige Eingabe bei Person: "+mPlayerList.get(players), Toast.LENGTH_SHORT);
                                errorToast.show();
                                dismiss();
                            }
                        }
                        for(int players = 0; players < InformationGamePlayerStatsArray.size(); players ++) {
                            InformationGamePlayerStatsArray.get(players).setKniffelTotalUpperSection(mKniffelTotalUpperSection.indexOf(players));
                            InformationGamePlayerStatsArray.get(players).setKniffelTotalDownerSection(mKniffelTotalUpperSection.indexOf(players));
                        }

                    }
                });

        mKniffelTotalUpperSection = new ArrayList();
        mKniffelTotalDownerSection = new ArrayList();
        for (int players = 0; players < InformationGamePlayerStatsArray.size(); players ++) {
            mKniffelTotalUpperSection.add(null);
            mKniffelTotalDownerSection.add(null);
        }


        final EditText EditTextTotalUpperSection = view.findViewById(R.id.EditTextGesamtSumme01);
        final EditText EditTextTotalDownerSection = view.findViewById(R.id.EditTextGesamtSumme02);

        final Spinner spinner = view.findViewById(R.id.SpinnerPlayerList);
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mPlayerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //set new Selection
                mKniffelTotalUpperSection.set(position, EditTextTotalUpperSection.getText().toString());
                mKniffelTotalDownerSection.set(position, EditTextTotalDownerSection.getText().toString());

                EditTextTotalUpperSection.setText(null);
                EditTextTotalDownerSection.setText(null);

                SelectedPlayerSpinner = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {spinner.setSelection(0);}
        });

        return builder.create();
    }
}

