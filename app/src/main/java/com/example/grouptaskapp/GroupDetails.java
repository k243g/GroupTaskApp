package com.example.grouptaskapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GroupDetails extends AppCompatActivity {

    String[] groupUsers = {"Tim","Jayson"};
    String[] groupTasks = {"Progress Report","Hw 1","HW 2","HW1"};
    //Dates[] taskDates = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        //Grab Group data passed from Main Activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("Group");
            //The key argument here must match that used in the other activity

        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, groupTasks);

        ListView listView = (ListView) findViewById(R.id.task_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String group = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent(view.getContext(), GroupDetails.class);
                        intent.putExtra("Group", group);
                        startActivity(intent);
                    }
                }
        );
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.group_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_deleteGroup:
                //Need to delete group and go back to main activity
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}
