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
        dish.ingredients_list = "• 1 chicken, cut into pieces\n\n • 1 cup buttermilk\n\n • 2 cups all-purpose flour for coating\n\n" +
                "• 1 teaspoon paprika\n\n • salt and pepper to taste\n\n • 2 quarts vegetable oil for frying";
        dish.steps_list = "1. Take your cut up chicken pieces and skin them if you prefer.\n\n" +
                "2. Put the flour in a large plastic bag (let the amount of chicken you are cooking dictate the amount of flour you use). Season the flour with paprika, salt and pepper to taste (paprika helps to brown the chicken).\n\n" +
                "3. Dip chicken pieces in buttermilk then, a few at a time, put them in the bag with the flour, seal the bag and shake to coat well.\n\n" +
                "4. Place the coated chicken on a cookie sheet or tray, and cover with a clean dish towel or waxed paper. \n\n" +
                "5. Fill a large skillet (cast iron is best) about 1/3 to 1/2 full with vegetable oil. Heat until VERY hot.\n\n" +
                "6. Put in as many chicken pieces as the skillet can hold. Brown the chicken in HOT oil on both sides.\n\n" +
                "7. When browned, reduce heat and cover skillet; let cook for 30 minutes (the chicken will be cooked through but not crispy). Remove cover, raise heat again, and continue to fry until crispy.\n\n" +
                "8. Drain the fried chicken on paper towels. Depending on how much chicken you have, you may have to fry in a few shifts. Keep the finished chicken in a slightly warm oven while preparing the rest.\n";
        Gson gson = new Gson();
        String json = gson.toJson(dish);
        prefsEditor.putString("Fried Chicken", json);
        prefsEditor.apply();

        dish = new Dish();
        dish.strURL = "https://www.allrecipes.com/thmb/8pVxYEFN8ksXOLb7h42xMA3Mwc4=/0x512/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/AR-6773-simple-whole-wheat-bread-DDMFS-4x3-B-969e7bce922948959cb9e85aa4b2ff0d.jpg";
        dish.name = "Wheat Bread";
        dish.ingredients_list = "•3 cups warm water (110 degrees F/45 degrees C)\n" +
                "\n" +
                "•2 (.25 ounce) packages active dry yeast\n" +
                "\n" +
                "•⅔ cup honey, divided\n" +
                "\n" +
                "•5 cups bread flour\n" +
                "\n" +
                "•5 tablespoons butter, melted, divided\n" +
                "\n" +
                "•1 tablespoon salt\n" +
                "\n" +
                "•4 cups whole wheat flour, or more as needed";
        dish.steps_list = "1. Mix warm water, yeast, and 1/3 cup honey in a large bowl to dissolve.\n\n" +
                "2. Add 5 cups bread flour, and stir to combine.\n\n" +
                "3. Let sit for 30 minutes, or until big and bubbly.\n\n" +
                "4. Mix in 3 tablespoons melted butter, remaining 1/3 cup honey, and salt. Stir in 2 cups whole wheat flour.\n\n" +
                "5. Transfer dough to a floured work surface and gradually knead in remaining 2 cups whole wheat flour.\n\n" +
                "6. Knead until dough starts to pull away from the work surface, adding more whole wheat flour if necessary; dough should be a bit tacky to the touch, but not too sticky.\n\n" +
                "7. Place in a greased bowl, turning once to coat the surface of the dough.\n\n" +
                "8. Cover with a dish towel and let rise in a warm place until doubled, 45 minutes to 1 hour.\n\n" +
                "9. Grease three 9x5-inch loaf pans. Punch down the dough, and divide it into 3 loaves.\n\n" +
                "10. Place in the prepared loaf pans, and allow to rise until dough has topped the pans by one inch, another 45 minutes to 1 hour.\n\n" +
                "11. Meanwhile, preheat the oven to 350 degrees F (175 degrees C). Bake the risen loaves in the preheated oven until golden brown for 25 to 30 minutes, do not overbake.\n\n" +
                "12. Lightly brush the tops of the loaves with remaining 2 tablespoons melted butter when done to prevent crust from getting hard.\n\n";
        ;
        gson = new Gson();
        json = gson.toJson(dish);
        prefsEditor.putString("Wheat Bread", json);
        prefsEditor.apply();
    }
    int tt = 1;
    Dish[] makeDishes() {
        Dish[] arr = new Dish[10];

        for (int i = 0; i < arr.length; i++) {
            String New_Dish;
            if(tt % 2 == 1) New_Dish = "Fried Chicken";
            else New_Dish = "Wheat Bread";
            tt++;
            tt %= 2;
            sharedPreferences = getSharedPreferences("DISHES", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString(New_Dish, "");
            Dish dish = gson.fromJson(json, Dish.class);
            arr[i] = dish;
        }
        return arr;
    }
}