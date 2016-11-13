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

public class GroupDetails extends AppCompatActivity {

    //Test values
    String[] groupUsers = {"Tim","Jason"};
    String[] groupTasks = {"Progress Report","Hw 1","HW 2","HW3"};
    //Dates[] taskDates = {};
    String groupName;
    String groupSummary;
    String groupDeadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        //Grab Group data passed from Main Activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            groupName = extras.getString("Group");
            //The key argument here must match that used in the other activity

        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle(groupName);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, groupTasks);

        ListView listView = (ListView) findViewById(R.id.task_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String task = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent(view.getContext(), TaskDetails.class);
                        intent.putExtra("Task", task);
                        startActivity(intent);
                    }
                }
        );

        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.activity_listview, groupUsers);

        ListView listView2 = (ListView) findViewById(R.id.user_list);
        listView2.setAdapter(adapter2);

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.group_menu, menu);
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
