package com.example.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.URL;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Dish> {
    public MyAdapter(Context context, Dish[] arr) {
        super(context, R.layout.activity_item, arr);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Dish dish = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);
        }
        assert dish != null;
        ((TextView) convertView.findViewById(R.id.tv_dish_name)).setText(dish.name);
        ((TextView) convertView.findViewById(R.id.tv_ingredients_title)).setText("Ingredients: ");
        ((TextView) convertView.findViewById(R.id.tv_ingredients_list)).setText(dish.ingredients_list);

        String strURL = dish.strURL;
        ImageView image_view = convertView.findViewById(R.id.iv_dishImage);

        @SuppressLint("HandlerLeak") Handler imageHandler = new Handler(){
            public void handleMessage(Message msg) {
                if(msg.obj instanceof Bitmap){
                    image_view.setImageBitmap((Bitmap)msg.obj);
                }
            };
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

        return convertView;
    }
}
