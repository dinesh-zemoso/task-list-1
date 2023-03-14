package com.codurance.training.tasks;

import java.util.List;
import java.util.Map;

public interface TaskShowByDateService {
    public void showByDate(String date, Map<String, List<Task>> tasks);
}
