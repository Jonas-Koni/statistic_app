package com.example.statistik_v2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private ArrayList<DataItem> mDataList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTitle;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.ivLogo);
            mTitle = itemView.findViewById(R.id.titleItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public DataAdapter(ArrayList<DataItem> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override
    public DataAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        DataViewHolder dvh = new DataViewHolder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        DataItem currentItem = mDataList.get(position);

        holder.mTitle.setText(currentItem.getTitle());
        holder.mImageView.setImageResource(currentItem.getImageResource());

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
