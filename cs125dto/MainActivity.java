package com.example.cs125dto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    FireStoreDTO fireStoreDTO;
    String buf;
    SearchView searchBar;
    private SqliteDTO sqliteDTO;
    private RecyclerView foodRecycler;
    private RecyclerView.Adapter foodAdapter;
    private RecyclerView.LayoutManager foodLayoutManager;
    private ArrayList<RecyclerItem> foodItemList;
    private ArrayList<String> VegeChoice;
    private ArrayList<String> MeatChoice;
    private ArrayList<String> RestaurantChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteDTO = new SqliteDTO(this);
        foodItemList = new ArrayList<>();
        VegeChoice = new ArrayList<String>();
        VegeChoice.add("Avocado");
        VegeChoice.add("Carrot salad");
        VegeChoice.add("Lettuce");
        MeatChoice = new ArrayList<String>();
        MeatChoice.add("BBQ short rib");
        MeatChoice.add("Liver and onions");
        MeatChoice.add("Meatball sub");
        String age = getIntent().getStringExtra("age");
        String gender = getIntent().getStringExtra("gender");
        String height = getIntent().getStringExtra("height");
        String weight = getIntent().getStringExtra("weight");
        RestaurantChoice = new ArrayList<String>();
        RestaurantChoice.add("Subway at Bc's Cavern Building, 500, Irvine, CA");
        RestaurantChoice.add("Jamba Juice at 311A C Student Center, Irvine, CA");
        RestaurantChoice.add("Panda Express at Student Center, A232, Irvine, CA ");

        fireStoreDTO = new FireStoreDTO();
        Button searchFoodBtn = findViewById(R.id.btn_searchfood);
        searchBar = findViewById(R.id.search_bar_main);
        //below is a weird syntax that defines a function
        //with in the initializer
        //notice the whole code block is in one (); not {}
        searchFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buf = searchBar.getQuery().toString();
                if (buf.equals(""))
                {
                    Toast t = Toast.makeText(getApplicationContext(), "be sure to fill food name", Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, CheckFoodActivity.class);
                Log.i("===========Act #1 ", buf);
                intent.putExtra("targetFoodName", buf);
                startActivity(intent);
            }
        });

        Button suggestFoodBtn = findViewById(R.id.btn_suggest_food);
        Button suggestRestaurantBtn = findViewById(R.id.btn_suggest_restaurant);
        Button dailySummaryBtn = findViewById(R.id.btn_daily_sum);
        Button historyBtn = findViewById(R.id.btn_history);
        suggestFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suggestFood();
            }
        });

        suggestRestaurantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suggestRestaurant();
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryCalorie.class);
                startActivity(intent);
            }
        });

        dailySummaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DailySummary.class);
                startActivity(intent);
            }
        });

        populateRecyclerList();
        foodRecycler = findViewById(R.id.food_recycler);
        foodRecycler.setHasFixedSize(true);
        //Log.i("populate Recycler: ", "creating event adapter");
        foodAdapter = new FoodAdapter(foodItemList,this);
        //Log.i("populate Recycler: ", "event adapter created");
        //Log.i("populate Recycler: ", "start binding items to view");
        foodRecycler.setAdapter(foodAdapter);
        foodLayoutManager = new LinearLayoutManager(this);
        //Log.i("populate Recycler: ", "linear manager is set");
        foodRecycler.setLayoutManager(foodLayoutManager);
        //Log.i("populate Recycler: ", "recyclerView rendering done");
    }


    private void populateRecyclerList()
    {
        Log.i("populate Recycler: ", "starting to populate buffer");
        Cursor data = sqliteDTO.getData();
        LinkedList<Food> tmp = new LinkedList<Food>();
        while(data.moveToNext())
        {
            Log.i("populate Recycler: ", data.getString(1));
            tmp.add(new Food(data.getString(1)));
            //eventItemList.add(new EventRecyclerItem(new Event(data.getString(1))));
        }
        for (Food f: tmp)
        {
            foodItemList.add(new FoodRecyclerItem(f));
        }



        Log.i("done, eventlist size: ", Integer.toString(foodItemList.size()));

    }
    private String sort(ArrayList<String> choice)
    {
        java.util.Random random = new java.util.Random();
        int i = random.nextInt(3);
        return choice.get(i);
    }
    public void refreshRecycler()
    {
        //Log.i("Main Activity ", "refresh recycler called");
        //Log.i("refresh recycler","eventItemList now has"+String.valueOf(eventItemList.size()));
        foodItemList.clear();
        //Log.i("refresh recycler","eventItemList now has"+String.valueOf(eventItemList.size()));
        foodAdapter.notifyDataSetChanged();
        populateRecyclerList();
        //Log.i("refresh recycler","eventItemList now has"+String.valueOf(eventItemList.size()));
        foodAdapter.notifyDataSetChanged();
    }

    private void suggestFood()
    {
        int vege = 0;
        int meat = 0;
        String s = "You have not eaten anything yet";
        Cursor myFood = sqliteDTO.getData();
        while(myFood.moveToNext())
        {
            Food f = new Food(myFood.getString(1));
            if(f.getVegetables() == 0)
            {meat += 1;}
            else
            {vege += 1;}
        }
        if (vege < meat)
        {
            String v = sort(VegeChoice);
            s = "Need some vegetable? I'm thinking " + v;
        }
        else if (vege > meat)
        {
            String m = sort(MeatChoice);
            s = "Need some meat? I'm thinking " + m;
        }
        else if (vege == meat)
        {
            s = "Need some sweets? I'm thinking lemonade";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder((Activity)this);
        // Add the buttons
        builder.setPositiveButton("sure", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setNegativeButton("not now", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        // Set other dialog properties
        builder.setTitle(s);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void suggestRestaurant()
    {
        String s = sort(RestaurantChoice);
        AlertDialog.Builder builder = new AlertDialog.Builder((Activity)this);
        // Add the buttons
        builder.setPositiveButton("I like it", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setNegativeButton("Not interested", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        // Set other dialog properties
        builder.setTitle(s);

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
