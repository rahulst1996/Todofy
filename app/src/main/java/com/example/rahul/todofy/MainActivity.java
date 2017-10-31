package com.example.rahul.todofy;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DB dbHelper;
    ArrayAdapter<String> mAdapter;
    ListView lstTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper= new DB(this);

        lstTask= (ListView)findViewById(R.id.lstTask);

        loadTaskList();
      /*  lstTask.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(getApplicationContext(),"POPUP",Toast.LENGTH_LONG).show();
                return true;
            }
        }); */
    }

    private void loadTaskList()
    {
        ArrayList<String> taskList= dbHelper.getTaskList();
        if(mAdapter==null)
        {
            mAdapter= new ArrayAdapter<String>(this,R.layout.row,R.id.taskTitle,taskList);
            lstTask.setAdapter(mAdapter);
        }
        else
        {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        //change menu icon color
        Drawable icon= menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_add_task:
                final EditText taskEditText= new EditText(this);
                AlertDialog dialog= new AlertDialog.Builder(this)
                        .setTitle("Add New Task")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task= taskEditText.getText().toString();
                                dbHelper.insertNewTask(task);
                                loadTaskList();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                dialog.show();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void deleteTask(View view)
    {

        try
        {
            int index = lstTask.getPositionForView(view);
            String task = mAdapter.getItem(index++);
            dbHelper.deleteTask(task);
            loadTaskList();
        }
        catch (Exception ex) {
            Toast.makeText(this, ex.getMessage().toString(), Toast.LENGTH_LONG).show(); }
        /*View parent= (View)view.getParent();
        TextView taskTextView= (TextView)findViewById(R.id.taskTitle);
        String task= valueOf(taskTextView.getText());
        dbHelper.deleteTask(task);
        loadTaskList();*/
    }


}
