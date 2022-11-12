package com.example.statistik_v2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;



public class dialogEditFolderName extends AppCompatDialogFragment {
    private EditText Et_Name;
    private EditText Et_shortDescription;
    private Button BtnDeleteDirectory;

    private int mPosition;
    private int mDirectoryId;
    private ArrayList<FolderItem> mFolderList;
    private FolderAdapter mAdapter;
    private boolean mNewFolder;
    private informationGameDbHelper dbHelper = null;

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
                            deleteDirectory(mFolderList.size() - 1);
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeFolderName();
                    }
                });

        mDirectoryId = mFolderList.get(mPosition).getDirectoryId();


        this.dbHelper = new informationGameDbHelper(this.getContext());


        Et_Name = view.findViewById(R.id.Et_Name);
        Et_shortDescription = view.findViewById(R.id.Et_ShortDescription);
        BtnDeleteDirectory = view.findViewById(R.id.BtnDeleteDirectory);

        BtnDeleteDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDirectory(mPosition);
                dismiss();
            }
        });

        if(!mNewFolder) {
            Et_Name.setText(mFolderList.get(mPosition).getText1()); //StandardText soll aktueller Text sein
            Et_shortDescription.setText(mFolderList.get(mPosition).getText2());
        }


        return builder.create();
    }


    private void deleteDirectory(int index) {

            if (!mNewFolder) {
                dbHelper.deleteDirectory(mFolderList.get(index).getDirectoryId(), this.getContext());
                dbHelper.close();

            }

        mFolderList.remove(index);
        mAdapter.notifyDataSetChanged();
        mAdapter.notifyItemRemoved(index);
    }

    private void changeFolderName() {
        String Text1 = Et_Name.getText().toString();
        String Text2 = Et_shortDescription.getText().toString();

        mFolderList.get(mPosition).setText1(Text1);
        mFolderList.get(mPosition).setText2(Text2);

        int newDirectoryId;

        if(mNewFolder) {
            dbHelper.insertDirectory(Text1, Text2, this.getContext());
            newDirectoryId = dbHelper.getDirectoryInformation().get(dbHelper.getDirectoryInformation().size() - 1).getDirectoryId();
            mFolderList.get(mPosition).setDirectoryId(newDirectoryId);

        } else {
            dbHelper.updateDirectory(Text1, Text2, mDirectoryId);
        }
        dbHelper.close();
        mAdapter.notifyDataSetChanged();
    }

}
