package com.github.fahimfarhan.admissiondeadline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSignIn, btnSignUp;
    SharedPreferences SM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SM = getSharedPreferences("userrecord", 0);
        Boolean islogin = SM.getBoolean("userlogin", false);
        if(islogin){
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
            finish();
            return;
        }

        btnSignIn = (Button) findViewById(R.id.gotosignin);
        btnSignUp = (Button) findViewById(R.id.gotosignup);

       btnSignIn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
                onSignIn();
           }
       });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onSignUp();
            }
        });
    }


    private void onSignUp(){
        Intent intent = new Intent(MainActivity.this, signup.class);
        startActivity(intent);
    }

    private void onSignIn(){
        Intent intent = new Intent(MainActivity.this, signin.class);
        startActivity(intent);
    }
}
