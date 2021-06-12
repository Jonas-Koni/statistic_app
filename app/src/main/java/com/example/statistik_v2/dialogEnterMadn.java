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

public class dialogEnterMadn extends AppCompatDialogFragment {
    private ArrayList mPlayerList;
    private int SelectedPlayerSpinner;
    private int mPosition;
    private ArrayList<FolderItem> mFolderList;
    private ArrayList<informationGame> mInformationGamesList;
    private ArrayList mAnzahlGeworfen = new ArrayList<Serializable>();
    private ArrayList mAnzahlWuerfe = new ArrayList<Serializable>();

    public dialogEnterMadn(int position, ArrayList FolderList, ArrayList<informationGame> informationGamesList){
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
                        for(int i = 0; i < mPlayerList.size(); i++){
                            if(!mInformationGamesList.get(0).isInteger(mAnzahlGeworfen.get(i).toString()) || !mInformationGamesList.get(0).isInteger(mAnzahlWuerfe.get(i).toString())){
                                Toast errorToast = Toast.makeText(getActivity(), "Keine gültige Eingabe bei Person: "+mPlayerList.get(i), Toast.LENGTH_SHORT);
                                errorToast.show();
                                dismiss();
                            }
                        }
                        mInformationGamesList.get(0).setMadnWuerfe(mAnzahlWuerfe);
                        mInformationGamesList.get(0).setMadnGeworfen(mAnzahlGeworfen);
                    }
                });

        final EditText EditTextWuerfe = view.findViewById(R.id.EditTextWuerfe);
        final EditText EditTextGeworfen = view.findViewById(R.id.EditTextGeworfen);

        final Spinner spinner = view.findViewById(R.id.SpinnerPlayerList);
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mPlayerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList stringEditText = new ArrayList();
                ArrayList stringSavedValues = new ArrayList();
                stringEditText.add(EditTextGeworfen.getText().toString());
                stringEditText.add(EditTextWuerfe.getText().toString());
                stringSavedValues.add(mAnzahlGeworfen.get(SelectedPlayerSpinner).toString());
                stringSavedValues.add(mAnzahlWuerfe.get(SelectedPlayerSpinner).toString());

                SelectedPlayerSpinner =  mInformationGamesList.get(0).changeSelection(stringEditText, stringSavedValues, SelectedPlayerSpinner, position, getContext(), spinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.setSelection(0);
            }
        });

        mAnzahlGeworfen = mInformationGamesList.get(0).setupArrayListSavedValues(mPlayerList.size(), mInformationGamesList.get(0).getMadnGeworfen());
        mAnzahlWuerfe = mInformationGamesList.get(0).setupArrayListSavedValues(mPlayerList.size(), mInformationGamesList.get(0).getMadnWuerfe());

        Button ConfirmPlayerStats = view.findViewById(R.id.ButtonConfirmPlayerStatsMadn);
        ConfirmPlayerStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInformationGamesList.get(0).setValuesSuccessful(mAnzahlGeworfen, SelectedPlayerSpinner, getContext(), EditTextGeworfen, mPlayerList.size()) &&
                        mInformationGamesList.get(0).setValuesSuccessful(mAnzahlWuerfe, SelectedPlayerSpinner, getContext(), EditTextWuerfe, mPlayerList.size())){
                    EditTextGeworfen.setText(null);
                    EditTextWuerfe.setText(null);
                }
            }
        });


        return builder.create();
    }
}
