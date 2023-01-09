package com.example.poetry.Controller;

import com.example.poetry.Model.PoemsByAuthor;
import com.example.poetry.View.IntView;

import java.util.List;

public class PoemsByAuthorController implements IntAuthorsController, PoemsByAuthor.MyCallBackPoem {

    IntView poemsByAuthorView;

    public PoemsByAuthorController(IntView poemsByAuthorView) {
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
        poemsByAuthorView.setData(list);
    }
}
