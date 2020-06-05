package com.example.justcook.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.os.Bundle;
import okhttp3.*;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.justcook.R;
import com.example.justcook.model.RecipeBook;
import com.google.android.material.navigation.NavigationView;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ArrayList<RecipeBook> recipeBooks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SETTING UP NAV DRAWER
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getRecipes();
    }

    private void getRecipes() {
        String url = "https://just-cook-ba441.firebaseio.com/recipes.json";
        Request request = new Request.Builder().url(url).build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("MainActivity","Gotta problem Bitch!");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Log.d("okHTTP",body);

                Gson gson = new GsonBuilder().create();

                Type listType = new TypeToken<ArrayList<RecipeBook>>(){}.getType();
                recipeBooks = gson.fromJson(body, listType);

                Log.d("ShowFood ",recipeBooks.get(0).toString());
                Log.d("ShowFood ", String.valueOf(recipeBooks.size()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initRecyclerView(recipeBooks);
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_desserts) {
            Toast toast = Toast.makeText(getApplicationContext(), "Desserts", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_main_courses) {
            Toast toast = Toast.makeText(getApplicationContext(), "Main courses", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_my_recipes) {
            Toast toast = Toast.makeText(getApplicationContext(), "My recipies", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_soups) {
            Toast toast = Toast.makeText(getApplicationContext(), "Soups", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_log_out) {
            Toast toast = Toast.makeText(getApplicationContext(), "Log out", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_add_recipe) {
            Toast toast = Toast.makeText(getApplicationContext(), "Add recipe", Toast.LENGTH_SHORT);
            toast.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initRecyclerView(ArrayList<RecipeBook> t){
        RecyclerView recyclerView = findViewById(R.id.content_recyclerView_main);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(t,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
