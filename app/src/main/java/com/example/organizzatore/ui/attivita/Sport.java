package com.example.organizzatore.ui.attivita;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizzatore.ExampleAdapter;
import com.example.organizzatore.ExampleDialog;
import com.example.organizzatore.ExampleItem;
import com.example.organizzatore.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Sport extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {

    public ExampleAdapter mAdapter;
    public ArrayList<ExampleItem> mExampleList;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    public FloatingActionButton opendialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mExampleList = new ArrayList<>();
        buildRecyclerView();
        opendialog = findViewById(R.id.floatingActionButton);
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

        /*if (position > mAdapter.getItemCount() || position == -1) {
            Toast.makeText(getApplicationContext(), "Posizione non valida", Toast.LENGTH_LONG).show();
            return;
        }*/
        mExampleList.add(new ExampleItem(nome, "This is Line " + position));
        mAdapter.notifyItemInserted(position);
    }

}
