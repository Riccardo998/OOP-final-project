package com.example.organizzatore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.organizzatore.ui.example.ExampleDialogOthers;

import java.util.Locale;


public class Impostazioni extends PreferenceFragment {

    private PreferenceListener listener;

    public interface PreferenceListener{
        void restartApp();
        void setLocale(String language);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.impostazioni);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        final SharedPreferences.Editor spEdit = sp.edit();
        SwitchPreference sw_night = (SwitchPreference) findPreference("night");
        sw_night.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean yes = (boolean) newValue;
                if (yes) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    listener.restartApp();
                    spEdit.putBoolean("night", true);
                    spEdit.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    listener.restartApp();
                    spEdit.putBoolean("night", false);
                    spEdit.apply();
                }
                return true;
            }
        });

        SharedPreferences spt= PreferenceManager.getDefaultSharedPreferences(getContext());
        final SharedPreferences.Editor sptEdit= spt.edit();
        SwitchPreference sw_translate= (SwitchPreference) findPreference( "translate");
        sw_translate.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean yes= (boolean) newValue;
                if(yes){
                    listener.setLocale("en");
                    listener.restartApp();
                    sptEdit.putBoolean("translate", true);
                    sptEdit.apply();
                }
                else{
                    listener.setLocale("it");
                    listener.restartApp();
                    sptEdit.putBoolean("translate", false);
                    sptEdit.apply();
                }
                return true;
            }
        });
    }

    @Override
    public void onAttach (Context context){
        super.onAttach(context);
        try {
            listener = (PreferenceListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }
}
