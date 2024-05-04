package com.example.adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends Activity {
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillDishes();
        MyAdapter adapter = new MyAdapter(this, makeDishes());
        ListView lv = findViewById(R.id.listview);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, ItemActivity.class);
            Dish item = (Dish) parent.getAdapter().getItem(position);
            intent.putExtra("DISH_NAME", item.name);
            startActivity(intent);
        });
    }
    void fillDishes(){
        sharedPreferences = getSharedPreferences("DISHES", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

        Dish dish = new Dish();
        dish.strURL = "https://www.allrecipes.com/thmb/s0mJbUaWhT4cvgMRK28grMkUXcU=/0x512/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/8805-CrispyFriedChicken-mfs-3x2-072-d55b8406d4ae45709fcdeb58a04143c2.jpg";
        dish.name = "Fried Chicken";
        dish.ingredients_list = "• 1 chicken, cut into pieces\n • 1 cup buttermilk\n • 2 cups all-purpose flour for coating\n" +
                "• 1 teaspoon paprika\n • salt and pepper to taste\n • 2 quarts vegetable oil for frying";
        dish.steps_list = "1. Take your cut up chicken pieces and skin them if you prefer.\n" +
                "2. Put the flour in a large plastic bag (let the amount of chicken you are cooking dictate the amount of flour you use). Season the flour with paprika, salt and pepper to taste (paprika helps to brown the chicken).\n" +
                "3. Dip chicken pieces in buttermilk then, a few at a time, put them in the bag with the flour, seal the bag and shake to coat well.\n" +
                "4. Place the coated chicken on a cookie sheet or tray, and cover with a clean dish towel or waxed paper. \n" +
                "5. Fill a large skillet (cast iron is best) about 1/3 to 1/2 full with vegetable oil. Heat until VERY hot.\n" +
                "6. Put in as many chicken pieces as the skillet can hold. Brown the chicken in HOT oil on both sides.\n" +
                "7. When browned, reduce heat and cover skillet; let cook for 30 minutes (the chicken will be cooked through but not crispy). Remove cover, raise heat again, and continue to fry until crispy.\n" +
                "8. Drain the fried chicken on paper towels. Depending on how much chicken you have, you may have to fry in a few shifts. Keep the finished chicken in a slightly warm oven while preparing the rest.";
        Gson gson = new Gson();
        String json = gson.toJson(dish);
        prefsEditor.putString("Fried Chicken", json);
        prefsEditor.apply();
    }
    Dish[] makeDishes() {
        Dish[] arr = new Dish[10];

        for (int i = 0; i < arr.length; i++) {
            sharedPreferences = getSharedPreferences("DISHES", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString("Fried Chicken", ""); // suppose str_dish_name = Fried Chicken (as a sample)
            Dish dish = gson.fromJson(json, Dish.class);
            arr[i] = dish;
        }
        return arr;
    }
}
/*
Активность рецепта (разметка заранее)
передаем через intent/shared_preference
 */