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

public class dialogEnterKniffel extends AppCompatDialogFragment {

    private int SelectedPlayerSpinner;
    private ArrayList<FolderItem> mFolderList;
    private ArrayList mPlayerList;
    private ArrayList mKniffelGesamtsumme01 = new ArrayList<Serializable>();
    private ArrayList mKniffelGesamtsumme02 = new ArrayList<Serializable>();
    private int mPosition;
    private int mGamePosition;
    private ArrayList<informationGame> mInformationGamesList;


    public dialogEnterKniffel(ArrayList<FolderItem> FolderList, int position, ArrayList<informationGame> informationGamesList, int gamePosition){
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
        View view = inflater.inflate(R.layout.layout_enter_kniffel, null);

        final EditText EditTextGesamtsumme01 = view.findViewById(R.id.EditTextGesamtSumme01);
        final EditText EditTextGesamtsumme02 = view.findViewById(R.id.EditTextGesamtSumme02);

        final Spinner spinner = view.findViewById(R.id.SpinnerPlayerList);
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mPlayerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList stringEditText = new ArrayList();
                ArrayList stringSavedValues = new ArrayList();
                stringEditText.add(EditTextGesamtsumme01.getText().toString());
                stringEditText.add(EditTextGesamtsumme02.getText().toString());
                stringSavedValues.add(mKniffelGesamtsumme01.get(SelectedPlayerSpinner).toString());
                stringSavedValues.add(mKniffelGesamtsumme02.get(SelectedPlayerSpinner).toString());

                SelectedPlayerSpinner =  mInformationGamesList.get(0).changeSelection(stringEditText, stringSavedValues, SelectedPlayerSpinner, position, getContext(), spinner, SelectedPlayerSpinner);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {spinner.setSelection(0);}
        });

        mKniffelGesamtsumme01 = mInformationGamesList.get(mGamePosition).setupArrayListSavedValues(mPlayerList.size(), mInformationGamesList.get(mGamePosition).getKniffelGesamtsumme01());
        mKniffelGesamtsumme02 = mInformationGamesList.get(mGamePosition).setupArrayListSavedValues(mPlayerList.size(), mInformationGamesList.get(mGamePosition).getKniffelGesamtsumme02());

        Button ConfirmPlayerStats = view.findViewById(R.id.ButtonConfirmPlayerStats);
        ConfirmPlayerStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*mKniffelGesamtsumme01.set(SelectedPlayerSpinner,Gesamtsumme(EditTextGesamtsumme01.getText().toString()));
                mKniffelGesamtsumme02.set(SelectedPlayerSpinner,Gesamtsumme(EditTextGesamtsumme02.getText().toString()));
                if(     mPlayerList.size() < 1 ||
                        mKniffelGesamtsumme01.get(SelectedPlayerSpinner).toString() == "-1" ||
                        mKniffelGesamtsumme02.get(SelectedPlayerSpinner).toString() == "-1")
                {return;}
                mPlayerList.remove(SelectedPlayerSpinner);
                adapter.notifyDataSetChanged();*/

                if(mInformationGamesList.get(mGamePosition).setValuesSuccessful(mKniffelGesamtsumme01, SelectedPlayerSpinner, getContext(), EditTextGesamtsumme01, mPlayerList.size()) &&
                        mInformationGamesList.get(mGamePosition).setValuesSuccessful(mKniffelGesamtsumme02, SelectedPlayerSpinner, getContext(), EditTextGesamtsumme02, mPlayerList.size())){
                    mPlayerList.remove(SelectedPlayerSpinner);
                    adapter.notifyDataSetChanged();
                }


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
                        for(int i = 0; i < mPlayerList.size(); i++){
                            if(!isInteger(mKniffelGesamtsumme01.get(i).toString())){
                                Toast errorToast = Toast.makeText(getActivity(), "Keine gültige Eingabe bei Person: "+mPlayerList.get(i), Toast.LENGTH_SHORT);
                                errorToast.show();
                                dismiss();
                            }
                        }
                        mInformationGamesList.get(mGamePosition).setKniffelGesamtsumme01(mKniffelGesamtsumme01);
                        mInformationGamesList.get(mGamePosition).setKniffelGesamtsumme02(mKniffelGesamtsumme02);
                        //EditTextGesamtsumme01.setText(mInformationGamesList.get(mGamePosition).getKniffelGesamtsumme01().get(mGamePosition));
                    }
                });

        return builder.create();
    }

    boolean isInteger (String StringIsInteger) {
        try {
            int Checkint = Integer.parseInt(StringIsInteger);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

/*if(SelectedPlayerSpinner != position){
        String StringEditTextGesamtsumme01 = EditTextGesamtsumme01.getText().toString();
        String StringKniffelGesamtsumme01 = mKniffelGesamtsumme01.get(SelectedPlayerSpinner).toString();
        String StringEditTextGesamtsumme02 = EditTextGesamtsumme02.getText().toString();
        String StringKniffelGesamtsumme02 = mKniffelGesamtsumme02.get(SelectedPlayerSpinner).toString();
        if(StringEditTextGesamtsumme01 != StringKniffelGesamtsumme01 && StringEditTextGesamtsumme01.length()>0){
        Toast toast = Toast.makeText(getContext(), "Ungespeicherte Daten", Toast.LENGTH_SHORT);
        toast.show();
        } else if(StringEditTextGesamtsumme02 != StringKniffelGesamtsumme02 && StringEditTextGesamtsumme02.length()>0){
        Toast toast = Toast.makeText(getContext(), "Ungespeicherte Daten", Toast.LENGTH_SHORT);
        toast.show();
        } else {
        SelectedPlayerSpinner = position;
        }
        spinner.setSelection(SelectedPlayerSpinner);
        } */


