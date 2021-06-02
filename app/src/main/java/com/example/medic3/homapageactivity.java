package com.example.medic3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class homapageactivity extends AppCompatActivity {

    ImageView ivAppointment, ivReminder,ivPanic,ivTracker;
    TextView tvAppointment, tvAppointment1, tvReminder,tvReminder1,tvTracker,tvTracker1,tvPanic,tvPanic1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homapageactivity);
        ivAppointment=findViewById(R.id.ivAppointment);
        ivReminder=findViewById(R.id.ivReminder);
        ivPanic=findViewById(R.id.ivPanic);
        ivTracker=findViewById(R.id.ivTracker);

        tvAppointment=findViewById(R.id.tvAppointment);
        tvAppointment1=findViewById(R.id.tvAoointment1);
        tvReminder=findViewById(R.id.tvReminder);
        tvReminder1=findViewById(R.id.tvReminder1);
        tvPanic=findViewById(R.id.tvPaniv);
        tvPanic1=findViewById(R.id.tvPanic1);
        tvTracker=findViewById(R.id.tvTracker);
        tvTracker1=findViewById(R.id.tvTracker1);


        ivAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homapageactivity.this, appointment.class));
            }
        });

        tvAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homapageactivity.this, appointment.class));
            }
        });

        tvAppointment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homapageactivity.this, appointment.class));
            }
        });

        ivReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homapageactivity.this, pillreminer.class));

            }
        });

        tvReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homapageactivity.this, pillreminer.class));

            }
        });

        tvReminder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homapageactivity.this, pillreminer.class));

            }
        });

        ivTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homapageactivity.this, trackerpage.class));

            }
        });

        tvTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homapageactivity.this, trackerpage.class));

            }
        });

        tvTracker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homapageactivity.this, trackerpage.class));

            }
        });

        ivPanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new  Intent(Intent.ACTION_DIAL, Uri.parse("tel:0697388602")));

            }
        });

        tvPanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new  Intent(Intent.ACTION_DIAL, Uri.parse("tel:0697388602")));

            }
        });

        tvPanic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new  Intent(Intent.ACTION_DIAL, Uri.parse("tel:0697388602")));

            }
        });

    }
}
