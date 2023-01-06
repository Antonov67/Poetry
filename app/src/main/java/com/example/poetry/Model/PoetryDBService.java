package com.example.poetry.Model;

import com.example.poetry.Model.Author;
import com.example.poetry.Model.Authors;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PoetryDBService {

    @GET("author")
    Call<Authors> getAuthorsList();

    @GET("author/{str}/author")
    Call<List<Author>> getAuthorsList(@Path("str") String str);

    @GET("author/{str}/title,lines")
    Call<List<PoemsByAuthor>> getPoemsByAuthor(@Path("str") String str);

    @GET("random")
    Call<List<RandomPoem>> getRandomPoem();



}
