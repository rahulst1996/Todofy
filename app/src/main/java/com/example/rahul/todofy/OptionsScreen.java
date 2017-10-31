package com.example.rahul.todofy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OptionsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);
    }

    public void makeANote(View v)
    {
        Intent i=new Intent();
        i.setClass(this,MainActivity.class);
        startActivity(i);
    }

    public void settings(View v)
    {
        Intent i=new Intent();
        i.setClass(this,Settings.class);
        startActivity(i);
    }
}
