package com.codurance.training.tasks;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class TaskDeadLineImp implements  TaskDeadLineService{

    @Override
    public void setDeadLine(String command, Map<String, List<Task>> tasks) {
        String[] subcommandRest = command.split(" ", 2);
        String taskId = subcommandRest[0];
        String deadLine = subcommandRest[1];

        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId().equals(taskId)) {
                    task.setDeadline(LocalDate.parse(deadLine));
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %s.%n", taskId);
    }
}
