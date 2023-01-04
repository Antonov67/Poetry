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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    static final String BASE_URL = "https://poetrydb.org";

    EditText author_search_field;
    TextView title_author_list;
    Button button_search_author;

    ListView author_list_view;
    List<String> authorList = new ArrayList<>();

    ArrayAdapter adapter;

    Retrofit retrofit;
    PoetryDBService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        author_list_view = findViewById(R.id.author_list);
        title_author_list = findViewById(R.id.title_author_list);
        author_search_field = findViewById(R.id.author_search_field);
        button_search_author = findViewById(R.id.button_search_author);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(PoetryDBService.class);


        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, authorList);
        author_list_view.setAdapter(adapter);


        allAuthors();

        button_search_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (author_search_field.getText().toString().length() != 0){
                    Log.d("777", "поиск по автору");
                    Call<List<Author>> callSearchAuthor = service.getAuthorsList(author_search_field.getText().toString());
                    callSearchAuthor.enqueue(new Callback<List<Author>>() {
                        @Override
                        public void onResponse(Call<List<Author>> call, Response<List<Author>> response) {
                            List<Author> list;
                            list = response.body();
                            authorList.clear();
                            for (Author author: list) {
                                Log.d("777", "1" + author.name);
                                if (!authorList.contains(author.name)){
                                    authorList.add(author.name);
                                }
                            }
                            adapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onFailure(Call<List<Author>> call, Throwable t) {
                            Log.d("777", t.getMessage());
                            Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    allAuthors();
                }

            }
        });

        author_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }

    void allAuthors(){
        Call<Authors> call = service.getAuthorsList(); // общий список авторов
        call.enqueue(new Callback<Authors>() {
            @Override
            public void onResponse(Call<Authors> call, Response<Authors> response) {
                Authors authors;
                authors = response.body();
                for (String str: authors.authorList) {
                    authorList.add(str);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Authors> call, Throwable t) {
                Log.d("777", t.getMessage());
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}