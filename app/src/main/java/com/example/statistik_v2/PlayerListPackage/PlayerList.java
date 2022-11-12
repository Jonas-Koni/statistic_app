package com.example.statistik_v2.PlayerListPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.statistik_v2.R;
import com.example.statistik_v2.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class PlayerList extends AppCompatActivity implements dialogAddPlayer.dialogAddPlayerListener{
    private RoomPlayerViewModel playerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        RecyclerView mRecyclerView = findViewById(R.id.rvPlayerList);
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);


        final PlayerListAdapter playerAdapter = new PlayerListAdapter();
        mRecyclerView.setAdapter(playerAdapter);
        playerAdapter.setOnItemClickListener(new PlayerListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RoomPlayers players) {
                openAddEditPlayer(false, players);
            }
        });

        playerViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RoomPlayerViewModel.class);
        playerViewModel.getAllPlayers().observe(this, new Observer<List<RoomPlayers>>() {
            @Override
            public void onChanged(List<RoomPlayers> roomPlayers) {
                playerAdapter.setPlayers(roomPlayers);
                //update Recyclerview
            }
        });

        Button BtnAddPlayer = findViewById(R.id.BtnAddPlayer);
        BtnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPlayer();
            }
        });
    }

    public void insertPlayer() {
        RoomPlayers NewPlayer = new RoomPlayers(-1, null);
        openAddEditPlayer(true, NewPlayer);
    }

    public void openAddEditPlayer(boolean isNewFolder, RoomPlayers player){
        dialogAddPlayer dialogAddPlayer = new dialogAddPlayer(isNewFolder, player);
        dialogAddPlayer.show(getSupportFragmentManager(), "add player dialog");
    }

    @Override
    public void applyTexts(RoomPlayers players, boolean delete, boolean isNewPlayer) {
        if(delete && !isNewPlayer){
            playerViewModel.delete(players);
            return;
        }
        if(isNewPlayer && !delete) {
            playerViewModel.insert(players);
            return;
        }
        playerViewModel.update(players);

    }
}