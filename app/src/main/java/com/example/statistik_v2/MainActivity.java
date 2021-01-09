package com.example.statistik_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements edit_foldername.dialogListener, edit_folder.ExampleDialogListener2, dialog_rename_item.ExampleDialogListener3, enter_statistic.DialogDataListener  {
    private ArrayList<FolderItem> mFolderList;
    private RecyclerView mRecyclerView;
    private FolderAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button buttonInsert;
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

        buttonInsert = findViewById(R.id.button_insert);
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
        openEdit_Foldername(position, "", "");
    }

    public void openEdit_Foldername(int position, String Et_NameText, String Et_shortDescriptionText) {
        edit_foldername dialog = new edit_foldername(position, Et_NameText, Et_shortDescriptionText);
        dialog.show(getSupportFragmentManager(), "edit_foldername");
    }

    public void open_EditFolder(int position) {
        edit_folder dialog2 = new edit_folder(mFolderList.get(position).getmPlayerList(), position, mFolderList.get(position).getGameType());
        dialog2.show(getSupportFragmentManager(), "edit_folder");
    }

    public void open_EnterStatistic(int GameType, ArrayList PlayerList, int position){ //0: Platzierung; 1: Knffel; 2: Mädn; 3: Monopoly; 4: Wikinger Schach; 5: Zeit und Anzahl
            enter_statistic dialog_data = new enter_statistic(GameType, PlayerList, mFolderList.get(position).getText1().toString(), R.drawable.figure, position);
            dialog_data.show(getSupportFragmentManager(), "ExampleDialog");

    }


    public void createFolderList() {
        mFolderList = new ArrayList<>();
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
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

                openEdit_Foldername(position, Et_NameText, Et_shortDescriptionText);
            }

            @Override
            public void onSettingsClick(int position) {
                open_EditFolder(position);
            }
        });
    }

    @Override
    public void applyText(String Name, String shortDescription, int position) {
        mFolderList.get(position).changeText1(Name);
        mFolderList.get(position).changeText2(shortDescription);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dropdown_menu, menu);
        return true;
    }

    @Override
    public void apply_btnEnterGame(int GameType, ArrayList<String> Player, int position, int reason) {
        mFolderList.get(position).changeGameType(GameType);
        mFolderList.get(position).changePlayerList(Player);

        switch (reason) {
            case 0:
                break;
            case 1:
                open_EnterStatistic(GameType, Player, position);

        }
    }
    @Override
    public void applyText3(String newName, int position) {
    }

    @Override
    public void applyTexts4(ArrayList PlayerList, int position) {
        mFolderList.get(position).changePlayerList(PlayerList);
    }
}
