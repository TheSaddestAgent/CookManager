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

        dish = new Dish();
        dish.strURL = "https://www.allrecipes.com/thmb/G1L_0_o8euNngYcOuX3BUChWp7w=/0x512/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/7968859_Green-Lentil-Gumbo-Soup_Chef-John_4x3-060776d1bb394ba0a08ebc05d1a4e90b.jpg";
        dish.name = "Gumbo Soup";
        dish.ingredients_list = "• 8 ounces andouille sausage, cut into 1/4-inch cubes\n\n" +
                "• 2 tablespoons olive oil\n\n" +
                "• 1 cup diced onion\n\n" +
                "• 1 cup diced green bell pepper\n\n" +
                "• 2/3 cup diced celery\n\n" +
                "• 1/4 teaspoon kosher salt, plus more to taste\n\n" +
                "• 1 teaspoon paprika\n\n" +
                "• 1 teaspoon ground cumin\n\n" +
                "• 1/2 teaspoon freshly ground black pepper\n\n" +
                "• 1/2 teaspoon dried thyme\n\n" +
                "• 1/4 teaspoon dried oregano\n\n" +
                "• 1 bay leaf\n\n" +
                "• 6 cups no-salt or low-sodium chicken broth\nv" +
                "• 8 ounces chicken thighs, cut into small pieces\n\n" +
                "• 4 ounces smoked ham, diced\n\n" +
                "• 3/4 cup sliced fresh or frozen okra\n\n" +
                "• 1 cup green lentils, rinsed\n\n" +
                "• 1 teaspoon Worcestershire sauce\nv" +
                "• 1/2 teaspoon Louisiana-style hot sauce\n\n" +
                "• 1/4 cup chopped Italian parsley\n\n" +
                "• 1/3 cup sliced green onions\n\n"  +
                "• 2 cups cooked white rice (optional)\n\n" +
                "• 1/3 cup sliced green onions, or as needed\n\n" +
                "• 1/8 teaspoon cayenne pepper, or to taste\n\n";
        dish.steps_list = "1) Add andouille to a dry soup pot and place over medium heat. Cook, stirring, until sausage begins to brown, a few minutes.\n\n" +
                "2) Add all the olive oil, unless a lot of fat was rendered from the sausage, in which case adjust the amount of oil added.\n\n" +
                "3) Add onion, green pepper, celery, and 1/4 teaspoon salt. Cook, stirring, until onions turn translucent, about 5 minutes.\n\n" +
                "4) Add paprika, cumin, black pepper, thyme, oregano, and bay leaf. Cook, stirring, for 2 minutes more.\n\n" +
                "5) Stir in broth, raise heat to high, and bring to a simmer. Reduce heat to medium low and stir in chicken, ham, and sliced okra.\n\n" +
                "6) Cook for 10 minutes, then stir in lentils. Simmer soup, stirring occasionally, until lentils are tender, about 1 hour.\n\n" +
                "7) Stir in Worcestershire sauce, hot sauce, parsley, and green onions, and cook for another few minutes. Season to taste with salt. Serve with rice, more green onions, and a shake of cayenne.\n\n";
        gson = new Gson();
        json = gson.toJson(dish);
        prefsEditor.putString("Gumbo Soup", json);

        dish = new Dish();
        dish.strURL = "https://www.allrecipes.com/thmb/9KDTW_QiOSvcPtSmYDoMvLZuYrg=/0x512/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/161586-papa-drexlers-bavarian-pretzels-DDMFS-001-4x3-4042ba41a6fa401e82aea394fa612f23.jpg";
        dish.name = "Bavarian Pretzels";
        dish.ingredients_list = "• 8 ounces andouille sausage, cut into 1/4-inch cubes\n\n" +
                "• 3 1/2 cups all-purpose flour, divided, or more as needed\n\n" +
                "• 1 tablespoon active dry yeast\n\n" +
                "• 1 teaspoon white sugar\n\n" +
                "• 1 ⅓ cups water\n\n" +
                "• 2 tablespoons butter, softened\n\n" +
                "• ¼ teaspoon salt\n\n" +
                "• 3 tablespoons baking soda\n\n" +
                "• 2 tablespoons butter, melted\n\n" +
                "• 1 tablespoon coarse sea salt, or to taste\n\n";
        dish.steps_list = "1) Whisk 1 cup flour, yeast, and sugar together in a bowl. Stir in water and softened butter until well combined. Let stand until bubbles begin to form, about 15 minutes.\n\n" +
                "2) Stir salt into yeast mixture, then gradually stir in 2 1/2 cups flour until dough can be picked up in a ball.\n\n" +
                "3) Knead until smooth and elastic, about 8 minutes, adding more flour if needed.\n\n" +
                "4) Divide dough into 6 pieces and let rest for a few minutes.\n\n" +
                "5) Divide dough into 6 pieces and let rest for a few minutes.\n\n" +
                "6) Preheat the oven to 450 degrees F (220 degrees C). Grease a baking sheet.\n\n" +
                "7) Bring 3 cups water to a boil in a pot. Stir in baking soda and remove from the heat.\n\n" +
                "8) Dip pretzels into the water bath for about 45 seconds, flipping over about halfway through.\n\n" +
                "9) Transfer soaked pretzels to the prepared baking sheet; brush with melted butter and sprinkle with salt.\n\n" +
                "10) Bake in the preheated oven until golden brown, 8 to 10 minutes.\n\n";
        gson = new Gson();
        json = gson.toJson(dish);
        prefsEditor.putString("Bavarian Pretzels", json);

        dish = new Dish();
        dish.name = "Baklava";
        dish.strURL = "https://www.allrecipes.com/thmb/Ms72N88Kcd8ew0_Yv_LFHiQQ8hk=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/7495495-easy-baklava-atk-1x1-1-5ff8675aa73f4c4bbb221dc84b77b98b.jpg";
        dish.ingredients_list = "• 1 pound chopped nuts\n\n" + "• 1 teaspoon ground cinnamon\n\n" + "• 1 (16 ounce) package phyllo dough\n\n" +
        "• 1 cup butter, melted\n\n" + "• 1 cup white sugar\n\n" + "• 1 cup water\n\n" + "• ½ cup honey\n\n" + "• 1 teaspoon vanilla extract\n\n" + "• 1 teaspoon grated lemon zest\n\n";
        dish.steps_list = "1) Preheat the oven to 350 degrees F (175 degrees C). Butter a 9x13-inch baking dish.\n\n" +
                "2) Toss together nuts and cinnamon. Unroll phyllo and cut the whole stack in half to fit the dish. Cover phyllo with a damp cloth while assembling the baklava, to keep it from drying out.\n\n" +
                "3) Place 2 sheets of phyllo in the bottom of the prepared dish. Brush generously with some of the melted butter. Sprinkle 2 to 3 tablespoons of the nut mixture on top. Repeat layers until all ingredients are used, ending with about 6 sheets of phyllo.\n\n" +
                "4) Using a sharp knife, cut baklava into 4 long rows, then diagonally 9 times to make 36 diamond shapes. Be sure to cut all the way through to the bottom of the layers.\n\n" + "" +
                "5) Bake in the preheated oven until golden brown and crisp, about 50 minutes.\n\n" +
                "6) While baklava is baking, combine sugar and water in a small saucepan over medium heat and bring to a boil. Stir in honey, vanilla, and lemon zest; reduce heat and simmer 20 minutes.\n\n" +
                "7) Remove baklava from the oven and immediately spoon syrup over it. Let cool completely before serving. Store uncovered.\n\n";
        gson = new Gson();
        json = gson.toJson(dish);
        prefsEditor.putString("Baklava", json);

        prefsEditor.apply();
    }
    int tt = 1, c = 5;
    Dish[] makeDishes() {
        Dish[] arr = new Dish[10];

        for (int i = 0; i < arr.length; i++) {
            String New_Dish = null;
            if(tt % c == 0) New_Dish = "Fried Chicken";
            if(tt % c == 1) New_Dish = "Wheat Bread";
            if(tt % c == 2) New_Dish = "Gumbo Soup";
            if(tt % c == 3) New_Dish = "Bavarian Pretzels";
            if(tt % c == 4) New_Dish = "Baklava";
            tt++;
            tt %= c;
            sharedPreferences = getSharedPreferences("DISHES", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString(New_Dish, "");
            Dish dish = gson.fromJson(json, Dish.class);
            arr[i] = dish;
        }
        return arr;
    }
}