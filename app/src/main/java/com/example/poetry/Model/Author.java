package com.example.poetry.Model;

import com.google.gson.annotations.SerializedName;

public class Author{

    @SerializedName("author")
    private String name;


    public String getName() {
        return name;
    }

}
