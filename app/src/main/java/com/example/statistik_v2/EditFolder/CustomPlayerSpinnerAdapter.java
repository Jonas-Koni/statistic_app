package com.example.statistik_v2.EditFolder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.List;

public class CustomPlayerSpinnerAdapter extends ArrayAdapter {

    public CustomPlayerSpinnerAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
    }
    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return super.getView(i, view, viewGroup);
    }
}
