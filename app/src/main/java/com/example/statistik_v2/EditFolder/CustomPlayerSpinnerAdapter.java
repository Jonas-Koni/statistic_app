package com.example.statistik_v2.EditFolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;

import com.example.statistik_v2.PlayerListPackage.RoomPlayers;
import com.example.statistik_v2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CustomPlayerSpinnerAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<SpinnerCheckboxState> listCbStates;


    public CustomPlayerSpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        try {
            return listCbStates.size();
        } catch (Exception e) {
            return 0;
        }
        //return allPlayers.getValue().size();
    }

    public void setListCbStates(ArrayList<SpinnerCheckboxState> listCbStates) {
        this.listCbStates = listCbStates;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.custom_player_spinner_items, null);

        TextView names = (TextView) view.findViewById(R.id.tvSpinnerPlayerName);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cbSpinnerPlayer);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //int position = (Integer) compoundButton.getId();
                listCbStates.get(i).setSelected(b);
            }
        });

        names.setText(listCbStates.get(i).getTitle());
        checkBox.setChecked(listCbStates.get(i).isSelected());
        return view;
    }
}