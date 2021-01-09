package com.example.statistik_v2;

import android.app.Dialog;
import android.content.Context;
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

import java.util.ArrayList;

public class edit_folder extends AppCompatDialogFragment {

    private EditText Et_EnterName;
    private ExampleDialogListener2 listener2;
    private ArrayList<String> mPlayerList = new ArrayList<String>();
    private int mGameType;
    private int mPosition;
    private ArrayList<GamesItem> mGamesList;

    public edit_folder(ArrayList<String> PlayerList, int position, int GameType){
        mPlayerList = PlayerList;
        mPosition = position;
        mGameType = GameType;
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
                        listener2.apply_btnEnterGame(mGameType, mPlayerList, mPosition, 0);
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
                listener2.apply_btnEnterGame(mGameType, mPlayerList, mPosition, 1);
                dismiss();
            }
        });

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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener2 = (ExampleDialogListener2) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement dialogListener2");
        }
    }

    public interface ExampleDialogListener2 {
        void apply_btnEnterGame(int GameType, ArrayList<String> Player, int position, int reason); //int reason: 0:nothing; 1:EnterGame; 2: EditGame; 3: SeeStatistics
    }
}