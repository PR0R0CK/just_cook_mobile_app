package com.example.justcook.activities;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justcook.R;
import com.example.justcook.model.RecipeBook;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private static final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    private ArrayList<String> placeholderInformation = new ArrayList<>();
    Context mContext;

    public RecyclerViewAdapter(ArrayList<String> placeholderInformation, Context mContext) {
        this.placeholderInformation = placeholderInformation;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DatabaseReference ref = mDatabase.getReference("/recipes");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RecipeBook recBook = dataSnapshot.getValue(RecipeBook.class);
                Log.d("Download",recBook.toString());
                System.out.println(recBook);
                placeholderInformation.add(recBook.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



        Log.d("onbind", "onbind called");
        holder.recipeRating.setText("5");
        holder.recipeType.setText("Dessert");
//        holder.recipeAuthor.setText("Ulaniec");
        holder.recipeTitle.setText(placeholderInformation.get(position));
        if(position%2==1) holder.parentLayout.setBackgroundColor(Color.parseColor("#F8033101"));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"Onclick",Toast.LENGTH_SHORT).show();
            }
        });

        //TODO: obsługa wyświetlania zdjęć holder.difficultyPhoto / holder.recipePhoto
    }

    @Override
    public int getItemCount() {
        return placeholderInformation.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView recipePhoto;
        ImageView difficultyPhoto;
        TextView recipeTitle;
        TextView recipeAuthor;
        TextView recipeType;
        TextView recipeRating;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipePhoto = itemView.findViewById(R.id.photo_imageView_recipe);
            difficultyPhoto = itemView.findViewById(R.id.difficulty_imageView_recipe);
            recipeTitle = itemView.findViewById(R.id.tvTitle_recipe);
            recipeAuthor = itemView.findViewById(R.id.tvAuthor_recipe);
            recipeType = itemView.findViewById(R.id.tvType_recipe);
            recipeRating = itemView.findViewById(R.id.rating_textView_recipe);
            parentLayout = itemView.findViewById(R.id.parentLayout_recipe);
        }
    }
}
