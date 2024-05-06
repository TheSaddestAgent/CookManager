package com.example.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.net.URL;

public class ItemActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView tv_dish_name, tv_ingredients_list, tv_steps_list;
    ImageView iv_dishImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        tv_dish_name = findViewById(R.id.tv_dish_name);
        tv_ingredients_list = findViewById(R.id.tv_ingredients_list);
        tv_steps_list = findViewById(R.id.tv_steps_list);
        iv_dishImage = findViewById(R.id.iv_dishImage);

        String str_dish_name = getIntent().getStringExtra("DISH_NAME");

        sharedPreferences = getSharedPreferences("DISHES", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(str_dish_name, ""); // suppose str_dish_name = Fried Chicken (as a sample)
        Dish dish = gson.fromJson(json, Dish.class);
        tv_dish_name.setText(dish.name);
        tv_ingredients_list.setText(dish.ingredients_list);
        tv_steps_list.setText(dish.steps_list);
        String strURL = dish.strURL;
        @SuppressLint("HandlerLeak") Handler imageHandler = new Handler(){
            public void handleMessage(Message msg) {
                if(msg.obj instanceof Bitmap){
                    iv_dishImage.setImageBitmap((Bitmap)msg.obj);
                }
            }
        };
        new Thread(() -> {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(new URL(strURL).openStream());
                Message msg = new Message();
                msg.obj = bitmap;
                imageHandler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}