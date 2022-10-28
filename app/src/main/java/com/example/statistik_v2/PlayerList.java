package com.example.statistik_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class PlayerList extends AppCompatActivity {
    ArrayList<PlayerListItem> mPlayerListItem;
    PlayerListAdapter mAdapter;
    private informationGameDbHelper dbHelper = new informationGameDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        buildRecyclerview();
        createPlayerList();

        Intent intent = getIntent();
        //mPlayerListItem = intent.getParcelableExtra(MainActivity.EXTRA_TEXT);

        Button BtnAddPlayer = findViewById(R.id.BtnAddPlayer);
        BtnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mPlayerListItem.size();
                insertPlayer(position);
            }
        });
    }
    public void createPlayerList(){
        mPlayerListItem = new ArrayList<>();
        mPlayerListItem = dbHelper.getPlayerInformation();
        dbHelper.close();


    }

    public void buildRecyclerview(){
        RecyclerView mRecyclerView = findViewById(R.id.rvPlayerList);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PlayerListAdapter(mPlayerListItem);
        mAdapter.setOnItemClickListener(new PlayerListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    public void insertPlayer(int position) {
        mPlayerListItem.add(position, new PlayerListItem());
        mAdapter.notifyItemInserted(position);
        openAddPlayer(position, mPlayerListItem, mAdapter, true);
    }

    public void openAddPlayer(int position, ArrayList<PlayerListItem> playerListItems, PlayerListAdapter adapter, boolean newPlayer){
        dialogAddPlayer dialogAddPlayer = new dialogAddPlayer(position, playerListItems, adapter, newPlayer);
        dialogAddPlayer.show(getSupportFragmentManager(), "add player dialog");
    }

}