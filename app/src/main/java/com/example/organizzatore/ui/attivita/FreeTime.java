package com.example.organizzatore.ui.attivita;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizzatore.MainActivity;
import com.example.organizzatore.R;
import com.example.organizzatore.data.adapter.FreeTimeDbAdapter;
import com.example.organizzatore.data.AttivitaDbHelper;
import com.example.organizzatore.data.contract.FreeTimeContract.FreeTimeEntry;
import com.example.organizzatore.ui.ThingsToDo.TFreeTime;
import com.example.organizzatore.ui.example.ExampleDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FreeTime extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {

    public String name;
    public FreeTimeDbAdapter mAdapter;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    public FloatingActionButton opendialog;
    private AttivitaDbHelper mDbHelper;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.hobby));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDbHelper = new AttivitaDbHelper(this);
        buildRecyclerView();
        opendialog = findViewById(R.id.floatingActionButton);
        //bottone +
        opendialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExampleDialog exampleDialog = new ExampleDialog();
                exampleDialog.show(getFragmentManager(), "ExampleDialog");
                ExampleDialog.position++;
            }
        });
    }


    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new FreeTimeDbAdapter(this, mDbHelper.getAllItemsFreeTime());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new FreeTimeDbAdapter.OnItemClickListener() {

            //se si clicca l'item si attiva usa funzione
            public void onItemClick(int position) {
                startActivity(new Intent(getApplicationContext(), TFreeTime.class));
            }

            @Override
            public void onDeleteClick(int position) {
                int x = ReadID(position);
                removeItem(x);
            }
        });
    }


    public void removeItem(int position) {
        mDbHelper.deleteFreeTimeEntry(position);
        mAdapter.swapCursor(mDbHelper.getAllItemsFreeTime());
    }


    /*dovrebbe eprmettere di leggere il corretto id dell'item in una data posizione*/
    public int ReadID(int position) {
        mDbHelper = new AttivitaDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {BaseColumns._ID,};

        String sortOrder = FreeTimeEntry._ID + " ASC";

        Cursor cursor = db.query(
                FreeTimeEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        try {
            int idColumnIndex = cursor.getColumnIndex(FreeTimeEntry._ID);
            int currentID = 0;
            int cg = 0;
            while ((cursor.moveToNext() == true) && (cg != (position + 1))) {
                currentID = cursor.getInt(idColumnIndex);
                cg++;
            }
            return currentID;
        } finally {
            cursor.close();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void insertItem(String nome, int position) {
        mDbHelper = new AttivitaDbHelper(this);
        mDbHelper.insertFreeTimeDb(nome);
        Toast.makeText(FreeTime.this, getString(R.string.elemento), Toast.LENGTH_SHORT).show();
        mAdapter.swapCursor(mDbHelper.getAllItemsFreeTime());
        mAdapter.notifyItemInserted(position);
    }

}

