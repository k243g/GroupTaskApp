package com.example.grouptaskapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AddTaskActivity extends AppCompatActivity {
    private static final String TAG = "AddTaskActivity";

    @InjectView(R.id.input_taskName) EditText _taskName;
    @InjectView(R.id.input_taskSummary) EditText _taskSummary;
    @InjectView(R.id.textview1) TextView _taskDeadLine;

    //@InjectView(R.id.btn_addUsers) Button _addUsers;
    @InjectView(R.id.btn_addTask) Button _addButton;

    //String[] reminderOptions = new String[]{"None","Day Before","Week Before"};

    // Test values
    //String[] groupUsers = new String[]{"Richard","Jason","Tim", "Damien", "Carrie","Stephanie"};
    List<String> groupUsers;
    ArrayList<String> assignedUsers;
    // need to assign task to current user
    Data d;
    Group g;
    String groupName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.inject(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            groupName = extras.getString("Group");
            //The key argument here must match that used in the other activity

        }
        d = Data.Instance();
        g = d.getGroup(groupName);
        groupUsers = g.getUsers();
        //Spinner Stuff
        // link: https://github.com/pratikbutani/MultiSelectSpinner

        //final List<String> list = Arrays.asList(groupUsers);
        final List<String> list = groupUsers;

        final List<KeyPairBoolData> listArray3 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(list.get(i));
            h.setSelected(false);
            listArray3.add(h);
        }

        MultiSpinnerSearch multiSpinner = (MultiSpinnerSearch) findViewById(R.id.multiSpinner);
        multiSpinner.setItems(listArray3,"Find Users", -1, new MultiSpinnerSearch.MultiSpinnerSearchListener() {

            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {
                assignedUsers = new ArrayList<String>(){};
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        Log.i("TAG", i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                        // save name into group array
                        assignedUsers.add(items.get(i).getName());
                    }
                }
            }
        });
        //End of Spinner Stuff

        _addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addTask();
            }
        });

    }





    public void addTask() {
        Log.d(TAG, "Add");

        if (!validate()) {
            onAddFailed();
            return;
        }

        _addButton.setEnabled(false);

        //Get all values from fields and save
        String taskName = _taskName.getText().toString();
        String taskSummary = _taskSummary.getText().toString();
        String taskDeadline = _taskDeadLine.getText().toString();
        Log.d(TAG, taskName +" : "+ taskSummary +" : " + taskDeadline);
        for(int t =0; t< assignedUsers.size(); ++t){
            Log.d(TAG, assignedUsers.get(t));
        }
        d = Data.Instance();
        //assignedUsers.add(d.getCurrentUser());
        if(true) {
            Task t = new Task(taskName, taskSummary, g, assignedUsers, taskDeadline);
            Log.d(TAG, groupName);
            g.addTask(t);
        }
        else{
            Toast.makeText(getBaseContext(), "Error: Please try again", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Send new Task info to backend to be saved

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        addSuccess();

                    }
                }, 1000);


    }


    public void addSuccess() {
        _addButton.setEnabled(true);
        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public boolean validate() {
        boolean valid = true;

        String taskName = _taskName.getText().toString();
        String taskSummary = _taskSummary.getText().toString();
        String taskDeadline = _taskDeadLine.getText().toString();

        if (groupName.isEmpty()) {
            _taskName.setError("enter a group name");
            valid = false;
        } else {
            _taskName.setError(null);
        }

        if (taskSummary.isEmpty()) {
            _taskSummary.setError("please provide some details");
            valid = false;
        } else {
            _taskSummary.setError(null);
        }

        if (taskDeadline.equals("Deadline")) {
            _taskDeadLine.setError("select deadline");
            valid = false;
        } else {
            _taskDeadLine.setError(null);
        }

        if (groupUsers== null) {
            Toast.makeText(getBaseContext(), "please add users", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            _taskDeadLine.setError(null);
        }

        return valid;
    }
    public void onAddFailed() {

        _addButton.setEnabled(true);
    }



}
