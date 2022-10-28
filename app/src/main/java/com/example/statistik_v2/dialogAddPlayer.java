package com.example.statistik_v2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class dialogAddPlayer extends AppCompatDialogFragment {
    private EditText etNewName;
    private Button BtnDeletePlayer; //nur, wenn keine Daten mit Spieler

    private int mPosition;
    private int mPlayerID;
    private ArrayList<PlayerListItem> mPlayerListItem;
    private PlayerListAdapter mAdapter;
    private boolean mNewPlayer;
    private informationGameDbHelper dbHelper = null;


    public dialogAddPlayer(int position, ArrayList<PlayerListItem> playerListItems, PlayerListAdapter Adapter, boolean newPlayer){
        mPosition = position;
        mPlayerListItem = playerListItems;
        mAdapter = Adapter;
        mNewPlayer = newPlayer;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_add_player, null);


        builder.setView(view)
                .setTitle("Spieler hinzfügen")
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(mNewPlayer) {
                            deletePlayer(mPlayerListItem.size() - 1);
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.print("Name: ");
                        changeFolderName();

                    }
                });
        mPlayerID = mPlayerListItem.get(mPosition).getmPlayerID();

        dbHelper = new informationGameDbHelper(this.getContext());

        etNewName = view.findViewById(R.id.etNewPlayername);
        BtnDeletePlayer = view.findViewById(R.id.BtnDeletePlayer);

        BtnDeletePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePlayer(mPlayerListItem.size()-1);
                dismiss();
            }
        });
        if(!mNewPlayer) {
            etNewName.setText(mPlayerListItem.get(mPosition).getName()); //StandardText soll aktueller Text sein
        }

        return builder.create();
    }


    private void deletePlayer(int index) {

        if (!mNewPlayer) {
            dbHelper.deletePlayer(mPlayerListItem.get(index).getmPlayerID(), this.getContext());
            dbHelper.close();
            System.out.println("deleted Player");

        }

        mPlayerListItem.remove(index);
        mAdapter.notifyDataSetChanged();
        mAdapter.notifyItemRemoved(index);
    }

    private void changeFolderName() {
        String Text1 = etNewName.getText().toString();

        mPlayerListItem.get(mPosition).setmName(Text1);

        int newPlayerID;

        if(mNewPlayer) {
            dbHelper.insertPlayer(Text1, -1, this.getContext());
            newPlayerID = dbHelper.getPlayerInformation().get(dbHelper.getPlayerInformation().size() - 1).getmPlayerID();
            mPlayerListItem.get(mPosition).setPlayerID(newPlayerID);
            mPlayerListItem.get(mPosition).setmImageResource(-1);

        } else {
            dbHelper.updatePlayer(-1, Text1, mPlayerID);
        }
        dbHelper.close();
        mAdapter.notifyDataSetChanged();
    }
}
