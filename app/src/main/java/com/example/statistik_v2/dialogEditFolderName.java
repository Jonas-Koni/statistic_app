package com.example.statistik_v2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.io.File;
import java.util.ArrayList;



public class dialogEditFolderName extends AppCompatDialogFragment {
    private EditText Et_Name;
    private EditText Et_shortDescription;
    private int mPosition;
    private ArrayList<FolderItem> mFolderList;
    private FolderAdapter mAdapter;
    private boolean mNewFolder;
    private ArrayList<File> mFileList = new ArrayList<File>();
    private informationGameDbHelper dbHelper = null;

    public dialogEditFolderName(int position, ArrayList<FolderItem> FolderList, FolderAdapter Adapter, boolean newFolder, ArrayList<File> fileList){
        mPosition = position;
        mFolderList = FolderList;
        mAdapter = Adapter;
        mNewFolder = newFolder;
        mFileList = fileList;
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
                        deleteNewDirectory();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeFolderName();
                    }
                });

        this.dbHelper = new informationGameDbHelper(this.getContext());


        Et_Name = view.findViewById(R.id.Et_Name);
        Et_shortDescription = view.findViewById(R.id.Et_ShortDescription);


        if(!mNewFolder) {
            Et_Name.setText(mFolderList.get(mPosition).getText1()); //StandardText soll aktueller Text sein
            Et_shortDescription.setText(mFolderList.get(mPosition).getText2());
        }


        return builder.create();
    }

    private void deleteNewDirectory() {
        if(mNewFolder) {
            mFolderList.remove(mFolderList.size() - 1);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void changeFolderName() {
        String Text1 = Et_Name.getText().toString();
        String Text2 = Et_shortDescription.getText().toString();
        mFolderList.get(mPosition).setText1(Text1);

        SQLiteDatabase database = this.dbHelper.getWritableDatabase();

        if(mNewFolder) {
            dbHelper.insertDirectory(Text1, Text2, this.getContext());
        } else {
            dbHelper.updateDirectory(Text1, Text2, mPosition);
        }
        dbHelper.close();

        mFolderList.get(mPosition).setText2(Text2);
        mAdapter.notifyDataSetChanged();
    }

}
