package com.example.statistik_v2.FolderListPackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.statistik_v2.FolderAdapter;
import com.example.statistik_v2.PlayerListPackage.PlayerListAdapter;
import com.example.statistik_v2.PlayerListPackage.RoomPlayers;
import com.example.statistik_v2.R;

import java.util.ArrayList;
import java.util.List;

public class FolderListAdapter extends RecyclerView.Adapter<FolderListAdapter.FolderListViewHolder> {
    private OnItemClickListener mListener;
    private List<RoomFolders> folders = new ArrayList<>();

    @NonNull
    @Override
    public FolderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.folder_item, parent, false);
        return new FolderListAdapter.FolderListViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderListViewHolder holder, int position) {
        RoomFolders currentFolder = folders.get(position);

        holder.ivFolderImage.setImageResource(currentFolder.getImageResource());
        holder.tvFolderTitle.setText(currentFolder.getTitle());
        holder.tvFolderDescription.setText(currentFolder.getDescription());
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    public void setFolders(List<RoomFolders> folders) {
        this.folders = folders;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(RoomFolders folders);
    }

    public void setOnItemClickListener(FolderListAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    class FolderListViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFolderImage;
        private TextView tvFolderTitle;
        private TextView tvFolderDescription;

        public FolderListViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            ivFolderImage = itemView.findViewById(R.id.ivFolderImage);
            tvFolderTitle = itemView.findViewById(R.id.tvFolderTitle);
            tvFolderDescription = itemView.findViewById(R.id.tvFolderDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(folders.get(position));
                    }
                }
            });
        }
    }
}
