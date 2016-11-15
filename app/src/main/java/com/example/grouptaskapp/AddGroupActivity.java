package com.example.grouptaskapp;


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

public class AddGroupActivity extends AppCompatActivity {
    private static final String TAG = "AddGroupActivity";

    @InjectView(R.id.input_groupName) EditText _groupName;
    @InjectView(R.id.input_groupSummary) EditText _groupSummary;
    @InjectView(R.id.textview1) TextView _groupDeadLine;

    //@InjectView(R.id.btn_addUsers) Button _addUsers;
    @InjectView(R.id.btn_addGroup) Button _addButton;

    User currentUser;
    ArrayList<String> groupUsers;

    // TODO: Get all Users and other info from backend
    // test values
    String[] allUsers = new String[]{"Richard","Jason","Tim", "Damien", "Carrie","Stephanie"};
    String admin = "Bruce"; // set admin to current user


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        ButterKnife.inject(this);



        //Spinner Stuff
        // link: https://github.com/pratikbutani/MultiSelectSpinner
        // need help getting multispinner to work, only single spinner works right now
        //MultiSpinner simpleSpinner = (MultiSpinner) findViewById(R.id.simpleMultiSpinner);
        final List<String> list = Arrays.asList(allUsers);

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
                //clear group members array NOTE: another way to get members selected is to
                groupUsers = new ArrayList<String>();
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        Log.i("TAG", i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                        // save name into group array
                        groupUsers.add(items.get(i).getName());
                    }
                }
            }
        });
        //End of Spinner Stuff

        _addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addGroup();
            }
        });

    }





    public void addGroup() {
        Log.d(TAG, "Add");

        _addButton.setEnabled(false);

        //Get all values from fields and save
        String groupName = _groupName.getText().toString();
        String groupSummary = _groupSummary.getText().toString();
        String groupDeadline = _groupDeadLine.getText().toString();
        // Group members saved in groupUsers arraylist
        Log.d(TAG, groupName +" : "+ groupSummary +" : " + groupDeadline);
        for(int t =0; t< groupUsers.size(); ++t){
            Log.d(TAG, groupUsers.get(t));
        }

        //Group newGroup = new Group(groupName,groupSummary,groupDeadline, currentUser, );


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
