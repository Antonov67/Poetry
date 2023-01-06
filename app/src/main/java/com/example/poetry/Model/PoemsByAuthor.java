package com.example.poetry.Model;

import android.os.AsyncTask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class PoemsByAuthor implements IntPoemsByAuthor{
    @SerializedName("title")
    @Expose
    private String poemTitle;

    @SerializedName("lines")
    @Expose
    public List<String> lines = null;

    public interface MyCallBackPoem{
        void callingBack(List<PoemsByAuthor> list);
    }
    private String searchAuthor;
    private List<PoemsByAuthor> poems;

    public PoemsByAuthor(String searchAuthor) {
        this.searchAuthor = searchAuthor;
    }

    MyCallBackPoem myCallBack;

    public void registerCallBack(MyCallBackPoem myCallBack){
        this.myCallBack = myCallBack;
    }

    public void doSomething(){
        ResponseThread responseThread = new ResponseThread();
        responseThread.execute();
    }

    public String getPoemTitle() {
        return poemTitle;
    }

    public String getPoemText(){
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : lines) {
            stringBuilder.append(str).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public List<PoemsByAuthor> getPoems() {
        return null;
    }

    class ResponseThread extends AsyncTask<Void, Void, List<PoemsByAuthor>> {

        @Override
        protected List<PoemsByAuthor> doInBackground(Void... voids) {
            PoetryDBService service = RetrofitConnection.getInstance().getRetrofit().create(PoetryDBService.class);
            Call<List<PoemsByAuthor>> call = service.getPoemsByAuthor(searchAuthor);

            List<PoemsByAuthor> list;
            poems = new ArrayList<>();
            try {
                list = call.execute().body();
                poems.addAll(list);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return poems;
        }

        @Override
        protected void onPostExecute(List<PoemsByAuthor> poems) {
            myCallBack.callingBack(poems);
        }
    }
}
