package com.example.organizzatore.ui.ThingsToDo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.organizzatore.R;
import com.example.organizzatore.ui.example.ExampleAdapterOthers;
import com.example.organizzatore.ui.example.ExampleDialogOthers;
import com.example.organizzatore.ui.example.ExampleItemOthers;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class TStudio extends AppCompatActivity implements ExampleDialogOthers.ExampleDialogListener{
    public ExampleAdapterOthers mAdapter;
    public ArrayList<ExampleItemOthers> mExampleList;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    public FloatingActionButton opendialog;
    public Button inizio;
    public Button deleteAll;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_others);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.esercizi));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mExampleList = new ArrayList<>();
        buildRecyclerView();
        opendialog = findViewById(R.id.floatingActionButton);
        inizio=findViewById(R.id.buttonstart);
        deleteAll=findViewById(R.id.deleteAll);
        inizio.setEnabled(false);

        //bottone +
        opendialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExampleDialogOthers exampleDialogOthers = new ExampleDialogOthers();
                exampleDialogOthers.show(getFragmentManager(),"ExampleDialogOthers");
                ExampleDialogOthers.position++;
            }
        });

        inizio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TStudio.this, PreStudio.class);
                intent.putExtra("list", mExampleList);
                startActivity(intent);
            }
        });

        /*deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAll();
            }
        });*/

    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapterOthers(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapterOthers.OnItemClickListener() {
            //se si clicca l'item si attiva la data activity
            public void onItemClick(int position){}
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
    public void insertItem (String nome, int position, String hour, String minute, String second){
        long ore=Long.parseLong(hour);
        long minuti=Long.parseLong(minute);
        long secondi=Long.parseLong(second);
        long input=(ore*3600+minuti*60+secondi)*1000;
        mExampleList.add(new ExampleItemOthers(nome, getString(R.string.durata_attivita) + ore + " : " + minuti + " : " + secondi , input));
        mAdapter.notifyItemInserted(position);
        inizio.setEnabled(true);
    }

    /*public void removeAll(){
        int i=0;
        while(!mExampleList.isEmpty()) {
            mExampleList.remove(i);
            mAdapter.notifyItemRemoved(i);
            i++;
        }
        inizio.setEnabled(false);
        deleteAll.setEnabled(false);
    }*/
}
