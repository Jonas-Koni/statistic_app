package com.example.statistik_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class EnterPlacement extends AppCompatDialogFragment {
    private int mPosition;
    private ArrayList<FolderItem> mFolderList;
    private ArrayList mPlayerList;
    private ArrayList Platzierung;
    private ArrayList PlatzierungPlayers;
    private int SelectedPlacement;
    private int SelectedPlayer;
    public EnterPlacement(int position, ArrayList<FolderItem> FolderList){
        mPosition = position;
        mFolderList = FolderList;
        mPlayerList = mFolderList.get(mPosition).getmPlayerList();
        Platzierung = new ArrayList();
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

                    }
                });

        final Spinner EpSpinner = view.findViewById(R.id.SpPlayerList);
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mPlayerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EpSpinner.setAdapter(adapter);
        EpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectedPlayer = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                EpSpinner.setSelection(0);
            }
        });

        for(int i = 0; i < mPlayerList.size(); i++){
            Platzierung.add(i+1);
        }

        Spinner EpPlatzierung = view.findViewById(R.id.SpPlacementList);
        final ArrayAdapter adapter1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, Platzierung);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EpPlatzierung.setAdapter(adapter1);
        EpPlatzierung.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectedPlacement = position;
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
                System.out.println(SelectedPlacement + ". " + SelectedPlayer);
                Platzierung.remove(SelectedPlacement);
                mPlayerList.remove(SelectedPlayer);
                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();
            }
        });

        return builder.create();



    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_placement);
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext())


        Intent intent = getIntent();
        //int mPosition = intent.getIntExtra(enter_statistic.EXTRA_NUMBER, 0);
        ArrayList<FolderItem> mFolderList = intent.getParcelableExtra("Folder Item");


        Spinner EpSpinner = findViewById(R.id.SpPlayerList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.PlayerList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EpSpinner.setAdapter(adapter);
        EpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {




        /*Spinner mSpinner = (Spinner) view.findViewById(R.id.SpGameType);
        GamesAdapter mAdapter = new GamesAdapter(getActivity(), mGamesList);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setEnabled(true);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {*/



}
