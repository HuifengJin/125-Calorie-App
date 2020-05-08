package com.example.cs125dto;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FireStoreDTO {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference foodsByNameTable = db.collection("FoodsByName");
    private Food foodBuff;

    public FireStoreDTO()
    {
    }
    public void say(String msg)
    {
        String header = "[DTO instance](-_-)/ ";
        Log.i(header, msg);
    }

    public void getFoodByName(String foodName, FireStoreCallBack callBack)
    {
        foodBuff = new Food();
        readData(foodName, callBack);
    }
    private void readData(String foodName,final FireStoreCallBack callBack)
    {
        Query query = foodsByNameTable.whereEqualTo("display_name",foodName);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(">>", document.getId() + " => " + document.toObject(Food.class).getStringdata());
                                foodBuff.loadString(document.toObject(Food.class).getStringdata());
                            }
                            callBack.onCallBack(foodBuff);
                        } else {
                            Log.d(">>", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
    public String getStringData()
    {
        return foodBuff.getStringdata();
    }
}
