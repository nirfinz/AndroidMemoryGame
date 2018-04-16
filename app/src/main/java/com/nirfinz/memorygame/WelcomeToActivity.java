package com.nirfinz.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeToActivity extends AppCompatActivity {

    final String USER_NAME = "name";
    final String USER_AGE = "age";

    private EditText nameEdit;
    private EditText ageEdit;
    private Button loginButton;
    private String name;
    private String age;
    private boolean validName;
    private boolean validAge;
    private boolean isOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome_to);

        init();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEdit.getText().toString().trim();
                age = ageEdit.getText().toString().trim();

                validName = isValidName(name);
                validAge = isValidAge(age);
                
                isOk = checkNameAndAge();

                if(isOk == false){
                    return;
                }else{
                    Intent i = new Intent(getBaseContext(),MenuActivity.class);
                    i.putExtra(USER_NAME,name);
                    i.putExtra(USER_AGE,age);
                    startActivity(i);
                }

            }
        });

    }
    
    public boolean checkNameAndAge(){
        if(!validName && !validAge){
            showErrorMessage("Write your name and your age please");
            return false;
        }
        
        if(!validName){
            showErrorMessage("Write your name please");
            return false;
        }

        if(!validAge){
            showErrorMessage("Write your age please");
            return false;
        }else{
            return true;
        }
    }


    public void showErrorMessage(String message){
        AlertDialog.Builder theAlertDia  = new AlertDialog.Builder(WelcomeToActivity.this);
        theAlertDia.setMessage(message);
        theAlertDia.setTitle("Error Message");
        theAlertDia.setPositiveButton("OK", null);
        theAlertDia.setCancelable(false);
        theAlertDia.create().show();
    }

    public boolean isValidName(String name){
        if(name.equals("")){
            return false;
        }
        return true;
    }

    public boolean isValidAge(String age){
        double  checkAge = 0;
        try{
            checkAge = Double.parseDouble(age);
        }
        catch (Exception e){
            return false;
        }
        return checkAge > 0 && checkAge <= 120;
    }

    public void init(){
        nameEdit = findViewById(R.id.edt_userName);
        ageEdit = findViewById(R.id.edt_age);
        loginButton = findViewById(R.id.btnLogin);
    }
}
