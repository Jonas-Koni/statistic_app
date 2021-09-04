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

public class dialogEnterMadn extends AppCompatDialogFragment {
    private ArrayList mPlayerList;
    private int SelectedPlayerSpinner;
    private int mPosition;
    private ArrayList<FolderItem> mFolderList;
    private ArrayList<informationGame> mInformationGamesList;
    private ArrayList mAnzahlGeworfen = new ArrayList<Serializable>();
    private ArrayList mAnzahlWuerfe = new ArrayList<Serializable>();
    private ArrayList<informationGamePlayerStats> InformationGamePlayerStatsArray;

    public dialogEnterMadn(int position, ArrayList FolderList, ArrayList<informationGame> informationGamesList, ArrayList<informationGamePlayerStats> informationGamePlayerStatsArray){
        mFolderList = FolderList;
        mPosition = position;
        mPlayerList = (ArrayList) mFolderList.get(mPosition).getmPlayerList().clone();
        mInformationGamesList = informationGamesList;
        InformationGamePlayerStatsArray = informationGamePlayerStatsArray;

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_enter_madn, null);

        builder.setView(view)
                .setTitle("Mensch ärgere dich nicht")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int players = 0; players < InformationGamePlayerStatsArray.size(); players ++){
                            if(!mInformationGamesList.get(InformationGamePlayerStatsArray.get(0).getGameId()).isInteger(mAnzahlGeworfen.get(players).toString())
                                    || !mInformationGamesList.get(InformationGamePlayerStatsArray.get(0).getGameId()).isInteger(mAnzahlWuerfe.get(players).toString())){
                                Toast errorToast = Toast.makeText(getActivity(), "Keine gültige Eingabe bei Person: "+mPlayerList.get(players), Toast.LENGTH_SHORT);
                                errorToast.show();
                                dismiss();
                            }
                        }
                        for(int players = 0; players < InformationGamePlayerStatsArray.size(); players ++) {
                            InformationGamePlayerStatsArray.get(players).setMadnThrew(mAnzahlWuerfe.indexOf(players));
                            InformationGamePlayerStatsArray.get(players).setMadnThrown(mAnzahlGeworfen.indexOf(players));
                        }
                    }
                });

        mAnzahlWuerfe = new ArrayList();
        mAnzahlGeworfen = new ArrayList();
        for (int players = 0; players < InformationGamePlayerStatsArray.size(); players ++) {
            mAnzahlWuerfe.add(null);
            mAnzahlGeworfen.add(null);
        }

        final EditText EditTextWuerfe = view.findViewById(R.id.EditTextWuerfe);
        final EditText EditTextGeworfen = view.findViewById(R.id.EditTextGeworfen);

        final Spinner spinner = view.findViewById(R.id.SpinnerPlayerList);
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mPlayerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAnzahlWuerfe.set(position, EditTextWuerfe.getText().toString());
                mAnzahlGeworfen.set(position, EditTextGeworfen.getText().toString());

                EditTextWuerfe.setText(null);
                EditTextGeworfen.setText(null);

                SelectedPlayerSpinner = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.setSelection(0);
            }
        });

        return builder.create();
    }
}
