package com.example;

import javax.persistence.*;

/**
 * Created by Yao on 12/30/2015.
 */

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String taskDate;
    private String taskName;
    private boolean taskDone = false;

    public Task() { }

    public Task(String taskDate, String taskName, boolean taskDone) {
        this.taskDate = taskDate;
        this.taskName = taskName;
        this.taskDone = taskDone;
    }

    public Long getId() {
        return id;
    }
    public void setTaskDate(String taskDate)
    {
        this.taskDate = taskDate;
    }
    public String getTaskDate()
    {
        return taskDate;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }
    public boolean getTaskDone() {
        return taskDone;
    }
}
