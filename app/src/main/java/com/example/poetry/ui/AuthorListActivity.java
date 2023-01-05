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

import com.example.poetry.Controller.AuthorsController;
import com.example.poetry.Controller.IntAuthorsController;
import com.example.poetry.R;
import com.example.poetry.View.IntAuthorsView;

import java.util.ArrayList;
import java.util.List;

public class AuthorListActivity extends AppCompatActivity implements IntAuthorsView {



    EditText author_search_field;
    TextView title_author_list;
    Button button_search_author;

    ListView author_list_view;
    List<String> authorList = new ArrayList<>();

    ArrayAdapter adapter;

    IntAuthorsController authorsController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_list);

        authorsController = new AuthorsController(this);

        author_list_view = findViewById(R.id.author_list);
        title_author_list = findViewById(R.id.title_author_list);
        author_search_field = findViewById(R.id.author_search_field);
        button_search_author = findViewById(R.id.button_search_author);



        button_search_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    authorsController.authors(author_search_field.getText().toString());

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
    public void setAuthors(List<String> list) {
        authorList.clear();
        authorList.addAll(list);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        author_list_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}