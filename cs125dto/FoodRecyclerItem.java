package com.example.cs125dto;

import android.graphics.Color;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FoodRecyclerItem extends RecyclerItem{
    private Food food;



    FoodRecyclerItem(Food f) {
        food = f;
    }

    public String getFoodName()
    {
        return food.getDisplay_name();
    }

    public String getCalories()
    {
        return String.valueOf(food.getCalories());
    }

    public int getHealthScore(){return 1; /*TODO: return normalized food health score*/}

}
