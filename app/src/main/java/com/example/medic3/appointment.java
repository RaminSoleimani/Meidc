package com.example.medic3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class appointment extends AppCompatActivity {

    Spinner spRegions,spBar ,spSab, spMat, spTer,spSant,spDoc;
    TextView tvRegion, tvDate, tvTime;

    Context mycotext=this;
    String regions[]={"Barcelona","Sabadell","Mataró","Terrassa","Sant Cugat del Vallès"};
    String Barca[]={"Barcelona Life Care","Centro Médico Quirónsalud Aribau","Unitat d'Oncothermia Barcelona","Medical Group Barcelona",
    "Acupuntura Barcelona - Dr Victor Espiga"};
    String Sab[]={"Centre Mèdic Sabadell","Check Vitae","Acosta't Terapies","Institut De Terapia Neural","CAP Sant Fèlix"};
    String Mat[]={"Centre Mèdic de Mataró","CENTRE MEDIC REHASTET","Eduard Babot Andreu","RetiaTech S.L"};
    String Ter[]={"medical center, Terrassa","Mútua Universal Terrassa","Europreven Terrassa","Consulta Privada de Brunet Costa, Jordi"};
    String Sant[]={"Tertulia Fisiocos Centre de Salut Sant Cugat","Policlínic Sant Cugat SLP","Clinic Sant Cugat Sl"};
    String Doc[]={"Family Physicians","Immunologists","Anesthesiologists","Cardiologists","Colon and Rectal Surgeons","Neurologists","Oncologists"
            ,"Ophthalmologists","Osteopaths"};
    ArrayAdapter<String> regionAdaptor, barAdaptor, sabadaptor, matadaptor,terAdaptor,santAdaptor,docAdaptor;
    String region, pcenter, pdoctor, ptime, pdat;
    String selecteddate, selectedtime;
    Button btnRegion, btnCen, btnDoc, btnSubmit, btnShow;
    Boolean Sregion,Scenter,Sdoctor,Sdate,Stime;

    private static String Tag="appointment";
    DatePickerDialog.OnDateSetListener myDateSetListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        spRegions=findViewById(R.id.spRegion);

        btnRegion=findViewById(R.id.btnRegion);
        btnCen=findViewById(R.id.btnCen);
        btnDoc=findViewById(R.id.btnDoc);
        btnSubmit=findViewById(R.id.btnSubmiit);
        btnShow=findViewById(R.id.btnShowp);

        tvDate=findViewById(R.id.tvDate);
        tvTime=findViewById(R.id.tvTime);

        spBar=findViewById(R.id.spBar);
        spSab=findViewById(R.id.spSab);
        spMat=findViewById(R.id.spMat);
        spTer=findViewById(R.id.spTer);
        spSant=findViewById(R.id.spSant);
        spDoc=findViewById(R.id.spDoc);

        tvRegion=findViewById(R.id.tvRegion);

        tvRegion.setVisibility(View.GONE);
        spBar.setVisibility(View.GONE);
        spSab.setVisibility(View.GONE);
        spMat.setVisibility(View.GONE);
        spTer.setVisibility(View.GONE);
        spSant.setVisibility(View.GONE);
        spDoc.setVisibility(View.GONE);
        btnCen.setVisibility(View.GONE);
        btnDoc.setVisibility(View.GONE);

        Sregion=false;
        Scenter=false;
        Sdoctor=false;
        Sdate=false;
        Stime=false;


       // spCenter.setClickable(false);



        regionAdaptor=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,regions);
        regionAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRegions.setAdapter(regionAdaptor);

        spRegions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                region=parent.getSelectedItem().toString();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(region)
                {
                    case "Barcelona":
                        spBar.setVisibility(View.VISIBLE);
                        spSab.setVisibility(View.GONE);
                        spMat.setVisibility(View.GONE);
                        spTer.setVisibility(View.GONE);
                        spSant.setVisibility(View.GONE);

                        break;
                    case "Sabadell":
                        spBar.setVisibility(View.GONE);
                        spSab.setVisibility(View.VISIBLE);
                        spMat.setVisibility(View.GONE);
                        spTer.setVisibility(View.GONE);
                        spSant.setVisibility(View.GONE);
                        break;
                    case "Mataró":
                        spBar.setVisibility(View.GONE);
                        spSab.setVisibility(View.GONE);
                        spMat.setVisibility(View.VISIBLE);
                        spTer.setVisibility(View.GONE);
                        spSant.setVisibility(View.GONE);
                        break;
                    case "Terrassa":
                        spBar.setVisibility(View.GONE);
                        spSab.setVisibility(View.GONE);
                        spMat.setVisibility(View.GONE);
                        spTer.setVisibility(View.VISIBLE);
                        spSant.setVisibility(View.GONE);
                        break;
                    case "Sant Cugat del Vallès":
                        spBar.setVisibility(View.GONE);
                        spSab.setVisibility(View.GONE);
                        spMat.setVisibility(View.GONE);
                        spTer.setVisibility(View.GONE);
                        spSant.setVisibility(View.VISIBLE);
                        break;

                    default:

                }
                btnCen.setVisibility(View.VISIBLE);
                Sregion=true;


            }
        });

        btnCen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spDoc.setVisibility(View.VISIBLE);
                btnDoc.setVisibility(View.VISIBLE);
                Scenter=true;

            }
        });






        barAdaptor=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Barca);
        barAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBar.setAdapter(barAdaptor);

        spBar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pcenter=parent.getSelectedItem().toString();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sabadaptor=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Sab);
        sabadaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSab.setAdapter(sabadaptor);

        spSab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                pcenter=parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        matadaptor=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Mat);
        matadaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMat.setAdapter(matadaptor);

        spMat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                pcenter=parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        terAdaptor=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Ter);
        terAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTer.setAdapter(terAdaptor);

        spTer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                pcenter=parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        santAdaptor=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Sant);
        santAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSant.setAdapter(santAdaptor);

        spSant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pcenter=parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        docAdaptor=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Doc);
        docAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDoc.setAdapter(docAdaptor);

        spDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                pdoctor=parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        tvDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Calendar cal=Calendar.getInstance();
               // int year=cal.get(Calendar.YEAR);
                //int month=cal.get(Calendar.MONTH);
                //int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(appointment.this
                        ,android.R.style.Theme_DeviceDefault_Dialog,
                        myDateSetListener,2020,4,6);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        myDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                selecteddate=year+"-"+month+"-"+dayOfMonth;

                tvDate.setText(selecteddate);
                Sdate=true;

            }
        };

        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(mycotext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvTime.setText(hourOfDay+":"+minute);
                        selectedtime=hourOfDay+"-"+minute;
                        Stime=true;

                    }
                },00,00,android.text.format.DateFormat.is24HourFormat(mycotext));
                timePickerDialog.show();
            }
        });

        btnDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sdoctor=true;

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Sregion)
                { 
                    if(Scenter)
                    {
                      if(Sdoctor)
                      {
                          if(Sdate)
                          {
                              if(Stime)
                              {
                                  try
                                  {
                                      appointmentDB db=new appointmentDB(appointment.this);
                                      db.open();
                                      //db.cleardb();
                                      db.createEntery(region,pcenter,pdoctor,selecteddate,selectedtime);
                                      db.close();
                                  }
                                  catch (SQLException e)
                                  {
                                      Toast.makeText(appointment.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                  }

                                 // Toast.makeText(appointment.this,
                                          //"The appointment was made\n You Can Make Another Appointment", Toast.LENGTH_SHORT).show();
                                  showtoast("The appointment was made\n You Can Make Another Appointment");

                                  Sregion=false;
                                  Scenter=false;
                                  Sdoctor=false;
                                  Sdate=false;
                                  Stime=false;
                                  tvDate.setText("Select Date");
                                  tvTime.setText("Select Time");
                                  spBar.setVisibility(View.GONE);
                                  spMat.setVisibility(View.GONE);
                                  spSab.setVisibility(View.GONE);
                                  spSant.setVisibility(View.GONE);
                                  spTer.setVisibility(View.GONE);
                                  spDoc.setVisibility(View.GONE);
                                  btnDoc.setVisibility(View.GONE);
                                  btnCen.setVisibility(View.GONE);

                              } else
                                  {
                                      Toast.makeText(appointment.this, "Please set The Time", Toast.LENGTH_SHORT).show();
                                  }


                          } else{

                              Toast.makeText(appointment.this, "Please Enter The Date", Toast.LENGTH_SHORT).show();
                          }
                      }
                      else
                          {
                              Toast.makeText(appointment.this,
                                      "Please Select The Doctor", Toast.LENGTH_SHORT).show();
                          }

                    }
                    else 
                        {
                            Toast.makeText(appointment.this,
                                    "Please Select The Center", Toast.LENGTH_SHORT).show();
                        }
                   
                }
                else
                {
                    Toast.makeText(appointment.this,
                            "please select the city", Toast.LENGTH_SHORT).show();
                }



            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(appointment.this,appointmentlistActivity.class);
                startActivity(intent);
            }
        });







    }

    public void showtoast(String message)
    {
        View toastview=getLayoutInflater().inflate(R.layout.toast,(ViewGroup)findViewById(R.id.linlay));
        TextView tvToast=(TextView) toastview.findViewById(R.id.tvToast);
        tvToast.setText(message);
        Toast toast=new Toast(appointment.this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastview);
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.FILL_HORIZONTAL,0,-10);
        toast.show();

    }




}
