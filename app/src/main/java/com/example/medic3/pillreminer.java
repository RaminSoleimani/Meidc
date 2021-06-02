package com.example.medic3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class pillreminer extends AppCompatActivity {
    EditText etPillname;
    TextView tvStarttime, tvDuration,test;
    Button btnIntime, btnDuration, btnAdd, btnTracker, btnAppointment;
    Context mcontext=this;

    private String startdata;
    private String startarray[];
    private int starthour;
    private int startminute;

    private String durationdata;
    private String durationarray[];
    private int durationthour;
    private int durationminute;

    private String pillname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createnotificationchanel();

        setContentView(R.layout.activity_pillreminer);
        etPillname=(EditText) findViewById(R.id.etPillname);
        tvStarttime=(TextView) findViewById(R.id.tvStartime);
        tvDuration=(TextView) findViewById(R.id.tvDuration);
        btnIntime=(Button) findViewById(R.id.btnSetInTime);
        btnDuration=(Button) findViewById(R.id.btnSetDuration);
        btnAdd=(Button) findViewById(R.id.btnAdd);
        test=(TextView)findViewById(R.id.test);

        btnTracker=findViewById(R.id.btntraker);
        btnAppointment=findViewById(R.id.btntAppointment);
        Calendar startCalender=Calendar.getInstance();
        final int hourstart=startCalender.get(Calendar.HOUR_OF_DAY);
        final int minutestrat=startCalender.get(Calendar.MINUTE);
        Calendar durationCalender=Calendar.getInstance();
        final int hourDuration=durationCalender.get(Calendar.HOUR_OF_DAY);
        final int minuteDuration=durationCalender.get(Calendar.MINUTE);


        pillname=etPillname.getText().toString();



        btnIntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog=new TimePickerDialog(mcontext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvStarttime.setText(hourOfDay+":"+minute);
                        startdata=tvStarttime.getText().toString();
                        startarray=startdata.split(":");
                        starthour=Integer.parseInt(startarray[0]);
                        startminute=Integer.parseInt(startarray[1]);
                    }
                },hourstart,minutestrat,android.text.format.DateFormat.is24HourFormat(mcontext));
                timePickerDialog.show();


            }
        });

        btnDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog=new TimePickerDialog(mcontext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvDuration.setText(hourOfDay+":"+minute);
                        durationdata=tvDuration.getText().toString();
                        durationarray=durationdata.split(":");
                        durationthour=Integer.parseInt(durationarray[0]);
                        durationminute=Integer.parseInt(durationarray[1]);

                    }
                },hourDuration,minuteDuration,android.text.format.DateFormat.is24HourFormat(mcontext));
                timePickerDialog.show();

            }
        });



        btnTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               startActivity(new Intent(pillreminer.this,Pilllistastivity.class));



            }
        });

        btnAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    pilldatabase db=new pilldatabase(pillreminer.this);
                    db.open();

                    String num=Integer.toString(67876);

                    db.deleteEntery(num);

                    db.close();
                }
                catch (SQLException e)
                {
                    Toast.makeText(pillreminer.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(etPillname.getText().toString().isEmpty()){
                   Toast.makeText(pillreminer.this,"Please enter the name of the pill",Toast.LENGTH_SHORT).show();
               }else if(tvStarttime.getText().toString().equals("Hour:Minute")){

                   Toast.makeText(pillreminer.this,"Please pick the start time",Toast.LENGTH_SHORT).show();

               }else if(tvDuration.getText().toString().equals("Hour:Minute")){
                   Toast.makeText(pillreminer.this,"Please pick the duration time",Toast.LENGTH_SHORT).show();
               }else{
                pillname=etPillname.getText().toString();
                test.setText(pillname+" "+starthour+":"+startminute+"  "+durationthour+":"+durationminute);
                Toast.makeText(pillreminer.this,"the alarm set",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(pillreminer.this, ReminderBroadcast.class);
                Calendar calendar=Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, starthour);
                calendar.set(Calendar.MINUTE, startminute);
                PendingIntent pendingIntent= PendingIntent.getBroadcast(pillreminer.this,0,intent,0);

                AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                Long buttonclicktime=System.currentTimeMillis();
                int tenseconneds=20000;
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        1000 * 60 * durationminute +1000 * 60 * 60 *  hourDuration, pendingIntent);

                // add to data base
                   try
                   {
                       pilldatabase db=new pilldatabase(pillreminer.this);
                       db.open();
                       //db.cleardb();
                       int random=new Random(0).nextInt(10000);
                       Long tsLong = System.currentTimeMillis();
                       Long tslong1=(tsLong/100000)*100000;
                       Long tsLong2=tsLong-tslong1;
                       String ts = tsLong2.toString();


                       String srandom=Integer.toString(random);
                       String s=starthour+" hour"+"-"+startminute+" minute";
                       String d=durationthour+" hour"+"-"+durationminute+" minute";

                       db.createEntery(pillname,s,d,ts);



                       ;

                       db.close();
                   }
                   catch (SQLException e)
                   {
                       Toast.makeText(pillreminer.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                   }
               }





            }


        });
    }
    private void createnotificationchanel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name="LemubitReminderChannel";
            String description="channel for Lemubit Reminder";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel("notifyRaminapp", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

}
