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
    PlayerListAdapter mAdapter;

    public dialogPlayerList(ArrayList<PlayerListItem> playerListItems){
        mPlayerListItem = playerListItems;

    }

    private informationGameDbHelper dbHelper = new informationGameDbHelper(this.getContext());

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

        createPlayerListItem();
        buildRecyclerview((view));

        Button AddPlayer = view.findViewById(R.id.BtnAddPlayer);

        AddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mPlayerListItem.size();
                insertPlayer(position);
            }
        });

        return builder.create();
    }

    public void createPlayerListItem(){
        //mPlayerListItem = new ArrayList<>();
        //mPlayerListItem = dbHelper.getPlayerInformation();
        //dbHelper.close();

        //mPlayerListItem.add(new PlayerListItem(-1, "Matthias"));
        //mPlayerListItem.add(new PlayerListItem(-1, "Matthias Königsmann"));
        //mPlayerListItem.add(new PlayerListItem(-1, "FLO_KO"));
        //mPlayerListItem.add(new PlayerListItem(-1, "ere"));*/
        //mPlayerListItem.add(new PlayerListItem(-1, "ere"));

    }


    public void buildRecyclerview(View view){
        RecyclerView mRecyclerView = view.findViewById(R.id.recycleViewPlayerList);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new PlayerListAdapter(mPlayerListItem);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new PlayerListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openAddPlayer(position, mPlayerListItem, mAdapter, false);
            }
        });


    }
    public void insertPlayer(int position) {
        mPlayerListItem.add(position, new PlayerListItem(-1, ""));
        mAdapter.notifyItemInserted(position);
        openAddPlayer(position, mPlayerListItem, mAdapter, true);
    }

    public void openAddPlayer(int position, ArrayList<PlayerListItem> playerListItems, PlayerListAdapter adapter, boolean newPlayer){
        dialogAddPlayer dialogAddPlayer = new dialogAddPlayer(position, playerListItems, adapter, newPlayer);
        dialogAddPlayer.show(getFragmentManager(), "add player dialog");
    }


}
