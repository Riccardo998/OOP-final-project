package com.example.organizzatore;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.Locale;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        loadLocale();
        SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("night", MODE_PRIVATE);
        boolean isNightMode = sharedPreferences.getBoolean("night",false);
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }

    public void loadLocale (){
        SharedPreferences sp= getApplicationContext().getSharedPreferences("translate", MODE_PRIVATE);
        String language= sp.getString("la mia lingua", "");
        setLocale(language);
    }

    private void setLocale (String language){
        Locale locale= new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration= new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor= getSharedPreferences("translate", MODE_PRIVATE).edit();
        editor.putString("la mia lingua", language);
        editor.apply();
    }
}
