package com.example.organizzatore.ui.ThingsToDo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizzatore.R;
import com.example.organizzatore.ui.attivita.Sport;
import com.example.organizzatore.ui.example.ExampleAdapterSport;
import com.example.organizzatore.ui.example.ExampleDialogSport;
import com.example.organizzatore.ui.example.ExampleItemOthers;
import com.example.organizzatore.ui.example.ExampleItemSport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TSport extends AppCompatActivity implements ExampleDialogSport.ExampleDialogListener{
    public ExampleAdapterSport mAdapter;
    public ArrayList<ExampleItemSport> mExampleList;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    public FloatingActionButton opendialog;
    public Button inizio;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_studio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.esercizi));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mExampleList = new ArrayList<>();
        buildRecyclerView();
        opendialog = findViewById(R.id.floatingActionButton);
        inizio=findViewById(R.id.buttonstart);
        inizio.setEnabled(false);

        //bottone +
        opendialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExampleDialogSport exampleDialogSport = new ExampleDialogSport();
                exampleDialogSport.show(getFragmentManager(),"ExampleDialogSport");
                ExampleDialogSport.position++;
            }
        });

        inizio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TSport.this, PreSport.class);
                intent.putExtra("list", mExampleList);
                startActivity(intent);
            }
        });

    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapterSport(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapterSport.OnItemClickListener() {
            //se si clicca l'item si attiva usa funzione
            public void onItemClick(int position){

            }
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
        if (mExampleList.isEmpty())
            inizio.setEnabled(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void insertItem(String nome, int position, String rep, String hour, String minute, String second ) {
        long ore=Long.parseLong(hour);
        long minuti=Long.parseLong(minute);
        long secondi=Long.parseLong(second);
        int ripetizioni=Integer.parseInt(rep);
        long input=(ore*3600+minuti*60+secondi)*1000;
        mExampleList.add(new ExampleItemSport(nome, getString(R.string.durata_attivita) + ore + " : " + minuti + " : " + secondi , input,ripetizioni)); //time
        mAdapter.notifyItemInserted(position);
        inizio.setEnabled(true);
    }

}
