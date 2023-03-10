package com.example.poetry.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;

import com.example.poetry.Controller.DataCallController;
import com.example.poetry.Controller.IntDataCallController;
import com.example.poetry.Model.PoemsByAuthor;
import com.example.poetry.R;
import com.example.poetry.View.IntView;

import java.util.List;

public class PoemsActivity extends AppCompatActivity implements IntView<List<PoemsByAuthor>> {

    private TextView authorTitle;
    private ViewPager2 viewPager;
    private OnBoardingAdapter adapter;

    IntDataCallController dataCallController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poems);

        authorTitle = findViewById(R.id.author);
        viewPager = findViewById(R.id.view_pager);

        String searchAuthor = getIntent().getStringExtra("data");
        authorTitle.setText(searchAuthor);

        dataCallController = new DataCallController(this);
        dataCallController.data(searchAuthor);

    }

    @Override
    public void setData(List<PoemsByAuthor> list) {
        adapter = new OnBoardingAdapter(list);
        viewPager.setAdapter(adapter);
    }
}