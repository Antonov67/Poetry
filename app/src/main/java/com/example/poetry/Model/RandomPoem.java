package com.example.poetry.Model;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class RandomPoem implements IntRandomPoem{

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


    private List<RandomPoem> poems;

    @Override
    public List<RandomPoem> getPoems() {
        return poems;
    }

    @Override
    public void setPoems(List<RandomPoem> list) {
        poems = list;
    }


    public interface MyCallBackRandomPoem{
        void callingBack(List<RandomPoem> list);
    }

    MyCallBackRandomPoem myCallBack;

    public void registerCallBack(MyCallBackRandomPoem myCallBack){
        this.myCallBack = myCallBack;
    }

    public void doSomething(){
        ResponseThread responseThread = new ResponseThread();
        responseThread.execute();
    }

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

    class ResponseThread extends AsyncTask<Void, Void, List<RandomPoem>> {

        @Override
        protected List<RandomPoem> doInBackground(Void... voids) {
            PoetryDBService service = RetrofitConnection.getInstance().getRetrofit().create(PoetryDBService.class);
            Call<List<RandomPoem>> call = service.getRandomPoem();

            List<RandomPoem> list;
            poems = new ArrayList<>();
            try {
                list = call.execute().body();

                poems.addAll(list);

            } catch (IOException e) {

                RandomPoem poem = new RandomPoem();
//                poem.setTitle("ошибка при получении данных" + "\n" + "проверьте интернет-соединение");
                poem.setTitle("ошибка при получении данных, проверьте интернет-соединение");
                poems.add(poem);
                e.printStackTrace();
            }

            return poems;
        }

        @Override
        protected void onPostExecute(List<RandomPoem> poems) {
            myCallBack.callingBack(poems);
        }
    }

}
