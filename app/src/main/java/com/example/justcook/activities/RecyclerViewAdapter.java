package com.example.justcook.activities;

import android.content.Context;
import android.content.Intent;
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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justcook.R;
import com.example.justcook.model.RecipeBook;

import java.util.ArrayList;
import java.util.Collection;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    ArrayList<RecipeBook> recipes = new ArrayList<>();
    Context mContext;

    public RecyclerViewAdapter(ArrayList<RecipeBook> placeholderInformation, Context mContext) {
        this.recipes = placeholderInformation;
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
        Log.d("onbind", "onbind called");
        final RecipeBook recipe = recipes.get(position);
        holder.recipeRating.setText(recipe.getRate());
        holder.recipeType.setText(recipe.getType());
        holder.recipeAuthor.setText(recipe.getUser().getUsername());
        holder.recipeTitle.setText(recipe.getName());
        if(position%2==1) holder.parentLayout.setBackgroundColor(Color.parseColor("#043701"));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RecipeDetailsActivity.class);
                intent.putExtra("userId",recipe.getUser().getUserId());
                intent.putExtra("username", recipe.getUser().getUsername());
                intent.putExtra("email",recipe.getUser().getEmail());
                intent.putExtra("recipeId", recipe.getRecipeId());
                intent.putExtra("name", recipe.getName());
                intent.putExtra("type", recipe.getType());
                intent.putExtra("picture", recipe.getPicture());
                intent.putExtra("ingredients", recipe.getIngredients());
                intent.putExtra("userId", recipe.getUser().getUsername());
                intent.putExtra("recipe", recipe.getRecipe());
                intent.putExtra("difficulty", recipe.getDifficulty());
                intent.putExtra("rate", recipe.getRate());
                mContext.startActivity(intent);
            }
        });
        switch(recipe.getDifficulty()){
            case "Easy": holder.difficultyPhoto.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.easy_icon));
            break;
            case "Intermediate": holder.difficultyPhoto.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.intermediate_icon));
            break;
            case "Challenging": holder.difficultyPhoto.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.challenging_icon));
            break;
        }
    }

    @Override
    public int getItemCount() {
        return recipes.size();
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