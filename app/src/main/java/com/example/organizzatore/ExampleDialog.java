package com.example.organizzatore;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizzatore.ui.attivita.Sport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ExampleDialog extends DialogFragment {

    //attributi per l'ExampleDialog
    private EditText textInputNome;
    private Button positiveButton;
    private Button negativeButton;
    //private EditText Position;
    private ExampleDialogListener listener;
    public static int position=0;

    public static final String TAG = "ExampleDialog";

    @NonNull
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        //collegamento con il layout del dialogo
        View view = inflater.inflate(R.layout.layout_dialog, container, false);

        //attributi per il nome da inserire e la scelta del programma (studio o allenamento)
        textInputNome = view.findViewById(R.id.tiCrea);
        //Position = view.findViewById(R.id.position);
        positiveButton = view.findViewById(R.id.positivebutton);
        negativeButton = view.findViewById(R.id.negativebutton);

        //disabilito il pulsante crea
        positiveButton.setEnabled(false);

        //sfruttando due TextWatcher, modifico il bottone crea, cioè lo abilito o no a seconda del fatto se sto scrivendo o no il nome del programma e se ho inserito la posizione
        textInputNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //implemento solo questa. Mentre sto scrivendo il nome e la posizione, rendo attivo il crea. Se uno dei due campi è vuoto, il bottone crea rimane disabilitato
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String nome = textInputNome.getText().toString().trim();
                //String position = Position.getText().toString().trim();
                //positiveButton.setEnabled(!nome.isEmpty() && !position.isEmpty());
                positiveButton.setEnabled(!nome.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        /*Position.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String nome = textInputNome.getText().toString().trim();
                String position = Position.getText().toString().trim();
                positiveButton.setEnabled(!nome.isEmpty() && !position.isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/


            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDialog().dismiss();
                }
            });

            //se con la funzione addTextChangedListener il bottone crea è stato abilitato, dovrebbe funzionare tutto ciò
            //nel bottone crea, inserisco le mie due variabili nome e radioId, che mi serviranno per la funzione da implementare (applyProgram)
            //radioId è l'ID del bottone scelto tra Studio e Allenamento,mentre il nome è quello che abbiamo scritto per il nostro nuovo programma
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    String nome = textInputNome.getText().toString().trim();
                    //int position = Integer.parseInt(Position.getText().toString());
                    //listener.insertItem(nome, position - 1);
                    listener.insertItem(nome, position);
                    getDialog().dismiss();
                }
            });

            return view;
        }

        @Override
        public void onAttach (Context context){
            super.onAttach(context);
            try {
                listener = (ExampleDialogListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() +
                        "must implement ExampleDialogListener");
            }
        }

        public interface ExampleDialogListener {
            void insertItem(String nome, int position);
        }
}
