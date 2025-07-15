package com.yandex.kanban.service;

public class Managers {
    private Managers() { //запрещаем создание объектов
    }

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static InMemoryHistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
