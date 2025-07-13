package com.yandex.kanban.service;

import com.yandex.kanban.module.Epic;
import com.yandex.kanban.module.Subtask;
import com.yandex.kanban.module.Task;

import java.util.ArrayList;

public interface TaskManager {
    //методы для id задач
    int generateNewTaskId();

    //гетеры таблиц
    ArrayList<Task> getAllTasks();

    ArrayList<Epic> getAllEpics();

    ArrayList<Subtask> getAllSubtasks();

    //Удаление всего списка задач
    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubtasks();

    //Получение задачи по id
    Task getTask(Integer id);

    Epic getEpic(Integer id);

    Subtask getSubtask(Integer id);

    // Добавление новой задачи в менеджер
    void addNewTask(Task newTask);

    void addNewEpic(Epic newEpic);

    void addNewSubtask(Subtask newSubtask);

    //Получить список всех подзадач Эпика
    ArrayList<Subtask> getEpicSubtasks(Epic epic);

    //Метод вычисления статуса эпика
    void setEpicStatus(Epic epic);

    /*
        Обновление задачи
        */
    void updateTask(Task updateTask);

    void updateEpic(Epic updateEpic);

    void updateSubtask(Subtask updateSubtask);

    // Удаление задачи из списка
    void deleteTask(int id);

    void deleteEpic(int id);

    void deleteSubtask(int id);

    //HistoryManager getHistory();

    @Override
    String toString();
}
