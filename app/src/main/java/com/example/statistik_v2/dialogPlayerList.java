package com.example.statistik_v2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dialogPlayerList extends AppCompatDialogFragment {
    ArrayList<PlayerListItem> mPlayerListItem;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.layout_player_list, null);

        builder.setView(view)
                .setTitle("Spieler Liste")
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //saveData()
                    }
                });

        buildRecyclerview((view));

        Button AddPlayer = view.findViewById(R.id.BtnAddPlayer);

        AddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPlayer();
            }
        });

        return builder.create();
    }

    public void buildRecyclerview(View view){
        mPlayerListItem = new ArrayList<>();
        mPlayerListItem.add(new PlayerListItem(-1, "Matthias"));
        mPlayerListItem.add(new PlayerListItem(-1, "Matthias Königsmann"));
        mPlayerListItem.add(new PlayerListItem(-1, "FLO_KO"));
        mPlayerListItem.add(new PlayerListItem(-1, "421 4 12412 "));


        RecyclerView mRecyclerView = view.findViewById(R.id.recycleViewPlayerList);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        PlayerListAdapter mAdapter = new PlayerListAdapter(mPlayerListItem);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //mAdapter.setOnItemClickListener(dialogEnterStatistic.this);
    }
    public void openAddPlayer(){
        //dialogAddPlayer dialogAddPlayer = new dialogAddPlayer();
        //dialogAddPlayer.show(getFragmentManager(), "add player dialog");
    }


}
