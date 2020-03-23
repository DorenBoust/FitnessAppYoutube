package com.example.fitnessapp.models;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.fitnessapp.keys.KeysUserFragment;
import com.example.fitnessapp.main.StatusFragment;
import com.example.fitnessapp.user.User;

public class BundleSingleton {

    private BundleSingleton(){}
    private static Bundle bundle = new Bundle();

    public static void setUserBundle(User user, Fragment fragment){

        bundle.putSerializable(KeysUserFragment.USER_DATA_TO_FRAGMENT, user);
        fragment.setArguments(bundle);

    }

}
