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
        dialogRenameItem.ExampleDialogListener3 , DatePickerDialog.OnDateSetListener {//, enter_statistic.DialogDataListener {
    private ArrayList<FolderItem> mFolderList;
    private FolderAdapter mAdapter;
    private String Et_NameText;
    private String Et_shortDescriptionText;
    private int mPosition;
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        mFolderList.get(mPosition).changeDate(c.getTime());
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        //open_EnterStatistic(mFolderList.get(mPosition).getGameType(), mFolderList.get(mPosition).getmPlayerList(), mPosition);
    }

    public void insertItem(int position) {
        mFolderList.add(position, new FolderItem(R.drawable.ordner_empty, "Neuer Ordner (" + position + ")", "This is Line 2"));
        mAdapter.notifyItemInserted(position);
        open_EditFolderName(position, "", "", mFolderList, mAdapter);
    }

    public void open_EditFolderName(int position, String Et_NameText, String Et_shortDescriptionText, ArrayList<FolderItem> mFolderList, FolderAdapter folderAdapter) {
        edit_FolderName dialog = new edit_FolderName(position, Et_NameText, Et_shortDescriptionText, mFolderList, folderAdapter);
        dialog.show(getSupportFragmentManager(), "edit_foldername");
    }

    public void open_EditFolder(int position, ArrayList<FolderItem> mFolderList) {
        dialogEditFolder dialog2 = new dialogEditFolder(mFolderList.get(position).getmPlayerList(), position, mFolderList.get(position).getGameType(), mFolderList);
        dialog2.show(getSupportFragmentManager(), "edit_folder");
    }

    /*public void open_EnterStatistic(int GameType, ArrayList PlayerList, int position){ //0: Platzierung; 1: Knffel; 2: Mädn; 3: Monopoly; 4: Wikinger Schach; 5: Zeit und Anzahl
            enter_statistic dialog_data = new enter_statistic(GameType, PlayerList, mFolderList.get(position).getText1().toString(), R.drawable.figure, position);
            dialog_data.show(getSupportFragmentManager(), "ExampleDialog");

    }*/




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

                open_EditFolderName(position, Et_NameText, Et_shortDescriptionText, mFolderList, mAdapter);
            }

            @Override
            public void onSettingsClick(int position) {
                mPosition = position;
                open_EditFolder(position, mFolderList);
            }
        });
    }

    /*@Override
    public void applyText(String Name, String shortDescription, int position) {
        mFolderList.get(position).changeText1(Name);
        mFolderList.get(position).changeText2(shortDescription);
        mAdapter.notifyDataSetChanged();

    }
        */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dropdown_menu, menu);
        return true;
    }

    /*@Override
    public void apply_btnEnterGame(int GameType, ArrayList<String> Player, int position, int reason) {
        mFolderList.get(position).changeGameType(GameType);
        mFolderList.get(position).changePlayerList(Player);

        mPosition  = position;

        switch (reason) {
            case 0:
                break;
            case 1:
                open_EnterStatistic(GameType, Player, position);
                break;
            default:
                break;
        }
    }*/
    @Override
    public void applyText3(String newName, int position) {
    }

    /*@Override
    public void applyTexts4(ArrayList PlayerList, int position, int reason) {
        mFolderList.get(position).changePlayerList(PlayerList);
        switch (reason){
            case -1:
                break;
            case 0:
                DialogFragment datePicker = new com.example.statistik_v2.DatePicker();
                datePicker.show(getSupportFragmentManager(),"date picker");
                break;
            case 1:
                break;
            default:
                break;

        }
    }*/
}
