package com.example.organizzatore;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.Locale;

public class App extends Application {

    @Override
    public void onCreate() {
        loadSettings();
        super.onCreate();
    }

    public void loadSettings() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        final boolean night = sp.getBoolean("night", false);
        if (night) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
