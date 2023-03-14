package com.codurance.training.tasks;

import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class TaskShowByDateImp implements  TaskShowByDateService{
    @Override
    public void showByDate(String date, Map<String, List<Task>> tasks) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getDeadline() != null && task.getDeadline().toString().equals(date)) {
                    out.printf("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                }
            }
            out.println();
        }
    }
}
