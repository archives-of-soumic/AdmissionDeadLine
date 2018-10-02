package com.github.fahimfarhan.admissiondeadline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {
    int num = 6;
    Button[] btnWord = new Button[num];
    LinearLayout linear;
    View.OnClickListener btnClicked;
    String[] varsity;
    Map mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        varsity = new String[]{"MIST", "DU", "RUET", "CUET", "KUET", "JU"};

        buttonBuilder();

        mp = new HashMap();
        mp.put("MIST", "lalala");


        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });  */
    }

    private void buttonBuilder() {
        linear = (LinearLayout) findViewById(R.id.linear);
        for (int i = 0; i < btnWord.length; i++) {
            btnWord[i] = new Button(this);
            btnWord[i].setHeight(50);
            btnWord[i].setWidth(50);
            btnWord[i].setText(varsity[i]);
            btnWord[i].setTextColor(getResources().getColor(R.color.white));
            if(i%2==0){btnWord[i].setBackgroundColor(getResources().getColor(R.color.colorPrimary));}
            else{btnWord[i].setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));}
            btnWord[i].setTag(i);
            // btnWord[i].setOnClickListener(btnClicked);
            btnWord[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // display circular
                }
            });

            linear.addView(btnWord[i]);
        }
    }

    private void signOut(){
        SharedPreferences SM = getSharedPreferences("userrecord", 0);
        SharedPreferences.Editor edit = SM.edit();
        edit.putBoolean("userlogin", false);
        edit.apply();
        edit.commit();

        Intent intent = new Intent(Home.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
