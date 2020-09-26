package com.example.organizzatore.ui.ThingsToDo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizzatore.R;
import com.example.organizzatore.ui.example.ExampleAdapterSport;
import com.example.organizzatore.ui.example.ExampleDialogSport;
import com.example.organizzatore.ui.example.ExampleItemSport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TSport extends AppCompatActivity implements ExampleDialogSport.ExampleDialogListener{
    public ExampleAdapterSport
            mAdapter;
    public ArrayList<ExampleItemSport> mExampleList;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    public FloatingActionButton opendialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_sport); //TODO implement layout all_pre per tutti, metto il + ... devo capire se è per tutti e negli altri metto rep =1  o solo per sport
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mExampleList = new ArrayList<>();
        buildRecyclerView();
        opendialog = findViewById(R.id.floatingActionButton);
        //bottone +
        opendialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExampleDialogSport exampleDialogSport = new ExampleDialogSport();
                exampleDialogSport.show(getFragmentManager(),"ExampleDialogSport");
                ExampleDialogSport.position++;
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
                startActivity(new Intent(getApplicationContext(), PreSport.class)); //todo linkare qui nel .class prima del . la classe corretta : il preimpostato
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
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void insertItem(String nome, int position) {

        mExampleList.add(new ExampleItemSport(nome, "This is Line " + position));
        mAdapter.notifyItemInserted(position);
    }

}
