package com.example.grouptaskapp;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AddGroupActivity extends AppCompatActivity {
    private static final String TAG = "AddGroupActivity";

    @InjectView(R.id.input_groupName) EditText _groupName;
    @InjectView(R.id.input_groupSummary) EditText _groupSummary;
    @InjectView(R.id.textview1) TextView _groupDeadLine;

    @InjectView(R.id.btn_addUsers) Button _addUsers;
    @InjectView(R.id.btn_addGroup) Button _addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        ButterKnife.inject(this);

        _addUsers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addUsers();
            }
        });
        _addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addGroup();
            }
        });

    }

    public void addUsers(){
        _addUsers.setEnabled(false);

        Intent intent = new Intent(this, SearchUserActivity.class);
        startActivity(intent);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        addSuccess();

                    }
                }, 1000);
        _addUsers.setEnabled(true);
    }

    public void addGroup() {
        Log.d(TAG, "Add");

        _addButton.setEnabled(false);

        String groupName = _groupName.getText().toString();
        String groupSummary = _groupSummary.getText().toString();

        //String groupMembers = _groupMembers.getText().toString();
        String groupDeadline = _groupDeadLine.getText().toString();


        // TODO: Send new Group info to backend to be saved

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        addSuccess();

                    }
                }, 1000);
    }


    public void addSuccess() {
        _addButton.setEnabled(true);
        finish();
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
