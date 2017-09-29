package com.nayan.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<MOption> optionArrayList;
    ArrayList<MQuestion> questionArrayList;
    public ArrayList<MCategory> categoryArrayList;
    MQuestion mQuestion;
    MOption mOption;
    public int pos;
    CategoryAdapter adapter;
    private Gson gson;
    DatabaseHelper db;
    private static MainActivity instance;

    public static MainActivity getInstance() {

        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getOnlineData();
        prepareView();
    }

    private void init() {
        gson = new Gson();
        instance=this;
        recyclerView = (RecyclerView) findViewById(R.id.recCategory);
        mOption = new MOption();
        mQuestion = new MQuestion();
        categoryArrayList = new ArrayList<>();
        optionArrayList = new ArrayList<>();
        questionArrayList = new ArrayList<>();
        adapter = new CategoryAdapter(this);
    }

    public void prepareView() {
        adapter.setQues(categoryArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void getOnlineData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://step2code.com/Nayan/question.php", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.e("data", " has " + response.toString());
                super.onSuccess(statusCode, headers, response);
                try {
                    MCategory[] categories = gson.fromJson(response.getJSONArray("category").toString(), MCategory[].class);
                    categoryArrayList = new ArrayList<MCategory>(Arrays.asList(categories));
                    Log.e("data", " has " + categoryArrayList.size());
                    prepareView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
