package com.example.poetry.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.poetry.Controller.IntRandomPoemController;
import com.example.poetry.Controller.RandomPoemController;
import com.example.poetry.Model.RandomPoem;
import com.example.poetry.R;
import com.example.poetry.View.IntView;

import java.util.List;

public class RandomPoemActivity extends AppCompatActivity implements IntView<List<RandomPoem>> {

    TextView author, title, poem, lineCounts;

    IntRandomPoemController randomPoemController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_poem);

        author = findViewById(R.id.random_author);
        title = findViewById(R.id.random_title);
        poem = findViewById(R.id.random_poem);
        lineCounts = findViewById(R.id.random_linecount);

        randomPoemController = new RandomPoemController(this);
        randomPoemController.data();
    }

    @Override
    public void setData(List<RandomPoem> poems) {

        title.setText(poems.get(0).getTitle());
        if (poems.get(0).getLines() != null){
            author.setText(poems.get(0).getAuthor());
            lineCounts.setText("количество строк: " + poems.get(0).getLinecount());
            StringBuilder stringBuilder = new StringBuilder();
            for (String str: poems.get(0).getLines()) {
                stringBuilder.append(str).append("\n");
            }
            poem.setText(stringBuilder);
        }

    }
}