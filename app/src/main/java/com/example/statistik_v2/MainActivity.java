package com.example.statistik_v2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<FolderItem> mFolderList;
    private FolderAdapter mAdapter;
    private ArrayList<File> mFileList = new ArrayList<File>();

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private StatisticDataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        informationGame testMemo = new informationGame(0);
        Log.d(LOG_TAG, "Inhalt: " + testMemo.toString());

        dataSource = new StatisticDataSource(this);

        createFolderList();
        buildRecyclerView();

        Button buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                int position = mFolderList.size();
                insertItem(position);
            }
        });
    }
    public void createFolderList() {
        mFolderList = new ArrayList<>();
    }
    public void buildRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recycleView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new FolderAdapter(mFolderList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new FolderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                open_EditFolderName(position, mFolderList, mAdapter, false);
            }

            @Override
            public void onSettingsClick(int position) {
                open_EditFolder(position, mFolderList);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dropdown_menu, menu);
        return true;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void insertItem(int position) {
        mFolderList.add(position, new FolderItem(R.drawable.ordner_empty, "", ""));
        mAdapter.notifyItemInserted(position);
        mFolderList.get(position).changeText1("Neuer Ordner (" + position + ")");

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

        open_EditFolderName(position, mFolderList, mAdapter, true);
    }
    public void open_EditFolderName(int position, ArrayList<FolderItem> mFolderList, FolderAdapter folderAdapter, boolean newFolder) {
        dialogEditFolderName dialog = new dialogEditFolderName(position, mFolderList, folderAdapter, newFolder, mFileList);
        dialog.show(getSupportFragmentManager(), "edit_foldername");
    }
    public void open_EditFolder(int position, ArrayList<FolderItem> mFolderList) {
        dialogEditFolder dialog2 = new dialogEditFolder(position, mFolderList);
        dialog2.show(getSupportFragmentManager(), "edit_folder");
    }
}
