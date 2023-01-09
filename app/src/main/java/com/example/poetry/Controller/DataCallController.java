package com.example.poetry.Controller;

import com.example.poetry.Model.Author;
import com.example.poetry.Model.Authors;
import com.example.poetry.Model.PoemsByAuthor;
import com.example.poetry.Model.PoetryDBService;
import com.example.poetry.Model.RandomPoem;
import com.example.poetry.Model.RetrofitConnection;
import com.example.poetry.View.IntView;
import com.example.poetry.ui.AuthorListActivity;
import com.example.poetry.ui.PoemsActivity;
import com.example.poetry.ui.RandomPoemActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataCallController implements IntDataCallController{

    private IntView view;

    public DataCallController(IntView view) {
        this.view = view;
    }

    public void data(Object[] t) {
        // запрос данных для случайного стихотворения
        if (view instanceof RandomPoemActivity){
            PoetryDBService service = RetrofitConnection.getInstance().getRetrofit().create(PoetryDBService.class);
            Call<List<RandomPoem>> call = service.getRandomPoem();
            call.enqueue(new Callback<List<RandomPoem>>() {
                @Override
                public void onResponse(Call<List<RandomPoem>> call, Response<List<RandomPoem>> response) {
                    view.setData(response.body());
                }

                @Override
                public void onFailure(Call<List<RandomPoem>> call, Throwable t) {

                }
            });
        }

        if (view instanceof AuthorListActivity){
            // если есть запрос по автору, то ищем по автору, иначе общий список авторов
            if ((t[0].toString().length()) != 0 ){
                PoetryDBService service = RetrofitConnection.getInstance().getRetrofit().create(PoetryDBService.class);
                Call<List<Author>> call = service.getAuthorsList((String) t[0]);
                call.enqueue(new Callback<List<Author>>() {
                    @Override
                    public void onResponse(Call<List<Author>> call, Response<List<Author>> response) {
                        List<String> authors = new ArrayList<>();
                        for (Author author: response.body()) {
                            if (!authors.contains(author.getName()))
                                authors.add(author.getName());
                        }
                        view.setData(authors);
                    }

                    @Override
                    public void onFailure(Call<List<Author>> call, Throwable t) {

                    }
                });
            }
            // общий список авторов
            if ((t[0].toString().length()) == 0) {
                PoetryDBService service = RetrofitConnection.getInstance().getRetrofit().create(PoetryDBService.class);
                Call<Authors> call = service.getAuthorsList();
                call.enqueue(new Callback<Authors>() {
                    @Override
                    public void onResponse(Call<Authors> call, Response<Authors> response) {
                        view.setData(response.body().getAuthors());
                    }

                    @Override
                    public void onFailure(Call<Authors> call, Throwable t) {

                    }
                });
            }


        }

        //запрос поем по автору
        if (view instanceof PoemsActivity){
            PoetryDBService service = RetrofitConnection.getInstance().getRetrofit().create(PoetryDBService.class);
            Call<List<PoemsByAuthor>> call = service.getPoemsByAuthor(t[0].toString());
            call.enqueue(new Callback<List<PoemsByAuthor>>() {
                @Override
                public void onResponse(Call<List<PoemsByAuthor>> call, Response<List<PoemsByAuthor>> response) {
                    view.setData(response.body());
                }

                @Override
                public void onFailure(Call<List<PoemsByAuthor>> call, Throwable t) {

                }
            });
        }


    }
}
