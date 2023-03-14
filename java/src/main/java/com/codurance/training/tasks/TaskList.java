package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();

    private final TaskService addProjectImp;
    private final TaskActionService uncheckTaskImp;
    private final TaskActionService checkTaskImp;
    private final TaskService addTaskImp;
    private final TaskByIdService taskByIdService;
    private final TaskShowService taskShowService;
    private final TaskShowService taskShowBydeadLine;
    private final TaskDeadLineService taskDeadLineService;
    private final TaskShowService taskView;

    private final TaskShowByDateService taskShowByDateService;
    private final BufferedReader in;
    private final PrintWriter out;


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.uncheckTaskImp = new UncheckTaskImp();
        this.checkTaskImp = new CheckTaskImp();
        this.addProjectImp = new AddProjectImp(tasks);
        this.addTaskImp = new AddTaskImp(tasks);
        this.taskByIdService = new TaskByIdImp();
        this.taskShowService = new TaskShowImp();
        this.taskDeadLineService = new TaskDeadLineImp();
        this.taskShowByDateService = new TaskShowByDateImp();
        this.taskShowBydeadLine = new TaskByDeadLine();
        this.taskView = new TaskView();
        this.in = reader;
        this.out = writer;
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                setDone(commandRest[1], checkTaskImp);
                break;
            case "uncheck":
                setDone(commandRest[1], uncheckTaskImp);
                break;
            case "help":
                UtilityClass.help();
                break;
            case "deadline":
                taskDeadLineService.setDeadLine(commandRest[1], tasks);
                break;
            case "today":
                taskShowService.show(tasks);
                break;
            case "delete":
                taskByIdService.remove(commandRest[1], tasks);
                break;
            case "view":
                viewTasks(commandRest[1]);
                break;
            default:
                UtilityClass.error(command);
                break;
        }
    }


    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProjectImp.add(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            addTaskImp.add(subcommandRest[1]);
        }
    }

    private void setDone(String idString, TaskActionService taskActionService) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId().equals(idString)) {
                    taskActionService.action(task);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %s.", idString);
        out.println();
    }

    private void viewTasks(String commandLine){
        String[] subcommandRest = commandLine.split(" ", 3);
        String subcommand = subcommandRest[1];
        if(subcommand.equals("date")){
            taskShowByDateService.showByDate(subcommandRest[2], tasks);
        }else if(subcommand.equals("project")){
            taskView.show(tasks);
        }
        else {
            taskShowBydeadLine.show(tasks);
        }
    }
}