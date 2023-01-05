package com.example.poetry.Model;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.poetry.MainActivity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Authors implements IntAuthors {



    @SerializedName("authors")
    @Expose
    private List<String> authorList = null;

    public interface MyCallBack{
        void callingBack(List<String> list);
    }

    MyCallBack myCallBack;

    public void registerCallBack(MyCallBack myCallBack){
        this.myCallBack = myCallBack;
    }

    public void doSomething(){
        ResponseThread responseThread = new ResponseThread();
        responseThread.execute();
    }


    public List<String> getAuthors() {
            return authorList;
    }

  class ResponseThread extends AsyncTask<Void, Void, List<String>>{

      @Override
      protected List<String> doInBackground(Void... voids) {
          PoetryDBService service = RetrofitConnection.getInstance().getRetrofit().create(PoetryDBService.class);
          Call<Authors> call = service.getAuthorsList(); // общий список авторов
          Authors authors = null;
          try {
              authors = call.execute().body();
          } catch (IOException e) {
              e.printStackTrace();
          }

          return authors.getAuthors();
      }

      @Override
      protected void onPostExecute(List<String> strings) {
         myCallBack.callingBack(strings);
      }
  }


}

