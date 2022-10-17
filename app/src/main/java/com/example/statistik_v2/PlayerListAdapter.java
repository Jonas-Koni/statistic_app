package com.example.statistik_v2;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.text.AlphabeticIndex;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerListViewHolder> {
    private ArrayList<PlayerListItem> mPlayerList;

    public class PlayerListViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;

        public PlayerListViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.ivIconPlayer);
            mTextView1 = itemView.findViewById(R.id.playerName);
        }
    }

    public PlayerListAdapter(ArrayList<PlayerListItem> playerList) {
        mPlayerList = playerList;
    }

    @NonNull
    @Override
    public PlayerListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.playerlist_item, parent, false);
        PlayerListViewHolder plv = new PlayerListViewHolder(v);
        return plv;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerListViewHolder holder, int position) {
        PlayerListItem currentItem = mPlayerList.get(position);
        //String[] Name = currentItem.getName().split(" ");
        //Drawable Icon = TextDrawable.builder().buildRoundRect(String.valueOf(Name[0].charAt(0)), Color.RED, 5);

        holder.mTextView1.setText(currentItem.getName());
        //holder.mImageView.setImageDrawable(Icon);
        if(currentItem.getImageResource() >= 0) {
            holder.mImageView.setImageResource(currentItem.getImageResource());
        } else {
            String[] Name = currentItem.getName().split(" ");
            String ShortName;
            if(Name.length>=2){
                ShortName = String.valueOf(Name[0].charAt(0)) + String.valueOf(Name[1].charAt(0));
            } else {
                ShortName = String.valueOf(Name[0].charAt(0));
            }
            //ColorGenerator generator = ColorGenerator.MATERIAL; //has to be a fixed Color
            float[] HSV = new float[3];
            HSV[0] = currentItem.getName().hashCode() % 360;
            HSV[1] = (float) 0.8;
            HSV[2] = (float) 0.5;
            int HashName = Color.HSVToColor(255, HSV);
            Drawable Icon = TextDrawable.builder().buildRoundRect(ShortName, HashName, 5); //
            holder.mImageView.setImageDrawable(Icon);
        }
    }

    @Override
    public int getItemCount() {
        return mPlayerList.size();
    }
}
