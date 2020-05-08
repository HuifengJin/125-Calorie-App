package com.example.cs125dto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class DailySummary extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailysummary);

        setupPieChart();
    }

    public double GoalCalorie()
    {

        //temp
        String gender = "male";
        double weight = 50.00;
        int age = 20;
        double height = 168.00;

        String exercise = "none";
        boolean none;
        boolean light;
        boolean moderately;
        boolean intensely;

        double BMR = 0;
        double goalCal = 0;


        if (gender == "male")
        {
            BMR = (int) (66 + (6.23 * weight) + (12.7 * height) - (6.8 * age));
        }
        else
        {
            BMR = (int) (665 + (4.35 * weight) + (4.7 * height) - (4.7 * age));
        }

        switch(exercise){
            case "none":
                goalCal = BMR * 1.2;
            case "light":
                goalCal = BMR * 1.375;
            case "moderately":
                goalCal = BMR * 1.55;
            case "intensely":
                goalCal = BMR * 1.9;
        }
        return goalCal;
    }

    public void setupPieChart() {
        PieChart piechart = findViewById(R.id.chart);
        piechart.setUsePercentValues(true);

        //add description
        Description desc = new Description();
        desc.setText("Here is your Daily Summary. Good Job!");
        desc.setTextSize(20f);
        piechart.setDescription(desc);
        piechart.setCenterText("Goal Calorie: 2500");
        piechart.setCenterTextSize(28f);

        //set radius
        piechart.setHoleRadius(50f);
        piechart.setTransparentCircleRadius(25f);

        //set data
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(427f, "Consumed 427"));
        pieEntries.add(new PieEntry(2073f, "Remaining 2073"));


        PieDataSet dataSet = new PieDataSet(pieEntries, "Daily Summary");
        PieData data = new PieData(dataSet);
        piechart.setData(data);

        //set color
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        //set animate
        piechart.animateXY(1400, 1400);
    }
}
