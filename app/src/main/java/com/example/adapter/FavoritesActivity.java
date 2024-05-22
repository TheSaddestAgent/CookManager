package com.example.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FavoritesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private SharedPreferences sharedPreferences, sharedFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        sharedPreferences = getSharedPreferences("DISHES", MODE_PRIVATE);
        sharedFavorites = getSharedPreferences("Favorites", MODE_PRIVATE);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activities2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        new FavoritesActivity.DownloadDishesTask().execute();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                Intent intent = new Intent(FavoritesActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class DownloadDishesTask extends AsyncTask<Void, Void, Dish[]> {

        @Override
        protected Dish[] doInBackground(Void... voids) {
            String json = sharedFavorites.getString("Favorites Recipes", "");
            Gson gson = new Gson();

            HashSet<String> d_names = gson.fromJson(json, HashSet.class);

            if(d_names == null){
                Dish[] tmp = new Dish[0];
                return tmp;
            }
            Dish[] arr = new Dish[d_names.size()];

            Iterator<String> iterator = d_names.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                String d_name = iterator.next();
                String url = transform2URL(d_name);
                Dish dish = downloadDishFromUrl(url);
                if (dish != null) {
                    arr[i] = dish;
                    i++;
                }
            }

            return arr;
        }

        @Override
        protected void onPostExecute(Dish[] dishes) {
            super.onPostExecute(dishes);

            MyAdapter adapter = new MyAdapter(FavoritesActivity.this, dishes);
            ListView lv = findViewById(R.id.listview);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(FavoritesActivity.this, ItemActivity.class);
                Dish item = (Dish) parent.getAdapter().getItem(position);
                intent.putExtra("DISH_NAME", item.name);
                startActivity(intent);
            });
        }

        private String transform2URL(String name) {
            String[] arrStr = name.split(" ");
            StringBuilder res = new StringBuilder();
            int n = arrStr.length;
            for (int i = 0; i < n; i++) {
                res.append(arrStr[i]);
                if (i != n - 1) res.append("_");
            }
            return "https://raw.githubusercontent.com/TheSaddestAgent/stuffManager/main/" + res.toString() + ".json";
        }

        private Dish downloadDishFromUrl(String url) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                assert response.body() != null;
                String jsonData = response.body().string();
                return parseJson(jsonData);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private Dish parseJson(String jsonData) {
            Gson gson = new Gson();
            return gson.fromJson(jsonData, Dish.class);
        }
    }

}