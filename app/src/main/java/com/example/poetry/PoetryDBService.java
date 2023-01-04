package com.example.poetry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PoetryDBService {

    @GET("author")
    Call<Authors> getAuthorsList();

    @GET("author/{str}/author")
    Call<List<Author>> getAuthorsList(@Path("str") String str);



}
