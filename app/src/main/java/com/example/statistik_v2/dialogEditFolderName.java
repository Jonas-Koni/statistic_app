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



public class dialogEditFolderName extends AppCompatDialogFragment {
    private EditText Et_Name;
    private EditText Et_shortDescription;
    private int mPosition;
    private ArrayList<FolderItem> mFolderList;
    private FolderAdapter mAdapter;
    private boolean mNewFolder;

    public dialogEditFolderName(int position, ArrayList<FolderItem> FolderList, FolderAdapter Adapter, boolean newFolder){
        mPosition = position;
        mFolderList = FolderList;
        mAdapter = Adapter;
        mNewFolder = newFolder;
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
                        if(mNewFolder) {
                            mFolderList.remove(mFolderList.size() - 1);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeFolderName();
                    }
                });

        Et_Name = view.findViewById(R.id.Et_Name);
        Et_shortDescription = view.findViewById(R.id.Et_ShortDescription);


        if(!mNewFolder) {
            Et_Name.setText(mFolderList.get(mPosition).getText1()); //StandardText soll aktueller Text sein
            Et_shortDescription.setText(mFolderList.get(mPosition).getText2());
        }

        return builder.create();
    }
    private void changeFolderName() {
        String Text1 = Et_Name.getText().toString();
        String Text2 = Et_shortDescription.getText().toString();
        if(Text1.length()>=1){
            mFolderList.get(mPosition).changeText1(Text1);
        }
        mFolderList.get(mPosition).changeText2(Text2);
        mAdapter.notifyDataSetChanged();
    }

}
