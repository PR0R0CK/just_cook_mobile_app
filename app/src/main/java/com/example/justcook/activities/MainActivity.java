package com.example.justcook.activities;

import androidx.annotation.NonNull;
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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.justcook.R;
import com.example.justcook.model.RecipeBook;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //TODO: utworzenie Data Class "Recipe"
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

        initRecyclerView();
//        fetchJson();

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

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.content_recyclerView_main);
        final ArrayList<String> placeholderInformation = new ArrayList<>();
//
//        String body = "";
//        String url  = "https://just-cook-ba441.firebaseio.com/recipes.json";
//        Gson gson = new GsonBuilder().create();
//        RecipeBook recipeBook = gson.fromJson(url,RecipeBook.class);

        placeholderInformation.add("Franchezinha");
        placeholderInformation.add("Pierogis");
        placeholderInformation.add("Pancakes");
        placeholderInformation.add("Beer Cake");

//        placeholderInformation.add(recipeBook.getName());

        placeholderInformation.add("Junk Food Special");

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(placeholderInformation,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



//    private void fetchJson() {
//        RecyclerView recyclerView = findViewById(R.id.content_recyclerView_main);
//        final ArrayList<String> placeholderInformation = new ArrayList<>();
//        final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
////        if (isInternetConnection()) {
////            Toast.makeText(this, "Refreshed!", Toast.LENGTH_SHORT).show();
//            println("Attempting to fetch JSON");
//
//            String url = "https://just-cook-ba441.firebaseio.com/recipes/name.json";
//
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder().url(url).build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//                if (!response.isSuccessful()) {
//                    throw new IOException("Unexpected code " + response);
//                } else {
//                    String body = response.body().string();
//                    Log.d("OKhttp",body);
//                    Gson gson = new GsonBuilder().create();
//
//                    List<String> placeholderInformation = new ArrayList<>();
//                    placeholderInformation.add(gson.fromJson(body,String.class));
//                }
//
//            }
//
//
////        } else {
////            Toast.makeText(this,"You don't have Internet Connection!",Toast.LENGTH_LONG).show();
////        }
//
//        });
//    }

}