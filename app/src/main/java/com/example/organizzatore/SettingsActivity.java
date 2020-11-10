package com.example.organizzatore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements Impostazioni.PreferenceListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.impostazioni));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            getFragmentManager().beginTransaction().add(R.id.fragment_container, new Impostazioni()).commit();
        }

        loadLocale();
    }

    @Override
    public void restartApp() {
        Intent i=new Intent(this, SettingsActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    public void setLocale (String language){
        Locale locale= new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration= new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor= getSharedPreferences("translate", MODE_PRIVATE).edit();
        editor.putString("la mia lingua", language);
        editor.apply();
    }

    public void loadLocale (){
        SharedPreferences sp= getSharedPreferences("translate", MODE_PRIVATE);
        String language= sp.getString("la mia lingua", "");
        setLocale(language);
    }

}
