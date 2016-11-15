package com.example.grouptaskapp;

import android.app.Application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kerrn on 11/14/2016.
 */
public class User{
    private String name;
    private String email;
    private String password;

    private ArrayList<Group> groups;
    private ArrayList<Task> tasks;


    //Use this when registering
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        groups = new ArrayList<Group>();
        tasks = new ArrayList<Task>();
    }
    //Use this when logging in
    //get name, groups, and tasks from backend
    public User(String name, String email, ArrayList<Group> groups, ArrayList<Task> tasks) {
        this.name = name;
        this.email = email;
        this.groups = groups;
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTasks(Task task) {
        tasks.add(task);
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void addGroups(Group group) {
        groups.add(group);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Makes groups onto list of strings
    public List<String> getGroupList(){
        ArrayList<String> stringList = new ArrayList<String>();
        for(int i=0; i< groups.size(); ++i){
            stringList.add(groups.get(i).getName());
        }
        return stringList;
    }
}
