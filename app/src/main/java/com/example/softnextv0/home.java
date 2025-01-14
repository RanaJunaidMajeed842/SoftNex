package com.example.softnextv0;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView navHome = findViewById(R.id.nav_home);
        ImageView navJobs = findViewById(R.id.nav_jobs);
        ImageView navChat = findViewById(R.id.nav_chats);
        navHome.setOnClickListener(v -> startActivity(new Intent(home.this, home.class)));
        navJobs.setOnClickListener(v -> startActivity(new Intent(home.this, jobs.class)));
        navChat.setOnClickListener(v -> startActivity( new Intent(home.this , chat.class)));

    }
}
