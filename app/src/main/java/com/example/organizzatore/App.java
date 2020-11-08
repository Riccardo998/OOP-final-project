package com.example.organizzatore;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class App extends Application {

    @Override
    public void onCreate() {
        loadSettings();
        super.onCreate();
    }

    public void loadSettings() {
        SharedPreferences sp = getApplicationContext().getSharedPreferences("night", 0);

        final boolean night = sp.getBoolean("night", false);
        if (night) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }
}
