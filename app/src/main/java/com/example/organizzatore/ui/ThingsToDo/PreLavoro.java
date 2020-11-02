package com.example.organizzatore.ui.ThingsToDo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.organizzatore.R;
import com.example.organizzatore.ui.example.ExampleItemOthers;

import java.util.ArrayList;
import java.util.Locale;

public class PreLavoro extends Activity {
    private  NotificationHelper mNotificationHelper;
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

    private ArrayList<ExampleItemOthers> arrayList;
    private int n;

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_others);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFinishOnTouchOutside(false);
        mNotificationHelper = new NotificationHelper(this);
        arrayList=(ArrayList<ExampleItemOthers>) getIntent().getSerializableExtra("list");

        final long data = arrayList.get(i).getTime();
        final String title= arrayList.get(i).getText1();

        n = arrayList.size();

        chronometer=findViewById(R.id.chronometer);
        btn_pause=findViewById(R.id.btn_pause);
        btn_reset=findViewById(R.id.btn_reset);
        btn_start=findViewById(R.id.btn_start);
        btn_next=findViewById(R.id.btn_next);
        btn_chiudi=findViewById(R.id.btn_close);
        attivita=findViewById(R.id.attività);

        btn_next.setEnabled(false);
        btn_pause.setEnabled(false);
        btn_reset.setEnabled(false);

        setTime(data,title);

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
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                stopPlayer();
                nextTimer();
            }
        });
        btn_chiudi.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(mCountDownTimer!=null)
                    mCountDownTimer.cancel();
                if(player!=null)
                    stopPlayer();
                if(i==n-1){
                    Toast.makeText(getApplication(), getString(R.string.attivitasvolta), Toast.LENGTH_SHORT).show();
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(getApplicationContext(), AlertReceiver.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, 0, pendingIntent);
                }
                finish();
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
                play();
                if(i == n - 1){
                    btn_next.setEnabled(false);
                    btn_reset.setEnabled(false);
                    btn_pause.setEnabled(false);
                    btn_start.setEnabled(false);
                }
            }
        }.start();
        btn_pause.setEnabled(true);
        btn_reset.setEnabled(true);
        if(i!=n-1)
            btn_next.setEnabled(true);
        else
            btn_next.setEnabled(false);
        btn_start.setEnabled(false);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        btn_start.setEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void nextTimer(){
        mCountDownTimer.cancel();
        i++;
        if(i==n-1)
            btn_next.setEnabled(false);
        Toast.makeText(getApplication(), getString(R.string.tasksvolta)+ i, Toast.LENGTH_SHORT).show();
        String title = arrayList.get(i).getText1();
        long time = arrayList.get(i).getTime();
        setTime(time,title);
        btn_start.setEnabled(true);
    }

    private void resetTimer() {
        stopPlayer();
        mCountDownTimer.cancel();
        mTimeLeftInMillis = mStartTimeInMillis;
        btn_start.setEnabled(true);
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

    public void setTime(long milliseconds, String title) {
        TextView textView=findViewById(R.id.cosedafare);
        textView.setText(title);
        mStartTimeInMillis = milliseconds;
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
    }

    public void play() {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.song);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}

