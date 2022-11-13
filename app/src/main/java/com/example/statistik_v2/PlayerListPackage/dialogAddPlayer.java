package com.example.statistik_v2.PlayerListPackage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.statistik_v2.R;

public class dialogAddPlayer extends AppCompatDialogFragment {
    private EditText etNewName;
    private dialogAddPlayerListener listener;
    final private boolean isNewPlayer;
    final private String newPlayerName;
    final private RoomPlayers Player;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (dialogAddPlayerListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement AddPlayerListener");
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_add_player, null);
        String Title;
        if (isNewPlayer){
            Title = "Spieler hinzufügen";
        } else {
            Title = "Spieler editieren";
        }

        builder.setView(view)
                .setTitle(Title)
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeFolderName();

                    }
                });

        etNewName = view.findViewById(R.id.etNewPlayername);
        //nur, wenn keine Daten mit Spieler
        Button btnDeletePlayer = view.findViewById(R.id.BtnDeletePlayer);
        etNewName.setText(newPlayerName);

        btnDeletePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.applyTexts(Player,true, isNewPlayer);
                dismiss();
            }
        });

        return builder.create();
    }

    public dialogAddPlayer(boolean isNewPlayer, RoomPlayers players){
        this.isNewPlayer = isNewPlayer;
        this.newPlayerName = players.getName();
        this.Player = players;
    }

    public interface dialogAddPlayerListener {
        void applyTexts(RoomPlayers player, boolean delete, boolean isNewPlayer);
    }


    private void changeFolderName() {
        String Text1 = etNewName.getText().toString();
        if(Text1.trim().isEmpty()) {
            Toast.makeText(getContext(), "Please insert Name", Toast.LENGTH_SHORT).show();
            return;
        }
        Player.setName(Text1);
        listener.applyTexts(Player, false, isNewPlayer);
    }
}
