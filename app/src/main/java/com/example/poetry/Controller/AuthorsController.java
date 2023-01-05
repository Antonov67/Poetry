package com.example.poetry.Controller;


import com.example.poetry.Model.Author;
import com.example.poetry.Model.Authors;
import com.example.poetry.View.IntAuthorsView;
import java.util.List;

public class AuthorsController implements IntAuthorsController, Authors.MyCallBack{

    IntAuthorsView authorsView;

    public AuthorsController(IntAuthorsView authorsView) {
        this.authorsView = authorsView;
    }

    @Override
    public void authors(String searchAuthor) {
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
        authorsView.setAuthors(list);
    }
}
