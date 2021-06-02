package com.example.medic3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Pillitemadaptor extends ArrayAdapter {

    private final Context context;
    private ArrayList<Pillitemclass> values;

    public Pillitemadaptor(@NonNull Context context, ArrayList<Pillitemclass> list) {
        super(context, R.layout.pilllist_layout,list);
        this.context = context;
        this.values = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View pilllistview=inflater.inflate(R.layout.pilllist_layout,parent,false);
        TextView tvPillname=pilllistview.findViewById(R.id.tvPillname);
        TextView tvStart=pilllistview.findViewById(R.id.tvStart);
        TextView tvDuration=pilllistview.findViewById(R.id.tvDuration);
        TextView tvID=pilllistview.findViewById(R.id.tvId);

        tvPillname.setText(values.get(position).getPillname());
        tvStart.setText(values.get(position).getStarttime());
        tvDuration.setText(values.get(position).getDuration());
        tvID.setText(values.get(position).getNotID());





        return pilllistview;
    }
}
