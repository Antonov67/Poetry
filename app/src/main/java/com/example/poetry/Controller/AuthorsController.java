package com.example.poetry.Controller;


import android.util.Log;

import com.example.poetry.Model.Author;
import com.example.poetry.Model.Authors;
import com.example.poetry.View.IntView;

import java.util.List;

public class AuthorsController implements IntAuthorsController, Authors.MyCallBackAuthors, Author.MyCallBackAuthor{

    IntView authorsView;

    public AuthorsController(IntView view) {
        this.authorsView = view;
    }

    @Override
    public void data(String searchAuthor) {
        if (searchAuthor.length() == 0){
            Authors authors = new Authors();
            authors.registerCallBack(this::callingBack);
            authors.doSomething();
        }else {
            Author author = new Author(searchAuthor);
            author.registerCallBack(this::callingBack);
            author.doSomething();
        }
    }


    @Override
    public void callingBack(List<String> list) {
        authorsView.setData(list);
    }
}
