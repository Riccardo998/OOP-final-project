package com.example.organizzatore.ui.example;

import java.io.Serializable;

public class ExampleItemOthers implements Serializable {
    private String mText1;
    private String mText2;
    private long time;

    public ExampleItemOthers(String text1, String text2, long tempo) {

        mText1 = text1;
        mText2 = text2;
        time = tempo;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

    public long getTime() {
        return time;
    }
}