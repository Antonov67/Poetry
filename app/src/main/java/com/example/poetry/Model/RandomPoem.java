package com.example.poetry.Model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;


public class RandomPoem{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("lines")
    @Expose
    private List<String> lines = null;
    @SerializedName("linecount")
    @Expose
    private String linecount;


    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getLines() {
        return lines;
    }

    public String getLinecount() {
        return linecount;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
