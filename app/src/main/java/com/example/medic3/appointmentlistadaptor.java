package com.example.medic3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class appointmentlistadaptor extends ArrayAdapter<appointmentitemclass> {

    private final Context context;
    private ArrayList<appointmentitemclass> values;


    public appointmentlistadaptor(@NonNull Context context, ArrayList<appointmentitemclass> list) {
        super(context, R.layout.appointlist_layout,list);
        this.context = context;
        this.values = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View appointmentlistview=inflater.inflate(R.layout.appointlist_layout,parent,false);
        TextView tvCityap=appointmentlistview.findViewById(R.id.tvCityap);
        TextView tvCenterap=appointmentlistview.findViewById(R.id.tvCenterap);
        TextView tvDoctorapp=appointmentlistview.findViewById(R.id.tvDoctorapp);
        TextView tvDateap=appointmentlistview.findViewById(R.id.tvDateap);
        TextView tvTimeap=appointmentlistview.findViewById(R.id.tvTimeap);

        tvCityap.setText(values.get(position).getRegion());
        tvCenterap.setText(values.get(position).getCenter());
        tvDoctorapp.setText(values.get(position).getDoctor());
        tvDateap.setText(values.get(position).getDate());
        tvTimeap.setText(values.get(position).getTime());




        return appointmentlistview;
    }
}
