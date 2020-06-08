package com.example.justcook.service;

import android.util.Log;

import com.example.justcook.R;
import com.example.justcook.model.RecipeBook;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class FirebaseDatabaseConnector {
    private ArrayList<RecipeBook> allRecipes;
    private RecipeBook recipe = new RecipeBook();
    private DatabaseReference reference;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();;

    public FirebaseDatabaseConnector(){}

    public FirebaseDatabaseConnector(ArrayList<RecipeBook> allRecipes) {
        this.allRecipes = allRecipes;
    }

    public ArrayList<RecipeBook> getAllRecipes() {
        reference = database.getReference("/recipes");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    allRecipes.clear();
                    for (DataSnapshot dss:dataSnapshot.getChildren()) {
                        recipe = dss.getValue(RecipeBook.class);
                        allRecipes.add(recipe);
//                        System.out.println("##@@" + allRecipes.toString());
                    }
                    Log.d("##@@",allRecipes.get(0).getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return allRecipes;
    }
}
