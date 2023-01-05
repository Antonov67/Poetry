package com.example.poetry.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PoemsByAuthor {
    @SerializedName("title")
    @Expose
    private String poemTitle;

    @SerializedName("lines")
    @Expose
    public List<String> lines = null;


    public String getPoemTitle() {
        return poemTitle;
    }

    public String getPoemText(){
        return lines.toString();
    }

}
