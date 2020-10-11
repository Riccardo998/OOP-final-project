package com.example.organizzatore.ui.example;

import java.io.Serializable;

public class ExampleItemSport implements Serializable {

    private String mText1;
    private String mText2;
    private long time;
    private int rep;

    public ExampleItemSport(String text1, String text2, long tempo, int ripetizioni) {
        mText1 = text1;
        mText2 = text2;
        time= tempo;
        rep=ripetizioni;
    }

    public String getText1() {
        return mText1;
    }
    public String getText2() {
        return mText2;
    }
    public long getTime(){return time;}
    public int getRep(){return rep;}
}
