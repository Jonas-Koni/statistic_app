package com.example.statistik_v2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;

import java.util.ArrayList;

public class dialogEditFolder extends AppCompatDialogFragment {

    private EditText Et_EnterName;
    private ArrayList mPlayerList;
    private int mGameType;
    private int mPosition;
    private int mGamePosition;
    private ArrayList<GamesItem> mGamesList;
    private ArrayList<FolderItem> mFolderList;
    private ArrayList<PlayerListItem> mPlayerListItem;
    private ArrayList<informationGame> mInformationList;
    private informationGameDbHelper dbHelper;

    public dialogEditFolder(int position, ArrayList<FolderItem> FolderList, ArrayList<PlayerListItem> playerListItems){
        mPosition = position;
        mFolderList = FolderList;
        mPlayerListItem = playerListItems;
        mPlayerList = mFolderList.get(mPosition).getmPlayerList();
        mGameType = mFolderList.get(mPosition).getGameType();
    }


    @NonNull
    @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_edit_folder, null);
        initList();

        Spinner mSpinner = (Spinner) view.findViewById(R.id.SpGameType);
        GamesAdapter mAdapter = new GamesAdapter(getActivity(), mGamesList);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setEnabled(true);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mGameType = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final Spinner mSpinner2 = (Spinner) view.findViewById(R.id.SpRenamePlayer);
        ArrayAdapter adapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, mPlayerList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner2.setAdapter(adapter2);

        createInformationGame();
        builder.setView(view)
                .setTitle("Einstellungen")
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveChanges();
                    }
                });

        Et_EnterName = view.findViewById(R.id.Et_EnterName);
        Button btn_ConfirmName = view.findViewById(R.id.BtnConfirmPlayerName);
        Button btnEnterGame = view.findViewById(R.id.BtnEnterGame);
        Button btnEditGame = view.findViewById(R.id.BtnEditGame);
        Button btnSeeStatistic = view.findViewById(R.id.BtnSeeStatistic);

        mSpinner.setSelection(mGameType);

        btn_ConfirmName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PlayerName = Et_EnterName.getText().toString();
                Et_EnterName.setText("");
                mPlayerList.add(PlayerName);
                mSpinner2.setSelection(0);
            }
        });
        btnEnterGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges(); //
                mInformationList.add(new informationGame());
                open_EnterStatistic();
            }
        });

        btnEditGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });
        btnSeeStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        dbHelper = new informationGameDbHelper(this.getContext());


        return builder.create();
    }






    private void initList() {
        mGamesList = new ArrayList<>();
        mGamesList.add(new GamesItem("Platzierung", R.drawable.platzierung));
        mGamesList.add(new GamesItem("Kniffel", R.drawable.kniffel));
        mGamesList.add(new GamesItem("Mensch ärgere dich nicht", R.drawable.figure_2));
        mGamesList.add(new GamesItem("Monopoly", R.drawable.monopoly));
        mGamesList.add(new GamesItem("Wikinger Schach", R.drawable.wikinger_schach));
        mGamesList.add(new GamesItem("Zeit und Anzahl", R.drawable.stoppuhr));
    }


    public void saveChanges(){
        mFolderList.get(mPosition).setGameType(mGameType);
        mFolderList.get(mPosition).setPlayerList(mPlayerList);

        int DirectoryId = mFolderList.get(mPosition).getDirectoryId();
        dbHelper.insertGameType(GameTypeToInteger(mGameType), DirectoryId);
        //dbHelper.insertDirectoryPlayers();
    }



    public String GameTypeToInteger(int gameType) {
        String GameType = "error";
        switch (gameType) {
            case 0:
                GameType = "Platzierung";
                break;
            case 1:
                GameType = "Kniffel";
                break;
            case 2:
                GameType = "Madn";
                break;
            case 3:
                GameType = "Monopoly";
                break;
            case 4:
                GameType = "Wikinger Schach";
                break;
            case 5:
                GameType = "Zeit und Anzahl";
                break;
        }
        return GameType;
    }

    public void open_EnterStatistic(){ //0: Platzierung; 1: Knffel; 2: Mädn; 3: Monopoly; 4: Wikinger Schach; 5: Zeit und Anzahl
        String GameType = GameTypeToInteger(mGameType);
        int DirectoryId = mFolderList.get(mPosition).getDirectoryId();

        ArrayList informationGamePlayerStatsArray = new ArrayList();
        int GameId = dbHelper.getInformation(mPosition).size();
        dbHelper.insertGameType(GameType, mPosition);

        for (int player = 0; player < mFolderList.get(mPosition).getmPlayerList().size(); player ++) {
            informationGamePlayerStats informationGamePlayerStats = new informationGamePlayerStats(mPosition, GameId);
            informationGamePlayerStatsArray.add(informationGamePlayerStats);
        }
        dbHelper.close();

        dialogEnterStatistic dialog_data = new dialogEnterStatistic(R.drawable.figure, mPosition, mFolderList, mInformationList, 0, informationGamePlayerStatsArray);
        dialog_data.show(getFragmentManager(), "ExampleDialog");

    }
    public void createInformationGame () {
        mInformationList = new ArrayList();

    }
}