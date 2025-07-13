package com.yandex.kanban.service;

import com.yandex.kanban.module.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager{
    private ArrayList<Task> lastTenViewedTasks = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (lastTenViewedTasks.size() == 10) {
            lastTenViewedTasks.remove(0);
        }
        lastTenViewedTasks.add(task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return new ArrayList<>(lastTenViewedTasks);
    }
}
