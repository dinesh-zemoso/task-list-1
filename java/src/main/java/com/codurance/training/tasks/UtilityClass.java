package com.codurance.training.tasks;

import static java.lang.System.out;

public class UtilityClass {
    public static void help() {
        out.println("Commands:");
        out.println("  view by project");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description> <task id>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  view by date <date>");
        out.println("  view by deadline");
        out.println("  today");
        out.println("  delete <task_id>");
        out.println();
    }

    public static void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
