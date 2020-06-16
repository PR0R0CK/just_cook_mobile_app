package com.example.justcook.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justcook.model.Commentary;
import com.example.justcook.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justcook.R;
import com.example.justcook.model.RecipeBook;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView tvEmail;
    ArrayList<RecipeBook> recipeBooks = new ArrayList<>();
    private ArrayList<RecipeBook> allRecipes = new ArrayList<>();
    private DatabaseReference reference;
    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private RecipeBook recipe;
    private String currentFilter = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        tvEmail = findViewById(R.id.email_textView_header);

        String em = firebaseUser.getEmail();
        String em1 = firebaseUser.getUid();

        Toast.makeText(this,em,Toast.LENGTH_SHORT).show();
        // Błąd !!!
//        tvEmail.setText(em);


        //SETTING UP NAV DRAWER
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        database = FirebaseDatabase.getInstance();
        recipe = new RecipeBook();

        getAllRecipes();
    }

    @Override
    protected void onResume() {
        switch(currentFilter){
            case "all": getAllRecipes();
            break;
            case "Desserts": getAllDessertRecipes();
            break;
            case "Main courses": getAllMainCourses();
            break;
            case "Soups": getAllSoupRecipes();
            break;
            case "My recipes": getAllRecipes(); //TODO: ZAMIENIĆ NA FUNKCJĘ WCZYTUJĄ PRZEPISY UŻYTKOWNIKA
            break;
        }

        super.onResume();
    }

    private void getAllRecipes() {
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
                    initRecyclerView(allRecipes);
                    Log.d("##@@",allRecipes.get(0).getName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getAllDessertRecipes() {
        reference = database.getReference("/recipes");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    allRecipes.clear();
                    for (DataSnapshot dss:dataSnapshot.getChildren()) {
                        recipe = dss.getValue(RecipeBook.class);
                        String type = dss.child("type").getValue(String.class);
                        if (type.equals("Dessert") || type.equals("Deser")) {
                            allRecipes.add(recipe);
                            Log.d("##@@!!",recipe.getType());
                        }
//                        System.out.println("##@@" + allRecipes.toString());
                    }
                    initRecyclerView(allRecipes);
                    Log.d("##@@",allRecipes.get(0).getName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getAllMainCourses() {
        reference = database.getReference("/recipes");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    allRecipes.clear();
                    for (DataSnapshot dss:dataSnapshot.getChildren()) {
                        recipe = dss.getValue(RecipeBook.class);
                        String type = dss.child("type").getValue(String.class);
                        if (type.equals("Main course") || type.equals("Main Course") || type.equals("Danie główne")) {
                            allRecipes.add(recipe);
                            Log.d("##@@!!",recipe.getType());
                        }
//                        System.out.println("##@@" + allRecipes.toString());
                    }
                    initRecyclerView(allRecipes);
                    Log.d("##@@",allRecipes.get(0).getName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void getAllRecipesWhichCalls(String givenName) {
        final String lowerCaseName = givenName.toLowerCase();
        reference = database.getReference("/recipes");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    allRecipes.clear();
                    for (DataSnapshot dss:dataSnapshot.getChildren()) {
                        recipe = dss.getValue(RecipeBook.class);
                        String name = dss.child("name").getValue(String.class);
                        if (name.toLowerCase().contains(lowerCaseName)) {
                            allRecipes.add(recipe);
                            Log.d("##@@!!",recipe.getName());
                        }
//                        System.out.println("##@@" + allRecipes.toString());
                    }
                    initRecyclerView(allRecipes);
                    Log.d("##@@",allRecipes.get(0).getName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getAllSoupRecipes() {
        reference = database.getReference("/recipes");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    allRecipes.clear();
                    for (DataSnapshot dss:dataSnapshot.getChildren()) {
                        recipe = dss.getValue(RecipeBook.class);
                        String type = dss.child("type").getValue(String.class);
                        if (type.equals("Soup") || type.equals("Zupa")) {
                            allRecipes.add(recipe);
                            Log.d("##@@!!",recipe.getType());
                        }
//                        System.out.println("##@@" + allRecipes.toString());
                    }
                    initRecyclerView(allRecipes);
                    Log.d("##@@",allRecipes.get(0).getName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // moze sie jeszcze przydac
//
//    private void getRecipes() {
//        String url = "https://just-cook-ba441.firebaseio.com/recipes.json";
//        Request request = new Request.Builder().url(url).build();
//
//        OkHttpClient client = new OkHttpClient();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("MainActivity","Gotta problem Bitch!");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String body = response.body().string();
//                Log.d("okHTTP",body);
//
//                Gson gson = new GsonBuilder().create();
//
//                Type listType = new TypeToken<ArrayList<RecipeBook>>(){}.getType();
//                recipeBooks = gson.fromJson(body, listType);
//
//                Log.d("ShowFood ",recipeBooks.get(0).toString());
//                Log.d("ShowFood ", String.valueOf(recipeBooks.size()));
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        initRecyclerView(recipeBooks);
//                    }
//                });
//            }
//        });
//    }

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
            currentFilter="desserts";
            getAllDessertRecipes();
            Toast toast = Toast.makeText(getApplicationContext(), "Desserts", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_main_courses) {
            currentFilter="main_courses";
            getAllMainCourses();
            Toast toast = Toast.makeText(getApplicationContext(), "Main courses", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_my_recipes) {
            currentFilter="my_recipes";
            Toast toast = Toast.makeText(getApplicationContext(), "My recipies", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_soups) {
            currentFilter="soups";
            getAllSoupRecipes();
            Toast toast = Toast.makeText(getApplicationContext(), "Soups", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_log_out) {
            Toast toast = Toast.makeText(getApplicationContext(), "Log out", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == R.id.nav_add_recipe) {
            Intent intent = new Intent(this, NewRecipeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_all){
            currentFilter = "all";
            getAllRecipes();
            Toast.makeText(this, "All recipes", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initRecyclerView(ArrayList<RecipeBook> t){
        Collections.reverse(t);
        RecyclerView recyclerView = findViewById(R.id.content_recyclerView_main);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(t,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void toggleSearchBar(View view) {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        view.startAnimation(shake);
        LinearLayout linearLayout = findViewById(R.id.searchBar_linearLayout_main);
        if(linearLayout.getTag().equals("hidden")){
            linearLayout.setVisibility(View.VISIBLE);
            linearLayout.setTag("visible");
        }else if(linearLayout.getTag()=="visible"){
            linearLayout.setVisibility(View.GONE);
            linearLayout.setTag("hidden");
            ((EditText)findViewById(R.id.search_editText_main)).setText("");
            View vi = this.getCurrentFocus();
            if (vi != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void searchRecipe(View view) {
        EditText searchBar = findViewById(R.id.search_editText_main);
        searchBar.setVisibility(View.GONE);
        searchBar.setVisibility(View.VISIBLE);
        String searchedRecipe = String.valueOf(searchBar.getText());
        View vi = this.getCurrentFocus();
        if (vi != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        if(!searchedRecipe.replaceAll("\\s","").equals("")){
            getAllRecipesWhichCalls(searchedRecipe);
        }
    }
}
