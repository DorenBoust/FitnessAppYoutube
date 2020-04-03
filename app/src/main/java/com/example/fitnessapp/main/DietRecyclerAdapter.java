package com.example.fitnessapp.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class DietRecyclerAdapter extends RecyclerView.Adapter<DietRecyclerAdapter.MealHolder>{

    private Diet diet;
    private Dictionary<String, ProductDataBase> productDataBase;
    private LayoutInflater inflater;
    private OnMealLisiner mOnMealLisiner;


    public DietRecyclerAdapter(Diet diet, User user, LayoutInflater inflater, OnMealLisiner onMealLisiner) {
        this.diet = diet;
        this.productDataBase = user.getProductDataBase();
        this.inflater = inflater;
        this.mOnMealLisiner = onMealLisiner;
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


        holder.pro.setText(String.valueOf(pro));
        holder.carboh.setText(String.valueOf(carboh));
        holder.fat.setText(String.valueOf(fat));
        holder.time.setText(meal.getTime());
        holder.title.setText(meal.getName());




    }

    @Override
    public int getItemCount() {
        return diet.getNumberOfMeals();
    }

    public class MealHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView time;
        TextView title;
        TextView pro;
        TextView carboh;
        TextView fat;

        OnMealLisiner onMealLisiner;
        LottieAnimationView lottieBackground;

        public MealHolder(@NonNull View itemView, OnMealLisiner onMealLisiner) {
            super(itemView);
            time = itemView.findViewById(R.id.diet_recycler_tv_number_meal);
            title = itemView.findViewById(R.id.tv_meal_title);

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

}
