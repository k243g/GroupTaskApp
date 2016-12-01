package com.example.grouptaskapp;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Group {
    public String name;
    public String summary;
    public String deadline;
    public String admin;
    public ArrayList<String> users;
    public ArrayList<Task> tasks;

    public Group(String name, String summary, String deadline, String admin, ArrayList<String> users) {
        this.name = name;
        this.summary = summary;
        this.deadline = deadline;
        this.admin = admin;
        this.users = users;
        this.users.add(admin);
        this.tasks = new ArrayList<Task>(){};
    }
    public Group(){}

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

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public void addTask(Task t){this.tasks.add(t);}

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    //Makes groups onto list of strings
    public List<String> getTaskList(){
        ArrayList<String> stringList = new ArrayList<String>();
        for(int i=0; i< tasks.size(); ++i){
            stringList.add(tasks.get(i).getTaskTitle());
        }
        return stringList;
    }

    public Task getTaskFromString(String taskName){
        Task temp = new Task();

        for(int i=0; i<tasks.size(); ++i){
            if(tasks.get(i).getTaskTitle().equals(taskName)){
                temp = tasks.get(i);
                break;
            }
        }
        return temp;
    }

    public void deleteTask(Task t){
        tasks.remove(t);
    }




}
