package com.example.statistik_v2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class dialogAddPlayer extends AppCompatDialogFragment {
    private TextView tvOldName;
    private EditText etNewName;
    private int mPosition;
    private String mOnItemSelectedText;
    private ExampleDialogListener4 listener4;

    public dialogAddPlayer(String onItemSelectedText, int position){
        mOnItemSelectedText = onItemSelectedText;
        mPosition = position;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener4 = (ExampleDialogListener4) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener4");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.layout_add_player, null);


        builder.setTitle("Spieler hinzfügen")
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = etNewName.getText().toString();
                        listener4.applyText4(newName, mPosition);
                    }
                });

        tvOldName = view.findViewById(R.id.tvOldPlayerName);
        etNewName = view.findViewById(R.id.etNewPlayername);

        tvOldName.setText(mOnItemSelectedText);


        return builder.create();
    }

    public interface ExampleDialogListener4{
        void applyText4(String newName, int position);
    }

}
