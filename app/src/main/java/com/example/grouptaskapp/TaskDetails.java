package com.example.grouptaskapp;

import android.app.Activity;
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
import android.widget.TextView;

import java.util.List;

public class TaskDetails extends AppCompatActivity {

    //Test values
    //String[] taskUsers = {"Tim","Jason"};
    List<String> taskUsers;

    String taskName;
    String groupName;
    Task t;
    String taskSummary;
    String taskDeadline;
    boolean taskComplete;
    Data d;
    Group g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        //Grab Group data passed from Main Activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            taskName = extras.getString("Task");
            groupName = extras.getString("Group");
            //The key argument here must match that used in the other activity

        }
        d = Data.Instance();
        g = d.getGroup(groupName);
        t = g.getTaskFromString(taskName);
        taskComplete = t.isCompleted();
        taskUsers = t.getAssignedUsers();
        taskSummary = t.getSummary();
        taskDeadline = t.getDeadline();

        TextView t = (TextView) findViewById(R.id.textview0);
        t.setText(taskSummary);
        TextView t2 = (TextView) findViewById(R.id.textviewDL);
        t2.setText(taskDeadline);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if(taskComplete){
            setTitle(taskName+ " : COMPLETE");
        }
        else{
            setTitle(taskName);
        }





        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.activity_listview, taskUsers);

        ListView listView2 = (ListView) findViewById(R.id.user_list);
        listView2.setAdapter(adapter2);

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        //show menu if task is not complete and current user is apart of the group
        d = Data.Instance();
        Group g = d.getGroup(groupName);
        t = g.getTaskFromString(taskName);
        if(!t.isCompleted()  &&  t.getAssignedUsers().contains(d.getCurrentUser())){
            getMenuInflater().inflate(R.menu.task_menu, menu);
        }

        return true;
    }

    // TODO: Make a case to delete group
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_doneTask:
                //Set task as complete
                // reload task details page
                t.setCompleted(true);
                finish();
                return true;

            case R.id.action_deleteTask:
                //Need to delete group and go back to main activity
                g.deleteTask(t);
                Intent resultIntent = new Intent();
                //resultIntent.putExtra("String");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;

            case R.id.action_editTask:
                Intent intent2 = new Intent(this, EditTaskActivity.class);
                intent2.putExtra("Group", groupName);
                intent2.putExtra("Task", taskName);
                startActivity(intent2);
                finish();
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}
