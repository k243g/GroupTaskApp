package com.example.grouptaskapp;

import java.util.ArrayList;

/**

 */
public class Task {
    public String taskTitle;
    public String summary;
    public Group assignedGroup;
    public ArrayList<String> assignedUsers;
    public boolean completed;
    public String deadline;

    public Task(String taskTitle, String summary, Group assignedGroup, ArrayList<String> assignedUsers, String deadline) {
        this.taskTitle = taskTitle;
        this.summary = summary;
        this.assignedGroup = assignedGroup;
        this.assignedUsers = assignedUsers;
        this.completed = false;
        this.deadline = deadline;
    }
    public Task(){}

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Group getAssignedGroup() {
        return assignedGroup;
    }

    public void setAssignedGroup(Group assignedGroup) {
        this.assignedGroup = assignedGroup;
    }

    public ArrayList<String> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(ArrayList<String> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
