package com.example.statistik_v2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GamesAdapter extends ArrayAdapter<GamesItem> {
    public GamesAdapter(Context context, ArrayList<GamesItem> gamesList){
        super(context, 0, gamesList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.games_spinner_row, parent,false
            );
        }

        ImageView ImageViewGame = convertView.findViewById(R.id.icon_view_games);
        TextView textViewName = convertView.findViewById(R.id.text_view_games);

        GamesItem currentItem = getItem(position);

        if(currentItem != null) {
            ImageViewGame.setImageResource(currentItem.getGameIcon());
            textViewName.setText(currentItem.getGameName());
        }

        return convertView;
    }
}
