package com.example.grouptaskapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class GroupDetails extends AppCompatActivity {
    public final static int REQUEST_CODE_B = 1;
    private static final String TAG = "GroupDetailsActivity";
    //Test values
    //String[] groupUsers = {"Tim","Jason"};
    //String[] groupTasks = {"Progress Report","Hw 1","HW 2","HW3"};
    List<String> groupUsers;
    List<String> groupTasks;
    //Dates[] taskDates = {};
    String groupName;
    String groupSummary;
    String groupDeadline;
    Data d;
    Group g;
    ArrayAdapter adapter;

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
        d = Data.Instance();

        g = d.getGroup(groupName);
        groupTasks = g.getTaskList();
        groupSummary = g.getSummary();
        groupDeadline = g.getDeadline();
        groupUsers = g.getUsers();

        TextView t = (TextView) findViewById(R.id.textview);
        t.setText(groupSummary);
        TextView t2 = (TextView) findViewById(R.id.textviewDL);
        t2.setText(groupDeadline);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle(groupName);

        adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, groupTasks);

        ListView listView = (ListView) findViewById(R.id.task_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String task = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent(view.getContext(), TaskDetails.class);
                        intent.putExtra("Task", task);
                        intent.putExtra("Group", groupName);
                        startActivityForResult(intent,REQUEST_CODE_B);
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
                intent.putExtra("Group", groupName);
                startActivityForResult(intent, REQUEST_CODE_B);
                return true;

            case R.id.action_deleteGroup:
                //Need to delete group and go back to main activity
                d.deleteGroup(g);
                Intent resultIntent = new Intent();
                //resultIntent.putExtra("String");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;

            case R.id.action_editGroup:
                //Need to delete group
                Intent intent2 = new Intent(this, EditGroupActivity.class);
                intent2.putExtra("Group", groupName);

                startActivity(intent2);
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void upDateList(){
        Log.d(TAG, "Updating Task List");
        adapter.clear();

        groupTasks = g.getTaskList();
        List<String> newTasks = g.getTaskList();
        for(int t =0; t< newTasks.size(); ++t){
            Log.d(TAG, newTasks.get(t));
        }
        adapter.addAll(newTasks);
        adapter.notifyDataSetChanged();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        upDateList();
    }

}
