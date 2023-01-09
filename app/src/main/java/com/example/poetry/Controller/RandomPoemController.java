package com.example.poetry.Controller;

import com.example.poetry.Model.RandomPoem;
import com.example.poetry.View.IntView;

import java.util.List;

public class RandomPoemController implements IntRandomPoemController, RandomPoem.MyCallBackRandomPoem{

    IntView randomPoemView;

    public RandomPoemController(IntView randomPoemView) {
        this.randomPoemView = randomPoemView;
    }

    @Override
    public void data() {
        RandomPoem randomPoem = new RandomPoem();
        randomPoem.registerCallBack(this::callingBack);
        randomPoem.doSomething();
    }

    @Override
    public void callingBack(List<RandomPoem> list) {
        randomPoemView.setData(list);
    }
}
