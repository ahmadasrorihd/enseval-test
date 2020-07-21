package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView tvEmail, tvGreeting, tvTime, tvEmailName;
    Button btnLogout;
    String emailName;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        tvEmail = findViewById(R.id.tv_email);
        tvGreeting = findViewById(R.id.tv_greeting);
        tvTime = findViewById(R.id.tv_time);
        tvEmailName = findViewById(R.id.tv_email_name);
        btnLogout = findViewById(R.id.btn_logout);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        emailName = email.substring(email.lastIndexOf("@")+1);
        tvEmail.setText("Email : "+email);
        tvEmailName.setText("Domain Email : "+emailName);

        if(timeOfDay >= 0 && timeOfDay < 12){
            tvGreeting.setText("Selamat Pagi");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            tvGreeting.setText("Selamat Siang");
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            tvGreeting.setText("Selamat Sore");
        }else if(timeOfDay >= 21 && timeOfDay < 24){
        tvGreeting.setText("Selamat Malam");
        }

        Calendar jam = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        String hasilJam = dateFormat.format(jam.getTime());
        tvTime.setText("Sekarang Jam : "+hasilJam);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Konfirmasin");
                builder.setMessage("Apakah anda yakin ingin keluar ?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Klik lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }
}