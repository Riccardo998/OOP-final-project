package com.example.organizzatore.ui.example;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.organizzatore.R;

public class ExampleDialog extends DialogFragment {

    //attributi per l'ExampleDialog
    private EditText textInputNome;
    private Button positiveButton;
    private Button negativeButton;
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
                positiveButton.setEnabled(!nome.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



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
