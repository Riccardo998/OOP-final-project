package com.example.organizzatore.ui.cronometro;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.organizzatore.R;

public class Cronometro extends Fragment {


    Button
            btn_start;
    Button btn_pause;
    Button btn_reset;
    Chronometer
            xChronometer;
    long pauseOffset;
    boolean running;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //collego il CronometroFragment al layout fragment_cronometro
        View view = inflater.inflate(R.layout.fragment_cronometro, container, false);
        xChronometer = view.findViewById(R.id.chronometer);
        //creo il cronometro, settando le ore, i minuti e i secondi
        xChronometer.setBase(SystemClock.elapsedRealtime());
        xChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                int h = (int) (time / 3600000);
                int m = (int) (time - h * 3600000) / 60000;
                int s = (int) (time - h * 3600000 - m * 60000) / 1000;
                String t = (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);
                chronometer.setText(t);
            }
        });

        //creo i bottoni per la pausa, il reset e lo start
        btn_pause=view.findViewById(R.id.btn_pause);
        btn_reset=view.findViewById(R.id.btn_reset);
        btn_start=view.findViewById(R.id.btn_start);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    xChronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    xChronometer.start();
                    running = true;
                }
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xChronometer.stop();
                xChronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                running=false;
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running) {
                    xChronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - xChronometer.getBase();
                    running = false;
                }
            }
        });

        //ritorno sempre la view, cioè ciò che verrà visto quando premo sul menu item "Cronometro"
        return view;
    }
}