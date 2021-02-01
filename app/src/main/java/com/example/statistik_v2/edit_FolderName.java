package com.example.statistik_v2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;



public class edit_FolderName extends AppCompatDialogFragment {
    private EditText Et_Name;
    private EditText Et_shortDescription;
    private int mPosition;
    private String mEt_NameText;
    private String mEt_shortDescriptionText;
    private ArrayList<FolderItem> mFolderList;
    private FolderAdapter mAdapter;

    public edit_FolderName(int position, String Et_NameText, String Et_shortDescriptionText, ArrayList<FolderItem> FolderList, FolderAdapter Adapter){
        mPosition = position;
        mEt_NameText = Et_NameText;
        mEt_shortDescriptionText = Et_shortDescriptionText;
        mFolderList = FolderList;
        mAdapter = Adapter;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_edit_foldername, null);

        builder.setView(view)
                .setTitle("Ordner benennen")
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name;
                        if(Et_Name.getText().toString().length() < 1){
                            name = "Neuer Ordner (Position:" + mPosition + ")";
                        } else {
                            name = Et_Name.getText().toString();
                        }
                        String shortDescription = Et_shortDescription.getText().toString();
                        mFolderList.get(mPosition).changeText1(name);
                        mFolderList.get(mPosition).changeText2(shortDescription);
                        mAdapter.notifyDataSetChanged();
                    }
                });
        Et_Name = view.findViewById(R.id.Et_Name);
        Et_shortDescription = view.findViewById(R.id.Et_ShortDescription);

        Et_Name.setText(mEt_NameText);
        Et_shortDescription.setText(mEt_shortDescriptionText);

        return builder.create();
    }

}
