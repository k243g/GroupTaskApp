package com.example.grouptaskapp;

import java.util.ArrayList;

/**
 * Created by Kerrn on 11/14/2016.
 */
public class Group {
    private String name;
    private String summary;
    private String deadline;
    private User admin;
    private ArrayList<User> users;
    private ArrayList<Task> tasks;

    public Group(String name, String summary, String deadline, User admin, ArrayList<User> users) {
        this.name = name;
        this.summary = summary;
        this.deadline = deadline;
        this.admin = admin;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
