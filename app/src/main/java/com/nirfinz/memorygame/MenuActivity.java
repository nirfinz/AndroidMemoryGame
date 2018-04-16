package com.nirfinz.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MenuActivity extends AppCompatActivity {

    final String USER_NAME = "name";
    final String USER_AGE = "age";
    final String LEVEL = "LEVEL";
    final int EASY = 0;
    final int MEDIUM = 1;
    final int HARD = 2;
    final String FOR_TIMER = "forTimer";

    private Button button4x4;
    private Button button2x2;
    private Button button6x6;
    private EditText name_txt;
    private EditText age_txt;
    private String userName;
    private String userAge;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button4x4 = (Button)findViewById(R.id.button_4x4_game);
        button2x2 = (Button)findViewById(R.id.button_2x2_game);
        button6x6 = (Button)findViewById(R.id.button_6x6_game);
        name_txt = (EditText) findViewById(R.id.activity2_userName);
        age_txt = (EditText) findViewById(R.id.activity2_userAge);
        updateUI();

        button2x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,GameActivity.class);
                intent.putExtra(USER_NAME,userName);
                intent.putExtra(USER_AGE,userAge);
                intent.putExtra(LEVEL,2);
                intent.putExtra(FOR_TIMER,EASY);
                startActivity(intent);
            }
        });

        button4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,GameActivity.class);
                intent.putExtra(USER_NAME,userName);
                intent.putExtra(USER_AGE,userAge);
                intent.putExtra(LEVEL,4);
                intent.putExtra(FOR_TIMER,MEDIUM);
                startActivity(intent);
            }
        });

        button6x6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,GameActivity.class);
                intent.putExtra(USER_NAME,userName);
                intent.putExtra(USER_AGE,userAge);
                intent.putExtra(LEVEL,6);
                intent.putExtra(FOR_TIMER,HARD);
                startActivity(intent);
            }
        });

    }

    public void onBackPressed(){
        Intent intent = new Intent(this, WelcomeToActivity.class);
        startActivity(intent);
    }

    private void updateUI() {
        userName = getIntent().getStringExtra(USER_NAME).toString();
        name_txt.setText("Name: " + userName);
        userAge = getIntent().getStringExtra(USER_AGE).toString();
        age_txt.setText("Age: " + userAge);
    }

}
