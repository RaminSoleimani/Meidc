package com.example.medic3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button button;
    ImageView ivPillreminder;
    ImageView ivAppointment;
    ImageView ivEmrgency;
    ImageView ivTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.test);
        ivPillreminder=findViewById(R.id.ivPillreminder);
        ivEmrgency=findViewById(R.id.ivEmergencycall);




        ivPillreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,com.example.medic3.pillreminer.class);
                startActivity(intent);

            }
        });

        ivEmrgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0697388602"));
                startActivity(intent);
            }
        });

        ivTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,com.example.medic3.tracker.class);
                startActivity(intent);

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,com.example.medic3.pillreminer.class);
                startActivity(intent);
            }
        });


    }
}
