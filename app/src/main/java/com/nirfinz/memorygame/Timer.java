package com.nirfinz.memorygame;

import android.os.CountDownTimer;
import android.widget.EditText;

public class Timer extends CountDownTimer {
    EditText timerText;
    GameActivity theActivity;

    public Timer(long numOfSeconds, long interval, EditText text,GameActivity theActivity){
        super(numOfSeconds,interval);
        this.timerText = text;
        this.theActivity = theActivity;
    }

    public void onTick(long millisUntilFinished){
        timerText.setText("Timer: " + millisUntilFinished/1000 + " seconds");
    }

    public void onFinish(){
        timerText.setText("Timer: " + 0 + " seconds");
        theActivity.timeOutForGame();
    }
}
