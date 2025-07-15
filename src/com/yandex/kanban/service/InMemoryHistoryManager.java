package com.yandex.kanban.service;

import com.yandex.kanban.module.Task;

import java.util.LinkedList;

public class InMemoryHistoryManager implements HistoryManager{
    private LinkedList<Task> lastTenViewedTasks = new LinkedList<>();
    private final static int MAX_SIZE = 10;

    @Override
    public void add(Task task) {
        if (lastTenViewedTasks.size() == MAX_SIZE) {
            lastTenViewedTasks.removeFirst();
        }
        lastTenViewedTasks.add(new Task(task));
    }

    @Override
    public LinkedList<Task> getHistory() {
        return new LinkedList<>(lastTenViewedTasks);
    }
}
