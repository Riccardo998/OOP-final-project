package com.example.organizzatore.ui.ThingsToDo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.organizzatore.R;
import com.example.organizzatore.ui.example.ExampleItemSport;
import java.util.ArrayList;
import java.util.Locale;
import com.example.organizzatore.ui.attivita.Sport;
public class PreSport extends Activity {

    private Button btn_start;
    private Button btn_pause;
    private Button btn_reset;
    private Button btn_next;
    private Button btn_chiudi;
    private TextView chronometer;
    private TextView attivita;

    private CountDownTimer mCountDownTimer;
    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;
    private int i=0;

    private ArrayList<ExampleItemSport> arrayList;
    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_sport);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFinishOnTouchOutside(false);

        arrayList=(ArrayList<ExampleItemSport>) getIntent().getSerializableExtra("list");

        final long data = arrayList.get(i).getTime();
        final String title= arrayList.get(i).getText1();
        final int rep= arrayList.get(i).getRep();
        n = arrayList.size();

        chronometer=findViewById(R.id.chronometer);
        btn_pause=findViewById(R.id.btn_pause);
        btn_reset=findViewById(R.id.btn_reset);
        btn_start=findViewById(R.id.btn_start);
        btn_next=findViewById(R.id.btn_next);
        btn_next=findViewById(R.id.btn_next);
        btn_chiudi=findViewById(R.id.btn_close);
        attivita=findViewById(R.id.attività);

        setTime(data,title,rep);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTimer();
            }
        });
        btn_chiudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TSport.class);
                startActivity(intent);
            }
        });

    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
    }

    private void nextTimer(){
        if(i == n - 1){
            mCountDownTimer.cancel();
            mCountDownTimer.onFinish();
            btn_next.setEnabled(false);
            btn_reset.setEnabled(false);
            btn_pause.setEnabled(false);
            btn_start.setEnabled(false);
            Toast.makeText(getApplication(), "HAI TERMINATO LA TUA ATTIVITA'", Toast.LENGTH_SHORT).show();

        }else {
            mCountDownTimer.cancel();
            mCountDownTimer.onFinish();
            i++;
            String title = arrayList.get(i).getText1();
            long time = arrayList.get(i).getTime();
            int rep= arrayList.get(i).getRep();
            setTime(time,title,rep);
            startTimer();
        }
    }

    private void resetTimer() {
        mCountDownTimer.cancel();
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }
        chronometer.setText(timeLeftFormatted);
    }

    public void setTime(long milliseconds, String title, int ripetizioni) {
        TextView rep=findViewById(R.id.rep);
        rep.setText("" + ripetizioni);
        TextView textView=findViewById(R.id.cosedafare);
        textView.setText(title);
        mStartTimeInMillis = milliseconds;
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
    }
}

