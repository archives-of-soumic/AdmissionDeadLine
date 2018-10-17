package com.github.fahimfarhan.admissiondeadline;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ListView extends AppCompatActivity {

    private ArrayList<DeadlineInfo> arrayList;

    private void getAllDataFromDB(){
        Models.databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    DeadlineInfo value = data.getValue(DeadlineInfo.class);
                    arrayList.add(value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_view);

            readLocalData();
            //ListView lv = findViewById(R.id.lv123);// (ListView) findViewById(R.id.lv123);

            /**
            arrayList = new ArrayList<DeadlineInfo>();
            getAllDataFromDB();
            String s123 = "\n\n";
            for(int i=0; i<arrayList.size(); i++){
                s123 += arrayList.get(i).exam+" "+arrayList.get(i).date.toString()+"\n";
                System.out.println(s123);

                Toast.makeText(this, "lalala1 New Deadline added! :D ", Toast.LENGTH_LONG).show();
            }
            s123 = s123+"\n\n";
           // tv.setText(s123);
            Toast.makeText(this, s123, Toast.LENGTH_LONG).show();
            */
        }catch (Exception x){
            System.out.println(x.toString());
            Toast.makeText(this, x.toString(), Toast.LENGTH_LONG).show();
        }

        // tv.setOnClickListener();

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
        });*/
    }

    private void readLocalData() throws FileNotFoundException {
        String filename = "farcry4.csv";
        FileInputStream fis = openFileInput(filename);
        Scanner scanner = new Scanner(fis);
        scanner.useDelimiter("\\Z");
        String content = scanner.next();
        scanner.close();
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

}
