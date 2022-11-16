package com.example.statistik_v2.EditFolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;

import com.example.statistik_v2.PlayerListPackage.RoomPlayers;
import com.example.statistik_v2.R;

import java.util.List;


public class CustomPlayerSpinnerAdapter extends BaseAdapter {
    Context context;
    //String[] allPlayers;
    LiveData<List<RoomPlayers>> allPlayers;
    //int[] PlayerIsIn;
    LiveData<List<Integer>> PlayerIsIn;
    LayoutInflater inflater;


    public CustomPlayerSpinnerAdapter(Context applicationContext, LiveData<List<RoomPlayers>> allPlayers, LiveData<List<Integer>> playerIsIn) {
        this.context = applicationContext;
        this.allPlayers = allPlayers;
        this.PlayerIsIn = playerIsIn;
        inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return allPlayers.getValue().size();
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
        ImageView Check = (ImageView) view.findViewById(R.id.ivSpinnerPlayerCheck);
        TextView names = (TextView) view.findViewById(R.id.tvSpinnerPlayerName);
        Check.setImageResource(R.drawable.ic_baseline_cancel_24);

        for(int j = 0; j <= PlayerIsIn.getValue().size()-1; j++){
            int PlayerId = PlayerIsIn.getValue().get(i);
            Check.setImageResource(R.drawable.ic_baseline_check_24);
        }

        names.setText(allPlayers.getValue().get(i).getName());
        return view;
    }
}
