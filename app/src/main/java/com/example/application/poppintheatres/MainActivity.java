package com.example.application.poppintheatres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button beginButton = findViewById(R.id.beginButton);

        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void openFacebook(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
        startActivity(intent);
    }

    public void openTwitter(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com"));
        startActivity(intent);
    }

    public void openLinkedIn(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com"));
        startActivity(intent);
    }
    public void openInstagram(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com"));
        startActivity(intent);
    }
}
