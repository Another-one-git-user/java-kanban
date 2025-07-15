package com.yandex.kanban.service;

import com.yandex.kanban.module.Task;

import java.util.LinkedList;

public interface HistoryManager {
    void add(Task task);
    LinkedList<Task> getHistory();
}
