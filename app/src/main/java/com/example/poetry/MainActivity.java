package com.example.poetry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poetry.Controller.AuthorsController;
import com.example.poetry.Controller.IntAuthorsController;
import com.example.poetry.Model.Author;
import com.example.poetry.Model.Authors;
import com.example.poetry.Model.PoetryDBService;
import com.example.poetry.Model.RetrofitConnection;
import com.example.poetry.View.IntAuthorsView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements IntAuthorsView {



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
        setContentView(R.layout.activity_main);

        authorsController = new AuthorsController(this);

        author_list_view = findViewById(R.id.author_list);
        title_author_list = findViewById(R.id.title_author_list);
        author_search_field = findViewById(R.id.author_search_field);
        button_search_author = findViewById(R.id.button_search_author);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, authorList);
        author_list_view.setAdapter(adapter);

        button_search_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (author_search_field.getText().toString().length() != 0){
                    authorsController.authors(author_search_field.getText().toString());
                }else{
                    authorsController.authors("");
                }

            }
        });


    }

    @Override
    public void setAuthors(List<String> list) {
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        author_list_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}