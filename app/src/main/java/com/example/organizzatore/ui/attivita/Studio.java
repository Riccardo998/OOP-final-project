package com.example.organizzatore.ui.attivita;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizzatore.R;
import com.example.organizzatore.ui.ThingsToDo.TSport;
import com.example.organizzatore.ui.ThingsToDo.TStudio;
import com.example.organizzatore.ui.example.ExampleAdapter;
import com.example.organizzatore.ui.example.ExampleDialog;
import com.example.organizzatore.ui.example.ExampleItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Studio extends AppCompatActivity implements ExampleDialog.ExampleDialogListener{

    public ExampleAdapter mAdapter;
    public ArrayList<ExampleItem> mExampleList;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    public FloatingActionButton opendialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studio);
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
                ExampleDialog exampleDialog = new ExampleDialog();
                exampleDialog.show(getFragmentManager(),"ExampleDialog");
                ExampleDialog.position++;
            }
        });
    }


    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {

            //se si clicca l'item si attiva usa funzione
            public void onItemClick(int position){
                startActivity(new Intent(getApplicationContext(), TStudio.class));
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

        mExampleList.add(new ExampleItem(nome, "This is Line " + position));
        mAdapter.notifyItemInserted(position);

    }

}
