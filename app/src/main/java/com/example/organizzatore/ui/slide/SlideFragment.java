package com.example.organizzatore.ui.slide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.organizzatore.R;
import com.example.organizzatore.SlideAdapter;

public class SlideFragment extends Fragment {

    private ViewPager viewPager;
    private SlideAdapter slideAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slide, container, false);
        viewPager= (ViewPager) view.findViewById(R.id.viewpager);

        slideAdapter= new SlideAdapter(getContext());

        viewPager.setAdapter(slideAdapter);
        return view;
    }
}
