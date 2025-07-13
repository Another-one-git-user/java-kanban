package com.yandex.kanban.service;

public class Managers {
    private TaskManager taskManager;

    public Managers(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public TaskManager getDefault() {
        return taskManager;
    }
}
