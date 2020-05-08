package com.example.cs125dto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckFoodActivity extends AppCompatActivity {


    //Put class variable before on create
    //so that space would be allocated
    //waiting for initialization in onCreate Method
    FireStoreDTO fireStoreDTO;
    TextView FoodName;
    TextView FoodCalorie;
    String targetFoodName;
    Food food;
    private SqliteDTO sqliteDTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkfood);

        sqliteDTO = new SqliteDTO(this);
        fireStoreDTO = new FireStoreDTO();
        FoodName = findViewById(R.id.FoodName);
        FoodCalorie = findViewById(R.id.FoodCalorie);
        targetFoodName = getIntent().getStringExtra("targetFoodName");
        Log.i("===========Act #2 ", targetFoodName);

        Button notNowBtn = findViewById(R.id.btn_not_now);
        Button addDayBtn = findViewById(R.id.btn_add_to_day);

        notNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        addDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = fireStoreDTO.getStringData();
                Log.d("Activity adding to day ", data);
                sqliteDTO.addData(data);
                Toast t = Toast.makeText(getApplicationContext(), "food added :)", Toast.LENGTH_SHORT);
                t.show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        fireStoreDTO.getFoodByName(targetFoodName, new FireStoreCallBack(){
            @Override
            public void onCallBack(Food f)
            {
                Log.d("Activity #2: callBack: ", targetFoodName);
                Log.d("Activity #2: callBack: ", f.stringdata);

                //parameter food f contains received food object ^^on callback^^;
                FoodName.setText(f.getDisplay_name());
                FoodName.setAllCaps(true);
                FoodCalorie.setText(String.valueOf(f.getCalories()));
            }
        });

    }
}
