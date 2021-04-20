package com.example.statistik_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class EnterPlacement extends AppCompatDialogFragment {
    private int mPosition;
    private ArrayList<FolderItem> mFolderList;
    private ArrayList mPlayerList;
    private ArrayList Platzierung;
    private ArrayList PlatzierungPlayers;
    private int SelectedPlacementSpinner;
    private int SelectedPlayerSpinner;
    private ArrayList<informationGame> mInformationGamesList;

    public EnterPlacement(int position, ArrayList<FolderItem> FolderList, ArrayList<informationGame> informationGamesList){
        mPosition = position;
        mFolderList = FolderList;
        mPlayerList = (ArrayList) mFolderList.get(mPosition).getmPlayerList().clone();
        Platzierung = new ArrayList();
        PlatzierungPlayers = new ArrayList();
        mInformationGamesList = informationGamesList;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_enter_placement, null);

        builder.setView(view)
                .setTitle("Platzierung")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(mPlayerList.size()<=0){
                            mInformationGamesList.get(mPosition).setRankingPlayers(PlatzierungPlayers);
                        }

                    }
                });

        final Spinner EpSpinner = view.findViewById(R.id.SpPlayerList);
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mPlayerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EpSpinner.setAdapter(adapter);
        EpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectedPlayerSpinner = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                EpSpinner.setSelection(0);
            }
        });

        for(int i = 0; i < mPlayerList.size(); i++){
            Platzierung.add(i+1);
            PlatzierungPlayers.add("not defined yet");
        }

        Spinner EpPlatzierung = view.findViewById(R.id.SpPlacementList);
        final ArrayAdapter adapter1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, Platzierung);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EpPlatzierung.setAdapter(adapter1);
        EpPlatzierung.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectedPlacementSpinner = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                EpSpinner.setSelection(0);
            }
        });


        Button ConfirmPlacement = view.findViewById(R.id.ButtonConfirm);
        ConfirmPlacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPlayerList.size()<=0){return;}
                PlatzierungPlayers.set(SelectedPlacementSpinner, SelectedPlayerSpinner);
                Platzierung.remove(SelectedPlacementSpinner);
                mPlayerList.remove(SelectedPlayerSpinner);
                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();

            }
        });

        return builder.create();



    }



}
