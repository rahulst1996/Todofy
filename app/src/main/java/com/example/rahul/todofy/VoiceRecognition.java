package com.example.rahul.todofy;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class VoiceRecognition extends AppCompatActivity {

    private TextView txtV;

    //private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognition);
        txtV=(TextView) findViewById(R.id.textView2);

    }
    public void imageClick(View v)
    {
        if(v.getId()==R.id.imageButton2)
        {
            voiceRecog();
        }
    }
    public void voiceRecog()
    {
        Intent i= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak your Password");
        try
        {
        startActivityForResult(i,100);
        }
        catch (ActivityNotFoundException anfe)
        {
            Toast.makeText(this,"Not Supported",Toast.LENGTH_LONG).show();
        }
    }

    public void onActivityResult(int reqcode,int resultCode,Intent i)
    {
        super.onActivityResult(reqcode,resultCode,i);
        SharedPreferences shpf= getSharedPreferences("UserPassword", Context.MODE_PRIVATE);
        String passwrd=shpf.getString("password","");
        switch (reqcode)
        {
            case 100:
                if(resultCode==RESULT_OK && i!=null)
                {
                    if(passwrd=="")
                    {
                        Toast.makeText(this,"Default Password is rahul!",Toast.LENGTH_LONG).show();
                        //System.out.print("Hello");
                        SharedPreferences.Editor edit= shpf.edit();
                        edit.putString("password","Rahul");
                        edit.apply();
                        String passWord= shpf.getString("password","");
                        ArrayList<String> arl = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                       // txtV.setText(passWord);
                        if (arl.get(0).equalsIgnoreCase(passWord)) {
                            Intent i1 = new Intent();
                            i1.setClass(this, OptionsScreen.class);
                            startActivity(i1);
                            Toast.makeText(this,"Login Successful!",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(this,"Wrong Password!",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        String passWord= shpf.getString("password","");
                        ArrayList<String> arl = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                      //  txtV.setText(passWord);
                        if (arl.get(0).equalsIgnoreCase(passWord)) {
                            Intent i1 = new Intent();
                            i1.setClass(this, OptionsScreen.class);
                            startActivity(i1);
                            Toast.makeText(this,"Login Successful!",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(this,"Wrong Password!",Toast.LENGTH_LONG).show();
                        }
                    }

                }
                break;
        }
    }
}
