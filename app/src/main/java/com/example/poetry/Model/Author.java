package com.example.poetry.Model;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.poetry.MainActivity;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Author implements IntAuthors{

    @SerializedName("author")
    private String name;

    private List<String> authors;

    @Override
    public List<String> getAuthors() {
        return authors;
    }

    private String searchAuthor;

    public interface MyCallBack{
        void callingBack(List<String> list);
    }

    Authors.MyCallBack myCallBack;

    public void registerCallBack(Authors.MyCallBack myCallBack){
        this.myCallBack = myCallBack;
    }

    public void doSomething(){
        ResponseThread responseThread = new ResponseThread();
        responseThread.execute();
    }



    public Author(String searchAuthor) {
        this.searchAuthor = searchAuthor;
    }

    public String getName() {
        return name;
    }

    class ResponseThread extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected List<String> doInBackground(Void... voids) {
            PoetryDBService service = RetrofitConnection.getInstance().getRetrofit().create(PoetryDBService.class);
            Call<List<Author>> call = service.getAuthorsList(searchAuthor);

            List<Author> list;
            authors = new ArrayList<>();
            try {
                list = call.execute().body();

                for (Author author: list) {
                   if (!authors.contains(author.getName()))
                    authors.add(author.getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return getAuthors();
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            myCallBack.callingBack(strings);
        }
    }

}
