package com.example.statistik_v2.PlayerListPackage;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.statistik_v2.R;

import java.util.ArrayList;
import java.util.List;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerListViewHolder> {
    private OnItemClickListener mListener;
    private List<RoomPlayers> players = new ArrayList<>();

    @NonNull
    @Override
    public PlayerListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playerlist_item, parent, false);
        return new PlayerListViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerListViewHolder holder, int position) {
        RoomPlayers currentItem = players.get(position);

        holder.mImageView.setImageDrawable(drawIcon(currentItem));
        holder.mTextView1.setText(currentItem.getName());

    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setPlayers(List<RoomPlayers> players) {
        this.players = players;
        notifyDataSetChanged();
    }


    public RoomPlayers getPlayerAt(int position) {
        return players.get(position);
    }
    //delete: in PlayerList
    /*
    playerViewModel.delete(adapter.getPlayerAt(viewHolder.getAdapterPosition()));
     */

    public interface OnItemClickListener {
        void onItemClick(RoomPlayers players);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    class PlayerListViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;

        public PlayerListViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.ivIconPlayer);
            mTextView1 = itemView.findViewById(R.id.playerName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(players.get(position));
                    }
                }
            });
        }
    }


    public Drawable drawIcon(RoomPlayers currentItem) {
        if (currentItem.getIcon() != -1) {
            return null; //später für eigene Bilder
        }
        if (currentItem.getName() == null) {
            return null;
        }

        String[] Name = currentItem.getName().split(" ");
        String ShortName = "";

        switch (Name.length) {
            case 0:
                System.out.println("falsch gefiltert");
                break;
            case 1:
                ShortName = String.valueOf(Name[0].charAt(0));
                break;
            default:
                ShortName = String.valueOf(Name[0].charAt(0)) + String.valueOf(Name[1].charAt(0));
        }

        float[] HSV = new float[3];
        HSV[0] = currentItem.getName().hashCode() % 360;
        HSV[1] = (float) 0.8;
        HSV[2] = (float) 0.5;
        int HashName = Color.HSVToColor(255, HSV);
        Drawable Icon = TextDrawable.builder().buildRoundRect(ShortName, HashName, 5);

        return Icon;
    }
}
