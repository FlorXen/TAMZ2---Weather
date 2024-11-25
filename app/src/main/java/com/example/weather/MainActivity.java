package com.example.weather;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.weather.ui.forecast.ForecastFragment;
import com.example.weather.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.weather.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Hide toolbar
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
        } else {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        }

        // Inflate the activity layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Check if we're in portrait (navigation setup needed)
        if (findViewById(R.id.nav_view) != null) {
            // Set up navigation only for portrait mode
            BottomNavigationView navView = findViewById(R.id.nav_view);
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.nav_host_fragment_activity_main);
            NavController navController = navHostFragment.getNavController();
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_forecast).build();
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navView, navController);
        } else {
            // Landscape mode: manually attach fragments
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.findFragmentById(R.id.fragment_home) == null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_home, new HomeFragment())
                        .commit();
            }
            if (fragmentManager.findFragmentById(R.id.fragment_forecast) == null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_forecast, new ForecastFragment())
                        .commit();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Handle navigation when up button is pressed (portrait only)
        if (findViewById(R.id.nav_view) != null) {
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.nav_host_fragment_activity_main);
            return navHostFragment.getNavController().navigateUp() || super.onSupportNavigateUp();
        }
        return super.onSupportNavigateUp();
    }
}
