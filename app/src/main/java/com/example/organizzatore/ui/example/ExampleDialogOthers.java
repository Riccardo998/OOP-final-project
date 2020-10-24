package com.example.organizzatore.ui.example;
/*UGUALE A QUELLO DI SPORT MA SENZA RIPETIZIONI*/
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
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.organizzatore.R;

public class ExampleDialogOthers extends DialogFragment {

    //attributi per l'ExampleDialog
    private EditText textInputNome;
    private Button positiveButton;
    private Button negativeButton;
    private ExampleDialogListener listener;
    private EditText textInputTempo;
    public static int position=0;

    public static final String TAG = "ExampleDialogOthers";
    private NumberPicker hour;
    private NumberPicker minute;
    private NumberPicker second;
    public  TextView H;
    public  TextView Min;
    public  TextView S;

    @NonNull
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        //collegamento con il layout del dialogo
        View view = inflater.inflate(R.layout.layout_dialog_others, container, false);

        //attributi per il nome da inserire e la scelta del programma (studio o allenamento)
        textInputNome = view.findViewById(R.id.tiCrea);
        positiveButton = view.findViewById(R.id.positivebutton);
        negativeButton = view.findViewById(R.id.negativebutton);

        //disabilito il pulsante crea
        positiveButton.setEnabled(false);

        hour= view.findViewById(R.id.hour);
        minute=view.findViewById(R.id.minute);
        second=view.findViewById(R.id.second);

        H=view.findViewById(R.id.tvH);
        Min=view.findViewById(R.id.tvM);
        S=view.findViewById(R.id.tvS);

        hour.setMinValue(0);
        hour.setMaxValue(23);
        minute.setMinValue(0);
        minute.setMaxValue(59);
        second.setMinValue(0);
        second.setMaxValue(59);

        hour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                H.setText(""+newVal);
            }
        });

        minute.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Min.setText(""+newVal);
            }
        });

        second.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                S.setText(""+newVal);
            }
        });

        //sfruttando due TextWatcher, modifico il bottone crea, cioè lo abilito o no a seconda del fatto se sto scrivendo o no il nome del programma e se ho inserito la posizione
        textInputNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //implemento solo questa. Mentre sto scrivendo il nome e la posizione, rendo attivo il crea. Se uno dei due campi è vuoto, il bottone crea rimane disabilitato
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String nome = textInputNome.getText().toString().trim();
                String ore= H.getText().toString().trim();
                String minuti= Min.getText().toString().trim();
                String secondi= S.getText().toString().trim();
                positiveButton.setEnabled(!nome.isEmpty() && !ore.isEmpty() && !minuti.isEmpty() && !secondi.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        H.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //implemento solo questa. Mentre sto scrivendo il nome e la posizione, rendo attivo il crea. Se uno dei due campi è vuoto, il bottone crea rimane disabilitato
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String nome = textInputNome.getText().toString().trim();
                String ore= H.getText().toString().trim();
                String minuti= Min.getText().toString().trim();
                String secondi= S.getText().toString().trim();
                positiveButton.setEnabled(!nome.isEmpty() && !ore.isEmpty() && !minuti.isEmpty() && !secondi.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Min.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //implemento solo questa. Mentre sto scrivendo il nome e la posizione, rendo attivo il crea. Se uno dei due campi è vuoto, il bottone crea rimane disabilitato
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String nome = textInputNome.getText().toString().trim();
                String ore= H.getText().toString().trim();
                String minuti= Min.getText().toString().trim();
                String secondi= S.getText().toString().trim();
                positiveButton.setEnabled(!nome.isEmpty() && !ore.isEmpty() && !minuti.isEmpty() && !secondi.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        S.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //implemento solo questa. Mentre sto scrivendo il nome e la posizione, rendo attivo il crea. Se uno dei due campi è vuoto, il bottone crea rimane disabilitato
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String nome = textInputNome.getText().toString().trim();
                String ore= H.getText().toString().trim();
                String minuti= Min.getText().toString().trim();
                String secondi= S.getText().toString().trim();
                positiveButton.setEnabled(!nome.isEmpty() && !ore.isEmpty() && !minuti.isEmpty() && !secondi.isEmpty());
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

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String nome = textInputNome.getText().toString().trim();
                String ore= H.getText().toString().trim();
                String minuti= Min.getText().toString().trim();
                String secondi= S.getText().toString().trim();
                listener.insertItem(nome, position, ore, minuti, secondi);
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
        void insertItem(String nome, int position, String hour, String minute, String second);

    }

}
