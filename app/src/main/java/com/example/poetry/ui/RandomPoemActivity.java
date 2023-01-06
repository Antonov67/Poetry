package com.example.poetry.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.poetry.Model.RandomPoem;
import com.example.poetry.R;
import com.example.poetry.View.IntRandomPoemView;

import java.util.List;

public class RandomPoemActivity extends AppCompatActivity implements IntRandomPoemView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_poem);
    }

    @Override
    public void setRandomPoem(List<RandomPoem> poems) {

    }
}