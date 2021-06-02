package com.example.medic3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class appointmentlistActivity extends AppCompatActivity {

    ListView lvAppointments;
    ArrayList<appointmentitemclass> list;
    private String readdata;
    private String rows[];
    private String columns[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentlist);



        lvAppointments=findViewById(R.id.lvAppointments);
        list=new ArrayList<appointmentitemclass>();

        try
        {
            appointmentDB db=new appointmentDB(appointmentlistActivity.this);
            db.open();
            readdata=db.getData();
            db.close();
        }
        catch (SQLException e)
        {
            Toast.makeText(appointmentlistActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        rows=readdata.split("\n");

        for (int i=0;i<rows.length;i++)
        {

            columns=rows[i].split(":");
            appointmentitemclass appointment1=new appointmentitemclass(columns[1],columns[2],columns[3],columns[4],columns[5]);
            list.add(appointment1);

        }






        appointmentlistadaptor adaptor=new appointmentlistadaptor(appointmentlistActivity.this,list);
        lvAppointments.setAdapter(adaptor);


    }
}
