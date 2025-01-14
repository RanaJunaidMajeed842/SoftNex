package com.example.softnextv0;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class jobs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        ImageView navHome = findViewById(R.id.nav_home);
        ImageView navJobs = findViewById(R.id.nav_jobs);
        ImageView navChat = findViewById(R.id.nav_chats);
        navHome.setOnClickListener(v -> startActivity(new Intent(jobs.this, home.class)));
        navJobs.setOnClickListener(v -> startActivity(new Intent(jobs.this, jobs.class)));
        navChat.setOnClickListener(v -> startActivity( new Intent(jobs.this , chat.class)));
    }
}
