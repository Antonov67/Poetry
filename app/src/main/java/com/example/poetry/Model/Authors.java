package com.example.poetry.Model;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class Authors implements IntAuthors {



    @SerializedName("authors")
    @Expose
    private List<String> authorList = null;

    public interface MyCallBackAuthors{
        void callingBack(List<String> list);
    }

    MyCallBackAuthors myCallBack;

    public void registerCallBack(MyCallBackAuthors myCallBack){
        this.myCallBack = myCallBack;
    }

    public void doSomething(){
        ResponseThread responseThread = new ResponseThread();
        responseThread.execute();
    }

    @Override
    public List<String> getAuthors() {
            return authorList;
    }

    public void setAuthors(List<String> authorList) {
        this.authorList = authorList;
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
              authors = new Authors();
              List<String> list = new ArrayList<>();
              list.add("ошибка при получении данных");
              list.add("проверьте интернет-соединение");
              authors.setAuthors(list);
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

