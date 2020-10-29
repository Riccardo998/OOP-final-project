package com.example.organizzatore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import android.preference.SwitchPreference;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.organizzatore.MainActivity;
import com.example.organizzatore.R;

import java.util.Locale;


public class Impostazioni extends PreferenceActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.impostazioni);
        loadLocale();
        loadSettings();
    }

    private void loadSettings() {
        SharedPreferences sp = getSharedPreferences("night", 0);
        final SharedPreferences.Editor spEdit = sp.edit();

        final boolean night = sp.getBoolean("night", false);
        if (night) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getListView().setBackgroundColor(Color.parseColor("#382D2C"));
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            getListView().setBackgroundColor(Color.parseColor("#ffffff"));
        }


        SwitchPreference sw_night = (SwitchPreference) findPreference("night");
        sw_night.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean yes = (boolean) newValue;
                if (yes) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    getListView().setBackgroundColor(Color.parseColor("#382D2C"));
                    spEdit.putBoolean("night", true);
                    spEdit.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    getListView().setBackgroundColor(Color.parseColor("#ffffff"));
                    spEdit.putBoolean("night", false);
                    spEdit.apply();
                }
                return true;
            }
        });

        SharedPreferences spt= getSharedPreferences("translate",0);
        final SharedPreferences.Editor sptEdit= spt.edit();

        SwitchPreference sw_translate= (SwitchPreference) findPreference( "translate");
        sw_translate.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean yes= (boolean) newValue;
                if(yes){
                    setLocale("en");
                    sptEdit.putBoolean("translate", true);
                    sptEdit.apply();
                    startActivity(getIntent());
                    finish();
                    overridePendingTransition(0, 0);
                }
                else{
                    setLocale("it");
                    sptEdit.putBoolean("translate", false);
                    sptEdit.apply();
                    startActivity(getIntent());
                    finish();
                    overridePendingTransition(0, 0);
                }
                return true;
            }
        });
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

    public void loadLocale (){
        SharedPreferences sp= getSharedPreferences("translate", MODE_PRIVATE);
        String language= sp.getString("la mia lingua", "");
        setLocale(language);
    }

    @Override
    protected void onResume() {
        loadSettings();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
