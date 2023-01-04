package com.example.poetry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Authors {

    @SerializedName("authors")
    @Expose
    public List<String> authorList = null;


}