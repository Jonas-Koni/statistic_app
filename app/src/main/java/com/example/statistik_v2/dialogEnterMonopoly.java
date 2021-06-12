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

    public dialogEnterMonopoly(ArrayList<FolderItem> FolderList, int position, ArrayList<informationGame> informationGamesList, int gamePosition){
        mFolderList = FolderList;
        mPosition = position;
        mPlayerList =  (ArrayList) mFolderList.get(mPosition).getmPlayerList().clone();
        mInformationGamesList = informationGamesList;
        mGamePosition = gamePosition;
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
                        for(int i = 0; i < mPlayerList.size(); i++){
                            if(!mInformationGamesList.get(0).isInteger(mMonopolyGeldBar.get(i).toString()) || !mInformationGamesList.get(0).isInteger(mMonopolyGeldHaus.get(i).toString()) || !mInformationGamesList.get(0).isInteger(mMonopolyGeldGrundstueck.get(i).toString())){
                                Toast errorToast = Toast.makeText(getActivity(), "Keine gültige Eingabe bei Person: "+mPlayerList.get(i), Toast.LENGTH_SHORT);
                                errorToast.show();
                                dismiss();
                            }
                        }
                        mInformationGamesList.get(mGamePosition).setMonopolyGeldBar(mMonopolyGeldBar);
                        mInformationGamesList.get(mGamePosition).setMonopolyGeldHaus(mMonopolyGeldHaus);
                        mInformationGamesList.get(mGamePosition).setMonopolyGeldGrundstueck(mMonopolyGeldGrundstueck);
                    }
                });


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
                ArrayList stringEditText = new ArrayList();
                ArrayList stringSavedValues = new ArrayList();
                stringEditText.add(EditTextGeldHaus.getText().toString());
                stringEditText.add(EditTextGeldBar.getText().toString());
                stringEditText.add(EditTextGeldGrundstueck.getText().toString());
                stringSavedValues.add(mMonopolyGeldBar.get(SelectedPlayerSpinner).toString());
                stringSavedValues.add(mMonopolyGeldHaus.get(SelectedPlayerSpinner).toString());
                stringSavedValues.add(mMonopolyGeldGrundstueck.get(SelectedPlayerSpinner).toString());

                SelectedPlayerSpinner =  mInformationGamesList.get(0).changeSelection(stringEditText, stringSavedValues, SelectedPlayerSpinner, position, getContext(), spinner);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {spinner.setSelection(0);}
        });

        mMonopolyGeldBar = mInformationGamesList.get(mGamePosition).setupArrayListSavedValues(mPlayerList.size(), mInformationGamesList.get(mGamePosition).getMonopolyGeldBar());
        mMonopolyGeldHaus = mInformationGamesList.get(mGamePosition).setupArrayListSavedValues(mPlayerList.size(), mInformationGamesList.get(mGamePosition).getMonopolyGeldHaus());
        mMonopolyGeldGrundstueck = mInformationGamesList.get(mGamePosition).setupArrayListSavedValues(mPlayerList.size(), mInformationGamesList.get(mGamePosition).getMonopolyGeldGrundstueck());

        Button ConfirmPlayerStats = view.findViewById(R.id.ButtonConfirmPlayerStatsMonopoly);
        ConfirmPlayerStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInformationGamesList.get(mGamePosition).setValuesSuccessful(mMonopolyGeldBar, SelectedPlayerSpinner, getContext(), EditTextGeldHaus, mPlayerList.size()) &&
                        mInformationGamesList.get(mGamePosition).setValuesSuccessful(mMonopolyGeldHaus, SelectedPlayerSpinner, getContext(), EditTextGeldBar, mPlayerList.size()) &&
                        mInformationGamesList.get(mGamePosition).setValuesSuccessful(mMonopolyGeldGrundstueck, SelectedPlayerSpinner, getContext(), EditTextGeldGrundstueck, mPlayerList.size())){
                    EditTextGeldBar.setText(null);
                    EditTextGeldGrundstueck.setText(null);
                    EditTextGeldHaus.setText(null);
                }
            }
        });
        return builder.create();
    }
}
