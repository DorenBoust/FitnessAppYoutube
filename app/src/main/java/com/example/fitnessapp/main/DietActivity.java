package com.example.fitnessapp.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.keys.KeysIntents;
import com.example.fitnessapp.user.Meal;
import com.example.fitnessapp.user.Product;
import com.example.fitnessapp.user.ProductDataBase;
import com.example.fitnessapp.user.User;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.List;

public class DietActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        textView = findViewById(R.id.product_title_diel);

        Intent intent = getIntent();
        Meal meal = (Meal) intent.getSerializableExtra(KeysIntents.DIET_DATA);
        User user = (User) intent.getSerializableExtra(KeysIntents.SEND_USER);

        List<Product> products = meal.getProducts();
        String productName = products.get(0).getProductName();

        Dictionary<String, ProductDataBase> productDataBase = user.getProductDataBase();

        ProductDataBase productDataBase1 = productDataBase.get(productName);

        textView.setText(productDataBase1.getProductNameHEB());



    }
}
