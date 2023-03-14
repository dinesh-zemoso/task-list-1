package com.codurance.training.tasks;

import java.util.List;
import java.util.Map;

public interface TaskByIdService {
    public void remove(String strId, Map<String, List<Task>> tasks);
}
