package com.example.cs125dto;

import android.content.Context;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.foodViewHolder> {
    private ArrayList<RecyclerItem> mfoodList;
    private Context mcontext;
    public static class foodViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mfoodTitle;
        public TextView mfoodRemainingTime;
        public Button mfoodStateButton;
        public CardView mfoodRecyclerCard;

        public foodViewHolder(View itemView)
        {
            super(itemView);
            mfoodTitle = itemView.findViewById(R.id.foodName_recycler_item);
            mfoodRemainingTime = itemView.findViewById(R.id.foodDescriptor_recycler_item);
            mfoodStateButton = itemView.findViewById(R.id.food_state_button);
            mfoodRecyclerCard = itemView.findViewById(R.id.food_recycler_card);
        }
    }

    public FoodAdapter(ArrayList<RecyclerItem> foodList, Context ct)
    {
        Log.i("food adapter", "just spawned");
        mfoodList = foodList;
        mcontext = ct;
    }

    @NonNull
    @Override
    public foodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Log.i("food adapter", "on craete view holder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_recycler_item, parent, false);
        foodViewHolder foodViewHolder = new foodViewHolder(v);
        return foodViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull foodViewHolder holder, int position) {
        final FoodRecyclerItem curItem = (FoodRecyclerItem)(mfoodList.get(position));
        //Log.i("food adapter setting:", curItem.getTitle());

        holder.mfoodTitle.setText(curItem.getFoodName());
        holder.mfoodRemainingTime.setText(curItem.getCalories());
        int healthScore = curItem.getHealthScore();
        if (healthScore == -1)
        {
            holder.mfoodStateButton.setBackgroundColor(Color.GRAY);
        }
        else if(curItem.getFoodName().compareTo("Bologna")==0)
        {
            holder.mfoodStateButton.setBackgroundColor(mcontext.getResources().getColor(R.color.Tomato));
        }
        else
        {
            holder.mfoodStateButton.setBackgroundColor(mcontext.getResources().getColor(R.color.DarkRain));
        }


    }

    @Override
    public int getItemCount() {
        return mfoodList.size();
    }

    private void checkNull(String s, String varName)
    {
        if (s.isEmpty()||s==null)
        {
            String info = varName + " is null!";
            //Log.i("food Adapter: ",info);
        }
        else
        {
            String info = varName + " is not null!: " + s;
            //Log.i("food Adapter: ",info);
        }
    }



}
