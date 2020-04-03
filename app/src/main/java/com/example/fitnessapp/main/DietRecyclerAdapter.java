package com.example.fitnessapp.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.fitnessapp.R;
import com.example.fitnessapp.models.CustomMethods;
import com.example.fitnessapp.user.Diet;
import com.example.fitnessapp.user.Meal;
import com.example.fitnessapp.user.NutritionalValuesProduct;
import com.example.fitnessapp.user.Product;
import com.example.fitnessapp.user.ProductDataBase;
import com.example.fitnessapp.user.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DietRecyclerAdapter extends RecyclerView.Adapter<DietRecyclerAdapter.MealHolder>{

    private Diet diet;
    private Dictionary<String, ProductDataBase> productDataBase;
    private LayoutInflater inflater;
    private OnMealLisiner mOnMealLisiner;
    private Context context;


    public DietRecyclerAdapter(Diet diet, User user, LayoutInflater inflater, OnMealLisiner onMealLisiner, Context context) {
        this.diet = diet;
        this.productDataBase = user.getProductDataBase();
        this.inflater = inflater;
        this.mOnMealLisiner = onMealLisiner;
        this.context = context;
    }

    @NonNull
    @Override
    public MealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.diet_fragment_layout,parent,false);

        return new MealHolder(v, mOnMealLisiner);
    }

    @Override
    public void onBindViewHolder(@NonNull MealHolder holder, int position) {
        Meal meal = diet.getMeals().get(position);
        List<Product> products = meal.getProducts();

        List<NutritionalValuesProduct> nutritionalValuesProductList = new ArrayList<>();

        for (Product product : products) {

            ProductDataBase productDataBase = this.productDataBase.get(product.getProductName());

            NutritionalValuesProduct productNut = CustomMethods.getProductNut(productDataBase, product);

            nutritionalValuesProductList.add(productNut);
        }


        int cal = 0;
        int pro = 0;
        int carboh = 0;
        int fat = 0;

        for (NutritionalValuesProduct nutritionalValuesProduct : nutritionalValuesProductList) {

            cal += nutritionalValuesProduct.getCal();
            pro += nutritionalValuesProduct.getPro();
            carboh += nutritionalValuesProduct.getCarboh();
            fat += nutritionalValuesProduct.getFat();


        }


        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String correctTime = timeFormat.format(new Date());



        holder.cal.setText(String.valueOf(cal));
        holder.pro.setText(String.valueOf(pro));
        holder.carboh.setText(String.valueOf(carboh));
        holder.fat.setText(String.valueOf(fat));
        holder.time.setText(meal.getTime());
        holder.title.setText(meal.getName());


        int i = meal.getTime().hashCode();
        System.out.println(meal.getTime() + " - " + i);


        String nextMeal = nextMeal();

        if (meal.getTime().equals(nextMeal)) {

            holder.nextMealIcon.setVisibility(View.VISIBLE);

        }


    }

    @Override
    public int getItemCount() {
        return diet.getNumberOfMeals();
    }

    public class MealHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView time;
        TextView title;
        TextView cal;
        TextView pro;
        TextView carboh;
        TextView fat;

        ImageView nextMealIcon;
        ConstraintLayout constraintLayout;

        OnMealLisiner onMealLisiner;
        LottieAnimationView lottieBackground;

        public MealHolder(@NonNull View itemView, OnMealLisiner onMealLisiner) {
            super(itemView);
            time = itemView.findViewById(R.id.diet_recycler_tv_number_meal);
            title = itemView.findViewById(R.id.tv_meal_title);
            constraintLayout = itemView.findViewById(R.id.diet_meal_constranlayout);
            nextMealIcon = itemView.findViewById(R.id.next_meal_icon);

            cal = itemView.findViewById(R.id.tv_recycler_cal);
            pro = itemView.findViewById(R.id.tv_recycler_pro);
            carboh = itemView.findViewById(R.id.tv_recycler_carboh);
            fat = itemView.findViewById(R.id.tv_recycler_fat);


            this.onMealLisiner = onMealLisiner;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onMealLisiner.onMealClick(getAdapterPosition());
        }
    }

    public interface OnMealLisiner{
        void onMealClick(int position);
    }


    private String nextMeal(){

        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String correctTime = timeFormat.format(new Date());
        int correctTimeHash = correctTime.hashCode();

        List<Meal> meals = diet.getMeals();


        for (Meal meal : meals) {

            int mealTimeHash = meal.getTime().hashCode();

            int diff = mealTimeHash - correctTimeHash;

            if (diff > 0){

                return meal.getTime();

            }


        }

        return "";




    }

}
