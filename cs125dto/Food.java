package com.example.cs125dto;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

class Food
{
	public long food_code;				  //int64
	public String display_name;		  //object
	public long portion_default;		  //int 64
	public Float portion_amount;		  //float 64
	public String portion_display_name;  //object
	public Float factor;                  //float64
	public Float increment;               //float64
	public Float multiplier;              //float64
	public Float grains;                  //float64
	public Float whole_grains;            //float64
	public Float vegetables;              //float64
	public Float orange_vegetables;       //float64
	public Float drkgreen_vegetables;     //float64
	public Float starchy_vegetables;      //float64
	public Float other_vegetables;        //float64
	public Float fruits;                  //float64
	public Float milk;                    //float64
	public Float meats;                   //float64
	public Float soy;                     //float64
	public Float drybeans_peas;           //float64
	public Float oils;                    //float64
	public Float solid_fats;              //float64
	public Float added_sugars;            //float64
	public Float alcohol;                 //float64
	public Float calories;                //float64
	public Float saturated_fats;          //float64
	public String stringdata;


	public void loadString(String s)
	{
	String v = s.replace("\n","");
	String arr[] = v.split("\\$",-1);
	if (arr.length < 20)
	{
		Log.i("Food Constructor: ", "got arr of size "+String.valueOf(arr.length));
	}

	try
	{
		food_code = Long.valueOf(arr[0]);//                int64
		display_name = arr[1];//              object
		portion_default = Long.valueOf(arr[2]);//            int64
		portion_amount = Float.valueOf(arr[3]);//           float64
		portion_display_name = arr[4];//      object
		factor = Float.valueOf(arr[5]);//                   float64
		increment = Float.valueOf(arr[6]);//                float64
		multiplier = Float.valueOf(arr[7]);//               float64
		grains = Float.valueOf(arr[8]);//                   float64
		whole_grains = Float.valueOf(arr[9]);//             float64
		vegetables = Float.valueOf(arr[10]);//               float64
		orange_vegetables = Float.valueOf(arr[11]);//        float64
		drkgreen_vegetables = Float.valueOf(arr[12]);//      float64
		starchy_vegetables = Float.valueOf(arr[13]);//       float64
		other_vegetables = Float.valueOf(arr[14]);//         float64
		fruits = Float.valueOf(arr[15]);//                   float64
		milk = Float.valueOf(arr[16]);//                     float64
		meats = Float.valueOf(arr[17]);//                    float64
		soy = Float.valueOf(arr[18]);//                      float64
		drybeans_peas = Float.valueOf(arr[19]);//            float64
		oils = Float.valueOf(arr[20]);//                     float64
		solid_fats = Float.valueOf(arr[21]);//               float64
		added_sugars = Float.valueOf(arr[22]);//             float64
		alcohol = Float.valueOf(arr[23]);//                  float64
		calories = Float.valueOf(arr[24]);//                 float64
		saturated_fats = Float.valueOf(arr[25]);//           float64
		stringdata = s;
	}
	catch(NumberFormatException n)
	{
		System.out.print("Error constructing Food object with String: ");
		System.out.println(s);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		n.printStackTrace(pw);
		Log.e("Food constructor",sw.toString());
	}

	}

	public Food(String s)
	{
		this.loadString(s);
	}

	public Food()
	{
		String stub = "00000000$Empty Food Object$2$1.0$cupcake$1.0$0.5$0.5$0.6126$0.0$0.0828$0.0828$0.0$0.0$0.0$0.0$0.0$0.0$0.0$0.0$2.3326700000000002$13.4946$88.55179$0.0$244.8$2.298";
		this.loadString(stub);
	}

	public long getFood_code() {
		return food_code;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public long getPortion_default() {
		return portion_default;
	}

	public Float getPortion_amount() {
		return portion_amount;
	}

	public String getPortion_display_name() {
		return portion_display_name;
	}

	public Float getFactor() {
		return factor;
	}

	public Float getIncrement() {
		return increment;
	}

	public Float getMultiplier() {
		return multiplier;
	}

	public Float getGrains() {
		return grains;
	}

	public Float getWhole_grains() {
		return whole_grains;
	}

	public Float getVegetables() {
		return vegetables;
	}

	public Float getOrange_vegetables() {
		return orange_vegetables;
	}

	public Float getDrkgreen_vegetables() {
		return drkgreen_vegetables;
	}

	public Float getStarchy_vegetables() {
		return starchy_vegetables;
	}

	public Float getOther_vegetables() {
		return other_vegetables;
	}

	public Float getFruits() {
		return fruits;
	}

	public Float getMilk() {
		return milk;
	}

	public Float getMeats() {
		return meats;
	}

	public Float getSoy() {
		return soy;
	}

	public Float getDrybeans_peas() {
		return drybeans_peas;
	}

	public Float getOils() {
		return oils;
	}

	public Float getSolid_fats() {
		return solid_fats;
	}

	public Float getAdded_sugars() {
		return added_sugars;
	}

	public Float getAlcohol() {
		return alcohol;
	}

	public Float getCalories() {
		return calories;
	}

	public Float getSaturated_fats() {
		return saturated_fats;
	}

	public String getStringdata() {
		return stringdata;
	}
}