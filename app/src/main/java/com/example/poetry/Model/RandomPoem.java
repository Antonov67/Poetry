package com.example.poetry.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RandomPoem {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("lines")
    @Expose
    public List<String> lines = null;
    @SerializedName("linecount")
    @Expose
    public String linecount;

}
