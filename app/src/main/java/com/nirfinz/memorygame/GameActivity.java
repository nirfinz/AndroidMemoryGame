package com.nirfinz.memorygame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements  View.OnClickListener {


    private int numOfElements;
    private  MemoryButton [] allButtons;
    private int [] allButtonsGraphicLocation; //random all the location
    private int [] allButtonsGraphics;
    private MemoryButton selectButton1;
    private MemoryButton selectButton2;
    private boolean isBusy = false;
    final String LEVEL = "LEVEL";
    final String USER_NAME = "name";
    final String USER_AGE = "age";
    final String FOR_TIMER = "forTimer";
    private int sizeOfMat;
    private int numOfSeconds;
    private EditText name_txt;
    private EditText age_txt;
    private EditText timer_txt;
    private String userName;
    private String userAge;
    private int[] timerSeconds = {30, 45, 60};
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bindUI();
        initForTimer(numOfSeconds + 1, timer_txt);
        GridLayout theGridLayout = (GridLayout)findViewById(R.id.grid_layout_for_all);
        sizeOfMat = getIntent().getIntExtra(LEVEL,0);
        int numCol =sizeOfMat;
        int numRow = sizeOfMat;
        this.numOfElements = numCol * numRow;
        this.allButtons = new MemoryButton[numOfElements];
        this.allButtonsGraphics = new int [numOfElements/2];
        if(numRow == 2){
            putAllButtonsGraphicForEasy();
        }else if(numRow == 4){
            putAllButtonsGraphicForMedium();
        }else{
            putAllButtonsGraphicForHard();
        }
        this.allButtonsGraphicLocation = new int [numOfElements];
        shuffleButtonGraphics();
        initeMemoryButtons(numRow,numCol,theGridLayout);


    }

    private void initForTimer(long numOfSeconds, EditText timerText) {
        timer = new Timer(numOfSeconds * 1000, 1000, timerText, this);
        timer.start();
    }


    public void initeMemoryButtons(int numRow,int numCol,GridLayout theGridLayout){
        for (int row = 0; row < numRow ; row++){
            for(int col = 0 ; col <numCol ; col++){
                MemoryButton tempButton = new MemoryButton(this,row,col,allButtonsGraphics[allButtonsGraphicLocation[row *numCol +col]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                allButtons[row * numCol +col] = tempButton;
                theGridLayout.addView(tempButton);
            }
        }
    }

    protected void shuffleButtonGraphics(){
        Random rand = new Random();

        for (int i = 0; i < numOfElements ; i++ ){
            this.allButtonsGraphicLocation[i] = i % (numOfElements/2);
        }
        for (int i = 0; i < numOfElements ; i++ ){//swap location
            int temp = this.allButtonsGraphicLocation[i];
            if(numOfElements == 4){
                int swapIndex = rand.nextInt(4);
                allButtonsGraphicLocation[i] = allButtonsGraphicLocation[swapIndex];
                allButtonsGraphicLocation[swapIndex] = temp;
            }else if(numOfElements == 16){
                int swapIndex = rand.nextInt(16);
                allButtonsGraphicLocation[i] = allButtonsGraphicLocation[swapIndex];
                allButtonsGraphicLocation[swapIndex] = temp;
            }else{
                int swapIndex = rand.nextInt(24);
                allButtonsGraphicLocation[i] = allButtonsGraphicLocation[swapIndex];
                allButtonsGraphicLocation[swapIndex] = temp;
            }

        }
    }

    public void onBackPressed(){
        timer.cancel();
        helperForMenu();
    }

    private void bindUI() {
        name_txt = (EditText) findViewById(R.id.userName);
        age_txt = (EditText) findViewById(R.id.userAge);
        timer_txt = (EditText)findViewById(R.id.timer_game);
        userName = getIntent().getStringExtra(USER_NAME).toString();
        name_txt.setText("Name: " + userName);
        userAge = getIntent().getStringExtra(USER_AGE).toString();
        age_txt.setText("Age: " + userAge);
        int level = getIntent().getIntExtra(FOR_TIMER, 0);
        numOfSeconds = timerSeconds[level];
    }


    public void putAllButtonsGraphicForEasy(){
        this.allButtonsGraphics[0] = R.drawable.button_1;
        this.allButtonsGraphics[1] = R.drawable.button_2;
    }

    public void putAllButtonsGraphicForMedium(){
        this.allButtonsGraphics[0] = R.drawable.button_1;
        this.allButtonsGraphics[1] = R.drawable.button_2;
        this.allButtonsGraphics[2] = R.drawable.button_3;
        this.allButtonsGraphics[3] = R.drawable.button_4;
        this.allButtonsGraphics[4] = R.drawable.button_5;
        this.allButtonsGraphics[5] = R.drawable.button_6;
        this.allButtonsGraphics[6] = R.drawable.button_7;
        this.allButtonsGraphics[7] = R.drawable.button_8;
    }
    public void putAllButtonsGraphicForHard(){
        this.allButtonsGraphics[0] = R.drawable.button_1;
        this.allButtonsGraphics[1] = R.drawable.button_2;
        this.allButtonsGraphics[2] = R.drawable.button_3;
        this.allButtonsGraphics[3] = R.drawable.button_4;
        this.allButtonsGraphics[4] = R.drawable.button_5;
        this.allButtonsGraphics[5] = R.drawable.button_6;
        this.allButtonsGraphics[6] = R.drawable.button_7;
        this.allButtonsGraphics[7] = R.drawable.button_8;
        this.allButtonsGraphics[8] = R.drawable.button_9;
        this.allButtonsGraphics[9] = R.drawable.button_10;
        this.allButtonsGraphics[10] = R.drawable.button_11;
        this.allButtonsGraphics[11] = R.drawable.button_12;
        this.allButtonsGraphics[12] = R.drawable.button_13;
        this.allButtonsGraphics[13] = R.drawable.button_14;
        this.allButtonsGraphics[14] = R.drawable.button_15;
        this.allButtonsGraphics[15] = R.drawable.button_16;
        this.allButtonsGraphics[16] = R.drawable.button_17;
        this.allButtonsGraphics[17] = R.drawable.button_18;
    }

    private boolean checkIfDone() {
        for (int i = 0; i < numOfElements; i++) {
            if (allButtons[i].isEnabled()) {
                return false;
            }
        }
        return true;
    }

    private void backToMenu() {
        Handler tempHandler = new Handler();
        tempHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                helperForMenu();
            }
        }, 3000);

    }

    public void timeOutForGame() {
        Toast.makeText(GameActivity.this, "Time's up", Toast.LENGTH_LONG).show();
        helperForMenu();
    }

    private void helperForMenu(){
        Intent intent = new Intent(GameActivity.this,MenuActivity.class);
        intent.putExtra(USER_NAME,userName);
        intent.putExtra(USER_AGE,userAge);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(isBusy){
            return;
        }
        MemoryButton button = (MemoryButton) view;
        if(button.isMatched){
            return;
        }
        if(selectButton1 == null){
            selectButton1 = button;
            selectButton1.flip();
            return;
        }
        if(selectButton1.getId() == button.getId()){
            return;
        }
        if(selectButton1.getFrontImageID() == button.getFrontImageID()){
            button.flip();
            button.setMatched(true);
            selectButton1.setMatched(true);

            selectButton1.setEnabled(false);
            button.setEnabled(false);
            selectButton1 = null;
            if(checkIfDone()){
                GameActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(GameActivity.this, "Winner", Toast.LENGTH_LONG).show();
                    }
                });
                timer.cancel();
                backToMenu();
            }
            return;

        }else{// are not the same
            selectButton2  = button;
            selectButton2.flip();
            isBusy = true;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectButton2.flip();
                    selectButton1.flip();
                    selectButton1 = null;
                    selectButton2 = null;
                    isBusy = false;
                }
            },500);
        }
    }
}