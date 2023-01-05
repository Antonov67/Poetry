package com.example.poetry.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetry.R;

public class PoemsActivity extends AppCompatActivity {

    private TextView authorTitle;
    private ViewPager2 viewPager;
    private OnBoardingAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poems);

        authorTitle = findViewById(R.id.author);
        viewPager = findViewById(R.id.view_pager);

        String str = getIntent().getStringExtra("data");
        authorTitle.setText(str);

        adapter = new OnBoardingAdapter();
        viewPager.setAdapter(adapter);

    }
}