package com.example.justcook.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.justcook.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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

}