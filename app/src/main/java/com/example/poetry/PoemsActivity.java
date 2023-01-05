package com.example.poetry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class PoemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poems);

        String str = getIntent().getStringExtra("data");
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}