package com.example.statistik_v2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.statistik_v2.FolderListPackage.FolderListAdapter;
import com.example.statistik_v2.FolderListPackage.RoomFolderViewModel;
import com.example.statistik_v2.FolderListPackage.RoomFolders;
import com.example.statistik_v2.FolderListPackage.dialogAddFolder;
import com.example.statistik_v2.PlayerListPackage.PlayerList;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements dialogAddFolder.dialogAddFolderListener {
    private RoomFolderViewModel folderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //createFolderList();
        RecyclerView mRecyclerView = findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        FolderListAdapter folderListAdapter = new FolderListAdapter();
        mRecyclerView.setAdapter(folderListAdapter);

        folderListAdapter.setOnItemClickListener(new FolderListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RoomFolders folders) {
                openAddEditFolder(false, folders);
            }
        });


        folderViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RoomFolderViewModel.class);
        folderViewModel.getAllFolders().observe(this, new Observer<List<RoomFolders>>() {
            @Override
            public void onChanged(List<RoomFolders> roomFolders) {
                folderListAdapter.setFolders(roomFolders);
            }
        });


        FloatingActionButton buttonInsert = findViewById(R.id.button_add_folder);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                insertFolder();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dropdown_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ItemSpieler:
                open_PlayerList();
        }
        return super.onOptionsItemSelected(item);
    }

    public void open_EditFolder(int position, ArrayList<FolderItem> mFolderList) {
        dialogEditFolder dialog2 = new dialogEditFolder(position, mFolderList);
        dialog2.show(getSupportFragmentManager(), "edit_folder");
    }

    public void open_PlayerList() {
        Intent intent = new Intent(this, PlayerList.class);
        startActivity(intent);
    }



    public void insertFolder() {
        RoomFolders NewFolder = new RoomFolders(R.drawable.ordner_empty, null, null, null);
        openAddEditFolder(true, NewFolder);
    }

    public void openAddEditFolder(boolean isNewFolder, RoomFolders folders){
        dialogAddFolder dialogAddFolder = new dialogAddFolder(isNewFolder, folders);
        dialogAddFolder.show(getSupportFragmentManager(), "add folder dialog");
    }
    @Override
    public void applyFolder(RoomFolders folders, boolean delete, boolean isNewPlayer) {
        if(delete && !isNewPlayer){
            folderViewModel.delete(folders);
            return;
        }
        if(isNewPlayer && !delete) {
            folderViewModel.insert(folders);
            return;
        }
        folderViewModel.update(folders);

    }
}
