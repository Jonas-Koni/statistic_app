package com.example.statistik_v2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class dialog_rename_item extends AppCompatDialogFragment {
    private TextView tvOldName;
    private EditText etNewName;
    private int mPosition;
    private String mOnItemSelectedText;
    private ExampleDialogListener3 listener3;

    public dialog_rename_item(String onItemSelectedText, int position){
        mOnItemSelectedText = onItemSelectedText;
        mPosition = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.layout_dialog_rename_item, null);


        builder.setView(view)
                .setTitle("Spieler umbenennen")
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = etNewName.getText().toString();
                        listener3.applyText3(newName, mPosition);
                    }
                });

        tvOldName = view.findViewById(R.id.tvOldName);
        etNewName = view.findViewById(R.id.etNewName);

        tvOldName.setText(mOnItemSelectedText);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener3 = (ExampleDialogListener3) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener3");
        }
    }

    public interface ExampleDialogListener3{
        void applyText3(String newName, int position);
    }

}
