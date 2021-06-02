package com.example.medic3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Pilllistastivity extends AppCompatActivity {

    ListView lvPills;
    EditText tvpillist;
    Button btnDelete;

    ArrayList<Pillitemclass> list;
    private String readdata;
    private String rows[];
    private String columns[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilllistastivity);

        lvPills=findViewById(R.id.lvPills);
        tvpillist=findViewById(R.id.tvpilllist);
        btnDelete=findViewById(R.id.btnDelete);


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvpillist.getText().toString().isEmpty())
                {
                    Toast.makeText(Pilllistastivity.this, "Please enter the ID number", Toast.LENGTH_SHORT).show();

                }else{
                    String id=tvpillist.getText().toString().trim();
                    try
                    {
                        pilldatabase db=new pilldatabase(Pilllistastivity.this);
                        db.open();



                        db.deleteEntery(id);

                        db.close();

                        /////////////////////////////
                        Toast.makeText(Pilllistastivity.this, "Successfully deleted notification", Toast.LENGTH_SHORT).show();

                        refresh();


                        ///////////////////////
                    }
                    catch (SQLException e)
                    {
                        Toast.makeText(Pilllistastivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });


        list=new ArrayList<Pillitemclass>();

        try
        {
            pilldatabase db=new pilldatabase(Pilllistastivity.this);
            db.open();
            readdata=db.getData();
            db.close();
        }
        catch (SQLException e)
        {
            Toast.makeText(Pilllistastivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        rows=readdata.split("\n");

        for (int i=0;i<rows.length;i++)
        {

            columns=rows[i].split(":");
            Pillitemclass pill=new Pillitemclass(columns[1],columns[2],columns[3],columns[4]);
            list.add(pill);

        }

        Pillitemadaptor adaptor=new Pillitemadaptor(Pilllistastivity.this,list);
        lvPills.setAdapter(adaptor);
    }

    void refresh()
    {

        list=new ArrayList<Pillitemclass>();

        try
        {
            pilldatabase db=new pilldatabase(Pilllistastivity.this);
            db.open();
            readdata=db.getData();
            db.close();
        }
        catch (SQLException e)
        {
            Toast.makeText(Pilllistastivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        rows=readdata.split("\n");

        for (int i=0;i<rows.length;i++)
        {

            columns=rows[i].split(":");
            Pillitemclass pill=new Pillitemclass(columns[1],columns[2],columns[3],columns[4]);
            list.add(pill);

        }

        Pillitemadaptor adaptor=new Pillitemadaptor(Pilllistastivity.this,list);
        lvPills.setAdapter(adaptor);
    }
}
