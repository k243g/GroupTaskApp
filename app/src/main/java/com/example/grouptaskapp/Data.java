package com.example.grouptaskapp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
   // ArrayList<Group> groups;
    String user = "Bruce";
    String[] allUsers = new String[]{"Richard", "Jason", "Tim", "Damian", "Carrie", "Stephanie"};
    Group group1 = new Group("EE 461L", "Software Engineering and Design Laboratory", "12/05/2016", user, new ArrayList<String>(
            Arrays.asList("Richard", "Tim", "Stephanie")));
    Task t11 = new Task("HW 1", "Create a Blog using Google appengine", group1, new ArrayList<String>(
            Arrays.asList(user, "Richard", "Tim", "Stephanie")), "9/23/2016");
    Task t12 = new Task("HW 3", "Testing Your Project", group1, new ArrayList<String>(
            Arrays.asList(user, "Richard", "Tim", "Stephanie")), "11/22/2016");
    ArrayList<Task> t1 = new ArrayList<Task>() {{
        add(t11);
        add(t12);
    }};


    Group group2 = new Group("EE 445L", "Embedded Systems Design Lab", "12/05/2016", user, new ArrayList<String>(
            Arrays.asList("Richard", "Damian")));
    Task t21 = new Task("Lab 3: Software", "Write .c and .h files", group2, new ArrayList<String>(
            Arrays.asList(user, "Richard")), "9/19/2016");
    Task t22 = new Task("Lab 3: Hardware", "Create PCB file", group2, new ArrayList<String>(
            Arrays.asList("Damian")), "9/12/2016");
    ArrayList<Task> t2 = new ArrayList<Task>() {{
        add(t21);
        add(t22);
    }};


    Group group3 = new Group("BIO 373", "Ecology Presentation", "11/28/2016", user, new ArrayList<String>(
            Arrays.asList("Richard", "Jason", "Tim")));
    Task t31 = new Task("Research Article", "Research Buffelgress", group2, new ArrayList<String>(
            Arrays.asList("Tim")), "11/21/2016");
    Task t32 = new Task("Slides", "Finish Presentation", group2, new ArrayList<String>(
            Arrays.asList("Richard")), "11/27/2016");
    Task t33 = new Task("Create Models", "Make chart of annual precipitation", group2, new ArrayList<String>(
            Arrays.asList(user)), "11/24/2016");
    ArrayList<Task> t3 = new ArrayList<Task>() {{
        add(t31);
        add(t32);
        add(t33);
    }};


    ArrayList<Group> groups = new ArrayList<Group>() {{
        add(group1);
        add(group2);
        add(group3);
    }};


    public static Data instance;

    public Data()
    {
        group1.setTasks(t1);
        group2.setTasks(t2);
        group3.setTasks(t3);
    }

    public String[] getAllUsers(){
        return allUsers;
    }
    public List<String> getGroupsList(){

            ArrayList<String> stringList = new ArrayList<String>();
            for(int i=0; i< groups.size(); ++i){
                stringList.add(groups.get(i).getName());
            }
            return stringList;
    }

    public static Data Instance()
    {
        //if no instance is initialized yet then create new instance
        //else return stored instance
        if (instance == null)
        {
            instance = new Data();
        }
        return instance;
    }
    public Group getGroup(String groupName){
        Group temp = new Group();

        for(int i=0; i<groups.size(); ++i){
            if(groups.get(i).getName().equals(groupName)){
                temp = groups.get(i);
                break;
            }
        }
        return temp;
    }
    public String getCurrentUser(){
        return user;
    }

    public void addGroup(Group g){
        groups.add(g);
    }

    public void deleteGroup(Group g){groups.remove(g);}







}
