package com.codurance.training.tasks;

import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class TaskByIdImp implements  TaskByIdService{
    @Override
    public void remove(String strId, Map<String, List<Task>> tasks) {
        for (List<Task> projectTasks : tasks.values()) {
            for (Task task : projectTasks) {
                if (task.getId().equals(strId)) {
                    projectTasks.remove(task);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %s.%n", strId);
    }
}
