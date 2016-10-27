package com.example.grouptaskapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    // Array of strings for testing group list
    String[] groupArray = {"EE 461L","Senior Design","EE 460N","HW1: Blog"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Need to check if user is already logged in
        //Get user profile
        //Helpful link:
        //https://stormpath.com/blog/build-user-authentication-for-android-app

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, groupArray);

        ListView listView = (ListView) findViewById(R.id.group_list);
        listView.setAdapter(adapter);

    }


    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_addGroup:
                // User chose the "Addgroup" item, show the app settings UI...
                Intent intent = new Intent(this, AddGroupActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }




}
