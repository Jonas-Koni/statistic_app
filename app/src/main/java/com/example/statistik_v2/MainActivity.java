package com.example.statistik_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements //edit_folder.ExampleDialogListener2, edit_foldername.dialogListener,
        dialogRenameItem.ExampleDialogListener3{// , DatePickerDialog.OnDateSetListener {//, dialogEnterStatistic.DialogDataListener {
    private ArrayList<FolderItem> mFolderList;
    private FolderAdapter mAdapter;
    private String Et_NameText;
    private String Et_shortDescriptionText;
    private boolean errorText1 = false;
    private boolean errorText2 = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFolderList();
        buildRecyclerView();

        Button buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mFolderList.size();
                insertItem(position);
            }
        });

    }

    public void insertItem(int position) {
        mFolderList.add(position, new FolderItem(R.drawable.ordner_empty, "Neuer Ordner (" + position + ")", "This is Line 2"));
        mAdapter.notifyItemInserted(position);
        open_EditFolderName(position, "", "", mFolderList, mAdapter, true);
    }

    public void open_EditFolderName(int position, String Et_NameText, String Et_shortDescriptionText, ArrayList<FolderItem> mFolderList, FolderAdapter folderAdapter, boolean deleteOnCancel) {
        dialogEditFolderName dialog = new dialogEditFolderName(position, Et_NameText, Et_shortDescriptionText, mFolderList, folderAdapter, deleteOnCancel);
        dialog.show(getSupportFragmentManager(), "edit_foldername");
    }

    public void open_EditFolder(int position, ArrayList<FolderItem> mFolderList) {
        dialogEditFolder dialog2 = new dialogEditFolder(position, mFolderList);
        dialog2.show(getSupportFragmentManager(), "edit_folder");
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
                try {
                    String text = mFolderList.get(position).getText1().toString();
                } catch (NullPointerException e) {
                    errorText1 = true;
                }
                try {
                    String text = mFolderList.get(position).getText2().toString();
                } catch (NullPointerException e) {
                    errorText2 = true;
                }

                if (errorText1) {
                    Et_NameText = "";
                } else {
                    Et_NameText = mFolderList.get(position).getText1().toString();
                }

                if (errorText2) {
                    Et_shortDescriptionText = "";
                } else {
                    Et_shortDescriptionText = mFolderList.get(position).getText2().toString();
                }

                errorText1 = false;
                errorText2 = false;

                open_EditFolderName(position, Et_NameText, Et_shortDescriptionText, mFolderList, mAdapter, false);
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

    @Override
    public void applyText3(String newName, int position) {
    }
}
