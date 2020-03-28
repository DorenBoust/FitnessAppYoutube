package com.example.fitnessapp.main;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProviders;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnessapp.R;
import com.example.fitnessapp.keys.KeysBundle;
import com.example.fitnessapp.keys.KeysIntents;
import com.example.fitnessapp.models.BundleSingleton;
import com.example.fitnessapp.user.Diet;
import com.example.fitnessapp.user.Meal;
import com.example.fitnessapp.user.ProductDataBase;
import com.example.fitnessapp.user.User;

import java.io.Serializable;
import java.util.Dictionary;

import static com.example.fitnessapp.models.AppNotification.CHANNEL_1_ID;
import static com.example.fitnessapp.models.AppNotification.CHANNEL_2_ID;

public class DietFragment extends Fragment implements DietRecyclerAdapter.OnMealLisiner {

    private DietViewModel mViewModel;
    private User user;
    private Diet diet;



    public static DietFragment newInstance() {
        return new DietFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.diet_fragment, container, false);

        user = (User) getArguments().getSerializable(KeysBundle.USER_DATA_TO_FRAGMENT);
        System.out.println("Diet" + user);
        System.out.println("Diet - " + user.getDiet().toString());

        diet = user.getDiet();


        final RecyclerView recyclerView = v.findViewById(R.id.diet_recyclerView);
        DietRecyclerAdapter adapter = new DietRecyclerAdapter(diet, getLayoutInflater(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DietViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onMealClick(int position) {
        Meal meal = diet.getMeals().get(position);
        Dictionary<String, ProductDataBase> productDataBase = user.getProductDataBase();

        Intent intent = new Intent(getContext(), DietActivity.class);
        intent.putExtra(KeysIntents.DIET_DATA, meal);
        intent.putExtra(KeysIntents.SEND_USER, user);
        startActivity(intent);
        System.out.println("Click On Meal");
    }

}
