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

public class dialogEnterMonopoly extends AppCompatDialogFragment  {
    private int SelectedPlayerSpinner;
    private ArrayList<FolderItem> mFolderList;
    private ArrayList mPlayerList;
    private ArrayList mMonopolyGeldBar = new ArrayList<Serializable>();
    private ArrayList mMonopolyGeldHaus = new ArrayList<Serializable>();
    private ArrayList mMonopolyGeldGrundstueck = new ArrayList<Serializable>();
    private int mPosition;
    private int mGamePosition;
    private ArrayList<informationGame> mInformationGamesList;
    private ArrayList<informationGamePlayerStats> InformationGamePlayerStatsArray;

    public dialogEnterMonopoly(ArrayList<FolderItem> FolderList, int position, ArrayList<informationGame> informationGamesList, int gamePosition, ArrayList<informationGamePlayerStats> informationGamePlayerStatsArray){
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
        View view = inflater.inflate(R.layout.activity_dialog_enter_monopoly, null);

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
                            if(!mInformationGamesList.get(InformationGamePlayerStatsArray.get(0).getGameId()).isInteger(mMonopolyGeldBar.get(players).toString())
                                    || !mInformationGamesList.get(InformationGamePlayerStatsArray.get(0).getGameId()).isInteger(mMonopolyGeldHaus.get(players).toString())
                                    || !mInformationGamesList.get(InformationGamePlayerStatsArray.get(0).getGameId()).isInteger(mMonopolyGeldGrundstueck.get(players).toString())){
                                Toast errorToast = Toast.makeText(getActivity(), "Keine gültige Eingabe bei Person: "+mPlayerList.get(players), Toast.LENGTH_SHORT);
                                errorToast.show();
                                dismiss();
                            }
                        }

                        for(int players = 0; players < InformationGamePlayerStatsArray.size(); players ++) {
                            InformationGamePlayerStatsArray.get(players).setMonopolyCash(mMonopolyGeldBar.indexOf(players));
                            InformationGamePlayerStatsArray.get(players).setMonopolyValueProperty(mMonopolyGeldGrundstueck.indexOf(players));
                            InformationGamePlayerStatsArray.get(players).setMonopolyValueHouse(mMonopolyGeldHaus.indexOf(players));
                        }
                    }
                });

        mMonopolyGeldBar = new ArrayList();
        mMonopolyGeldGrundstueck = new ArrayList();
        mMonopolyGeldHaus = new ArrayList();
        for (int players = 0; players < InformationGamePlayerStatsArray.size(); players ++) {
            mMonopolyGeldBar.add(null);
            mMonopolyGeldGrundstueck.add(null);
            mMonopolyGeldHaus.add(null);
        }


        final EditText EditTextGeldHaus = view.findViewById(R.id.EditTextGeldHaus);
        final EditText EditTextGeldBar = view.findViewById(R.id.EditTextGeldBar);
        final EditText EditTextGeldGrundstueck = view.findViewById(R.id.EditTextGeldGrundstueck);

        final Spinner spinner = view.findViewById(R.id.SpinnerPlayerList);
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mPlayerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mMonopolyGeldBar.set(position, EditTextGeldBar.getText().toString());
                mMonopolyGeldGrundstueck.set(position, EditTextGeldGrundstueck.getText().toString());
                mMonopolyGeldHaus.set(position, EditTextGeldHaus.getText().toString());

                EditTextGeldBar.setText(null);
                EditTextGeldGrundstueck.setText(null);
                EditTextGeldHaus.setText(null);

                SelectedPlayerSpinner = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {spinner.setSelection(0);}
        });
        return builder.create();
    }
}
