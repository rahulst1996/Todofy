package com.example.rahul.todofy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Welcome extends AppCompatActivity {

    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        imgView= (ImageView)findViewById(R.id.imgv);
        Thread thread= new Thread(){
            @Override
            public void run() {
               try {
                   sleep(3000);
                   Intent i= new Intent(getApplicationContext(),VoiceRecognition.class);
                   startActivity(i);
                   finish();
               }
               catch (InterruptedException ie)
               {
                   ie.printStackTrace();
               }
            }
        };
        thread.start();

    }
}
