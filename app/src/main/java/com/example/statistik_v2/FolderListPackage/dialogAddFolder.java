package com.example.statistik_v2.FolderListPackage;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.statistik_v2.PlayerListPackage.RoomPlayers;
import com.example.statistik_v2.PlayerListPackage.dialogAddPlayer;
import com.example.statistik_v2.R;

public class dialogAddFolder extends AppCompatDialogFragment {
    private EditText etTitle;
    private EditText etDescription;
    private dialogAddFolder.dialogAddFolderListener listener;
    final private boolean isNewFolder;
    final private String newFolderTitle;
    final private String newFolderDescription;
    final private RoomFolders Folder;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (dialogAddFolder.dialogAddFolderListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement AddFolderListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_edit_foldername, null);
        String Title;
        if (isNewFolder){
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

        etTitle = view.findViewById(R.id.Et_Title);
        etDescription = view.findViewById(R.id.Et_ShortDescription);
        Button BtnDeleteFolder = view.findViewById(R.id.BtnDeleteDirectory);

        etTitle.setText(newFolderTitle);
        etDescription.setText(newFolderDescription);

        BtnDeleteFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.applyFolder(Folder,true, isNewFolder);
                dismiss();
            }
        });
        return builder.create();
    }

    public dialogAddFolder(boolean isNewFolder, RoomFolders folders){
        this.isNewFolder = isNewFolder;
        this.newFolderTitle = folders.getTitle();
        this.newFolderDescription = folders.getDescription();
        this.Folder = folders;

    }
    public interface dialogAddFolderListener {
        void applyFolder(RoomFolders folders, boolean delete, boolean isNewPlayer);
    }

    private void changeFolderName() {
        String Title = etTitle.getText().toString();
        String Description = etDescription.getText().toString();
        if(Title.trim().isEmpty() || Description.trim().isEmpty()) {
            Toast.makeText(getContext(), "Please insert Title", Toast.LENGTH_SHORT).show();
            return;
        }
        Folder.setTitle(Title);
        Folder.setDescription(Description);
        listener.applyFolder(Folder, false, isNewFolder);
    }

}
