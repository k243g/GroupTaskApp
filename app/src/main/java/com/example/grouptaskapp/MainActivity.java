package com.example.grouptaskapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public final static int REQUEST_CODE_B = 1;
    // Array of strings for testing group list


    //String[] groupArray = {"EE 461L","Senior Design","EE 460N","HW1: Blog"};
    //List<String> groupArray = d.getGroupsList();
    List<String> groupArray;
    Data d;
    ArrayAdapter adapter;

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

        d = Data.Instance();

        groupArray = d.getGroupsList();

        adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, groupArray);
        //ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, currentUser.getGroupList());

        ListView listView = (ListView) findViewById(R.id.group_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String group = String.valueOf(parent.getItemAtPosition(position));
                        Intent intent = new Intent(view.getContext(), GroupDetails.class);
                        intent.putExtra("Group", group);
                        startActivityForResult(intent,REQUEST_CODE_B);
                    }
                }
        );


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
                //startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_B);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void upDateList(){
        Log.d(TAG, "Updating Main List");
        adapter.clear();

        groupArray = d.getGroupsList();
        List<String> newGroups = d.getGroupsList();
        for(int t =0; t< newGroups.size(); ++t){
            Log.d(TAG, newGroups.get(t));
        }
        adapter.addAll(newGroups);
        adapter.notifyDataSetChanged();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        upDateList();
    }





}
