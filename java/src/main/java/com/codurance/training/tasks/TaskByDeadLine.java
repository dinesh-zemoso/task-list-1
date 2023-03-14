package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class TaskByDeadLine implements  TaskShowService{

    @Override
    public void show(Map<String, List<Task>> tasks) {
        List<Task> allTasks = new ArrayList<>();
        for (List<Task> tasks_list : tasks.values()) {
            allTasks.addAll(tasks_list);
        }
        allTasks.sort(Comparator.comparing(Task::getDeadline));
        for (Task task : allTasks) {
            if (task.getDeadline() != null) {
                out.printf("%s: %s : %s%n", task.getId(),task.getDescription(), task.getDeadline());
            }
        }
    }
}
