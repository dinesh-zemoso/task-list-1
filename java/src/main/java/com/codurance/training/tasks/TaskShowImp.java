package com.codurance.training.tasks;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class TaskShowImp implements  TaskShowService{

    @Override
    public void show(Map<String, List<Task>> tasks) {
        LocalDate currentDate = LocalDate.now();

        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                LocalDate deadline = task.getDeadline();
                if (deadline != null && deadline.equals(currentDate)) {
                    out.printf("    [%c] %s: %s : %s%n", (task.isDone() ? 'x' : ' '), task.getId(),
                            task.getDescription(), deadline);
                }
            }
            out.println();
        }
    }
}
