package com.codurance.training.tasks;

import java.util.List;
import java.util.Map;

public interface TaskDeadLineService {

    public void setDeadLine(String command, Map<String, List<Task>> tasks);
}
