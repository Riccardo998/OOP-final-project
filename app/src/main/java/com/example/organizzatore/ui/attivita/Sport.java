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
import com.example.organizzatore.data.adapter.SportDbAdapter;
import com.example.organizzatore.ui.ThingsToDo.TSport;
import com.example.organizzatore.ui.example.ExampleDialog;
import com.example.organizzatore.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.organizzatore.data.AttivitaDbHelper;
import com.example.organizzatore.data.contract.SportContract.SportEntry;

public class Sport extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {

    public String name;
    public SportDbAdapter mAdapter;
    public RecyclerView mRecyclerView;
    public RecyclerView.LayoutManager mLayoutManager;
    public FloatingActionButton opendialog;
    private AttivitaDbHelper
            mDbHelper;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mDbHelper = new AttivitaDbHelper(this);
        dbReadActivity(); //legge elementi dal db e mette in textview

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
        mAdapter = new SportDbAdapter(this, mDbHelper.getAllItemsSport());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new SportDbAdapter.OnItemClickListener() {

            //se si clicca l'item si attiva usa funzione
            public void onItemClick(int position){
                startActivity(new Intent(getApplicationContext(), TSport.class));

            }
            @Override
            public void onDeleteClick (int position) {
                int x = ReadID(position);
                removeItem(x);
            }
        });
    }

   public void removeItem(int position) {
       mDbHelper.deleteSportEntry(position);
       mAdapter.swapCursor(mDbHelper.getAllItemsSport());

    }


/*dovrebbe eprmettere di leggere il corretto id dell'item in una data posizione*/
     public int ReadID(int position){
         mDbHelper = new AttivitaDbHelper(this);
         SQLiteDatabase db = mDbHelper.getReadableDatabase();
         String[] projection = {BaseColumns._ID,
            };

         String sortOrder = SportEntry._ID + " ASC";

         Cursor cursor = db.query(
                 SportEntry.TABLE_NAME,   // The table to query
                 projection,             // The array of columns to return (pass null to get all)
                 null,              // The columns for the WHERE clause
                 null,          // The values for the WHERE clause
                 null,                   // don't group the rows
                 null,                   // don't filter by row groups
                 sortOrder               // The sort order
         );
         try {
         int idColumnIndex = cursor.getColumnIndex(SportEntry._ID);
             int currentID  = 0;
             int cg=0;
         while ((cursor.moveToNext() == true) && (cg != (position + 1))) {
             currentID = cursor.getInt(idColumnIndex);
             cg ++;

            }
         return currentID;
         } finally {
        cursor.close();
    }
}





    @RequiresApi(api = Build.VERSION_CODES.M)
    public void insertItem(String nome, int position) {
        mDbHelper = new AttivitaDbHelper(this);
        mDbHelper.insertSportDb(nome);
        Toast.makeText(Sport.this, "inserito nuovo elemento", Toast.LENGTH_SHORT).show();
        mAdapter.swapCursor(mDbHelper.getAllItemsSport());
        mAdapter.notifyItemInserted(position);
    }


/*PARTE PER DISPLAY ----DEBUG -----*/
    public void dbReadActivity (){
        mDbHelper = new AttivitaDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //specifico le colonne che userò dopo la query
        String[] projection = {
                BaseColumns._ID,
                SportEntry.COLUMN_ATTIVITA
                /* SportEntry.COLUMN_COSE_DA_FARE,
                 SportEntry.COLUMN_REP,
                 SportEntry.COLUMN_TIME*/
        };

// Filter results WHERE "title" = 'My Title'
        //String selection = SportEntry.COLUMN_ATTIVITA + " = ?";
        //String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
        String sortOrder = SportEntry._ID + " ASC";

        Cursor cursor = db.query(
                SportEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        TextView displayView = (TextView) findViewById(R.id.text_view_out);

        try {
            displayView.setText("La tabella attività contiene: " + cursor.getCount() + " Sport.\n\n");
            displayView.append(SportEntry._ID + " - " +
                    SportEntry.COLUMN_ATTIVITA + " - " +
                   /* SportEntry.COLUMN_COSE_DA_FARE + " - " +
                    SportEntry.COLUMN_REP + " - " +
                    SportEntry.COLUMN_TIME +*/ "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(SportEntry._ID);
            int attivitaColumnIndex = cursor.getColumnIndex(SportEntry.COLUMN_ATTIVITA);
            /*int cfdColumnIndex = cursor.getColumnIndex(SportEntry.COLUMN_COSE_DA_FARE);
            int repColumnIndex = cursor.getColumnIndex(SportEntry.COLUMN_REP);
            int timeColumnIndex = cursor.getColumnIndex(SportEntry.COLUMN_TIME);*/

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentAttivita = cursor.getString(attivitaColumnIndex);
                /*String currentCdf = cursor.getString(cfdColumnIndex);
                int currentREP = cursor.getInt(repColumnIndex);
                int currentTime = cursor.getInt(timeColumnIndex);*/
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentAttivita + " - " /*+
                        currentCdf + " - " +
                        currentREP + " - " +
                        currentTime*/));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
