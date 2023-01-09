package com.example.poetry.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.poetry.Controller.DataCallController;
import com.example.poetry.Controller.IntDataCallController;
import com.example.poetry.R;
import com.example.poetry.View.IntView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AuthorListActivity extends AppCompatActivity implements IntView<List<String>> {

    EditText author_search_field;
    TextView title_author_list;
    Button button_search_author;
    ListView author_list_view;
    FloatingActionButton actionButton;

    List<String> authorList = new ArrayList<>();

    ArrayAdapter adapter;

    IntDataCallController dataCallController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_list);

        dataCallController = new DataCallController(this);



        author_list_view = findViewById(R.id.author_list);
        title_author_list = findViewById(R.id.title_author_list);
        author_search_field = findViewById(R.id.author_search_field);
        button_search_author = findViewById(R.id.button_search_author);
        actionButton = findViewById(R.id.floatingActionButton);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AuthorListActivity.this, RandomPoemActivity.class));
            }
        });



        button_search_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    dataCallController.data(author_search_field.getText().toString());
            }
        });

        author_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AuthorListActivity.this, PoemsActivity.class);
                intent.putExtra("data", authorList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void setData(List<String> list) {
        authorList.clear();
        authorList.addAll(list);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        author_list_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}