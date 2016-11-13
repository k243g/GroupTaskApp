package com.example.grouptaskapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TaskDetails extends AppCompatActivity {

    //Test values
    String[] taskUsers = {"Tim","Jason"};

    //Dates[] taskDates = {};
    String taskName;
    String taskSummary;
    String taskDeadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        //Grab Group data passed from Main Activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            taskName = extras.getString("Task");
            //The key argument here must match that used in the other activity

        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle(taskName);



        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.activity_listview, taskUsers);

        ListView listView2 = (ListView) findViewById(R.id.user_list);
        listView2.setAdapter(adapter2);

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.task_menu, menu);
        return true;
    }

    // TODO: Make a case to delete group
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_addTask:
                //Need to delete group and go back to main activity
                Intent intent = new Intent(this, AddTaskActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}
