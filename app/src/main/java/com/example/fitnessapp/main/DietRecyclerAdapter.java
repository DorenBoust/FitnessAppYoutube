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
import com.example.fitnessapp.user.Diet;
import com.example.fitnessapp.user.Meal;

public class DietRecyclerAdapter extends RecyclerView.Adapter<DietRecyclerAdapter.MealHolder>{

    private Diet diet;
    private LayoutInflater inflater;
    private OnMealLisiner mOnMealLisiner;


    public DietRecyclerAdapter(Diet diet, LayoutInflater inflater, OnMealLisiner onMealLisiner) {
        this.diet = diet;
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
        Switch notification;
        OnMealLisiner onMealLisiner;
        LottieAnimationView lottieBackground;

        public MealHolder(@NonNull View itemView, OnMealLisiner onMealLisiner) {
            super(itemView);
            time = itemView.findViewById(R.id.diet_recycler_tv_number_meal);
            title = itemView.findViewById(R.id.tv_meal_title);
            notification = itemView.findViewById(R.id.switch_eat);

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
