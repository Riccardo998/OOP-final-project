package com.example.organizzatore.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.example.organizzatore.R;
import com.example.organizzatore.ui.attivita.FreeTime;
import com.example.organizzatore.ui.attivita.Lavoro;
import com.example.organizzatore.ui.attivita.Sport;
import com.example.organizzatore.ui.attivita.Studio;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        /*rendo cliccabili gli elementi della cardview*/
        CardView cardSport = root.findViewById(R.id.sport);
        CardView cardLavoro = root.findViewById(R.id.lavoro);
        CardView cardStudio = root.findViewById(R.id.studio);
        CardView cardFreeTime = root.findViewById(R.id.freetime);

        cardSport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Sport.class));
            }
        });

        cardStudio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Studio.class));
            }
        });

        cardLavoro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_nav_home_to_lavoro2);
            }
        });

        cardFreeTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FreeTime.class));
            }
        });
        return root;
    }
}