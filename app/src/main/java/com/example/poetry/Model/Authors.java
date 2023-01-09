package com.example.poetry.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Authors {


    @SerializedName("authors")
    @Expose
    private List<String> authorList = null;

    public List<String> getAuthors() {
            return authorList;
    }




}

