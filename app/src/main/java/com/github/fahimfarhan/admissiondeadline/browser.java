package com.github.fahimfarhan.admissiondeadline;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

public class browser extends AppCompatActivity {

    Button viewCircular, createDeadLine;
    private DatePicker dpResult;
    private Button btnChangeDate;

    private int year;
    private int month;
    private int day;


    private String dmail, dexam;
    private Date ddate;
    private DeadlineInfo deadlineInfo;

    private void getAllInfo(){
        dmail = Models.email;
        dexam = Models.exam;
        ddate = new Date(dpResult.getYear(),dpResult.getMonth(),dpResult.getDayOfMonth());

    }

    private void setDeadLine(){
        deadlineInfo = new DeadlineInfo(dmail, dexam, ddate);
    }

    private void createDeadLineAndSaveInfo()
    {
        try{
            Models.databaseReference.child(Models.user.getUid()).setValue(deadlineInfo);
            Toast.makeText(browser.this, "New Deadline added! :D ", Toast.LENGTH_LONG).show();
        }catch (Exception x){
            System.out.println(x.toString());
            Toast.makeText(browser.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
        }

    }

    private void saveOnLocalDevice(){
        String filename = "farcry4.csv";
        String fileContents = deadlineInfo.exam+";"+deadlineInfo.date.toString()+"\n";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);

            outputStream.write(fileContents.getBytes());
            outputStream.close();
            Toast.makeText(browser.this, "New Deadline added! :D ", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(browser.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
        }

    }

    static final int DATE_DIALOG_ID = 999;

    //private WebView webView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        setCurrentDateOnView();
        //addListenerOnButton();
        /*
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(
                "http://readingbd.com/chittagong-university-admission-notice/");
        */
        Intent browserIntent = new
                Intent(Intent.ACTION_VIEW, Uri.parse(Models.site));
        startActivity(browserIntent);
        viewCircular = findViewById(R.id.view_circular);

        viewCircular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse(Models.site));
                startActivity(browserIntent);
            }
        });


        createDeadLine = findViewById(R.id.create_deadline);
        createDeadLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);

                getAllInfo();
                setDeadLine();
                //createDeadLineAndSaveInfo();
                saveOnLocalDevice();

                Intent intent = new Intent(browser.this, Home.class);
                startActivity(intent);
                finish();
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
        });
        */
    }

    public void setCurrentDateOnView() {

        dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview

        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            /**
            tvDisplayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));
            */
            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

        }
    };

}
