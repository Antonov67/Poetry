package com.example.poetry.Controller;

import com.example.poetry.Model.PoemsByAuthor;
import com.example.poetry.View.IntPoemsByAuthorView;

import java.util.List;

public class PoemsByAuthorController implements IntAuthorsController, PoemsByAuthor.MyCallBackPoem {

    IntPoemsByAuthorView poemsByAuthorView;

    public PoemsByAuthorController(IntPoemsByAuthorView poemsByAuthorView) {
        this.poemsByAuthorView = poemsByAuthorView;
    }


    @Override
    public void data(String searchAuthor) {
        PoemsByAuthor poemsByAuthor = new PoemsByAuthor(searchAuthor);
        poemsByAuthor.registerCallBack(this::callingBack);
        poemsByAuthor.doSomething();
    }

    @Override
    public void callingBack(List<PoemsByAuthor> list) {
        poemsByAuthorView.setPoems(list);
    }
}
