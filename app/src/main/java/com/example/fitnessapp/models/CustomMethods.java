package com.example.fitnessapp.models;

import com.example.fitnessapp.user.Day;
import com.example.fitnessapp.user.Exercise;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomMethods {

    private CustomMethods(){

    }

    public static String getEsTime(Day day){
        List<Exercise> exercises = day.getExercises();
        long restTime = 0;
        long exTime = 0;
        for (Exercise exercise : exercises) {
            restTime += exercise.getRest();
            restTime += exercise.getSets() * 60_000;
        }
        long hours = TimeUnit.MILLISECONDS.toHours(restTime);
        long min = TimeUnit.MILLISECONDS.toMinutes(restTime) - (hours*60);
        String minString = String.valueOf(min);
        String hourString = String.valueOf(hours);
        String total;


        if (min < 10){
            total = hourString + ":0" + min;
        } else {
            total = hourString + ":" + min;
        }

        return total;

    }

    public static String getEsTime(long restTime){
        long hours = TimeUnit.MILLISECONDS.toHours(restTime);
        long min = TimeUnit.MILLISECONDS.toMinutes(restTime) - (hours*60);
        String minString = String.valueOf(min);
        String hourString = String.valueOf(hours);
        String total;


        if (min < 10){
            total = hourString + ":0" + min;
        } else {
            total = hourString + ":" + min;
        }

        return total;
    }

    public static String convertDate(String correctDay){
        switch (correctDay){
            case "sunday":
                return "יום ראשון";
            case "monday":
                return "יום שני";
            case "tuesday":
                return "יום שלישי";
            case "wednesday":
                return "יום רביעי";
            case "thursday":
                return "יום חמישי";
            case "friday":
                return "יום שישי";
            case "saturday":
                return "יום שבת";
            case "יום ראשון":
                return "יום ראשון";
            case "יום שני":
                return "יום שני";
            case "יום שלישי":
                return "יום שלישי";
            case "יום רביעי":
                return "יום רביעי";
            case "יום חמישי":
                return "יום חמישי";
            case "יום שישי":
                return "יום שישי";
            case "יום שבת":
                return "יום שבת";
        }

        return null;
    }

    public static String convertDateToEnglish(String correctDay){
        switch (correctDay){
            case "יום ראשון":
                return "sunday";
            case "יום שני":
                return "monday";
            case "יום שלישי":
                return "tuesday";
            case "יום רביעי":
                return "wednesday";
            case "יום חמישי":
                return "thursday";
            case "יום שישי":
                return "friday";
            case "יום שבת":
                return "saturday";
        }

        return null;
    }



}
