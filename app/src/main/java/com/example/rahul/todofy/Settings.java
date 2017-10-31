package com.example.rahul.todofy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void changePassword(View v)
    {
        SharedPreferences shpf= getSharedPreferences("UserPassword", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor= shpf.edit();
        //final EditText taskEditText2= new EditText(this);
        final EditText taskEditText3= new EditText(this);
        AlertDialog dialog2= new AlertDialog.Builder(this)
                .setTitle("Change Password")
                /*.setMessage("Enter Existing Password")
                .setView(taskEditText2)*/
                .setMessage("Enter New Password")
                .setView(taskEditText3)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String pswrd= taskEditText3.getText().toString();
                        editor.putString("password",pswrd);
                        editor.apply();
                        //Toast.makeText(getApplicationContext(),pswrd,Toast.LENGTH_LONG).show();

                        Toast.makeText(getApplicationContext(),"Password changed!", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel",null)
                .create();
        dialog2.show();
    }
}

