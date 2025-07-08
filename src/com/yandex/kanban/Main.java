package com.yandex.kanban;
import com.yandex.kanban.module.*;
import com.yandex.kanban.service.TaskManager;

public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");

        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Task 1", "description 1");
        Task task2 = new Task(TaskStatus.IN_PROGRESS,"Task 2 with status", "task is in forever progress");

        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);

        Epic epic = new Epic("Epic1", "Epic 1 Description");
        Subtask subtask1 = new Subtask("com.yandex.kanban.module.Subtask 1", "none");
        Subtask subtask2 = new Subtask(TaskStatus.DONE, "com.yandex.kanban.module.Subtask 2", "none again");

        taskManager.addNewEpic(epic);
        taskManager.addNewSubtask(subtask1, epic);
        taskManager.addNewSubtask(subtask2, epic);

        Epic epic2 = new Epic("Epic 2", "Epic 2 Description");
        Subtask subtask3 = new Subtask("Subtask 3 in epic2", "noen");

        taskManager.addNewEpic(epic2);
        taskManager.addNewSubtask(subtask3, epic);

        System.out.println(taskManager.getAllTasks());
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtasks());

        Subtask subtask4 = new Subtask("Subtask 4 in epic2", "noen");
        taskManager.updateSubtask(subtask4, 4);
        System.out.println(taskManager.getAllEpics());

        taskManager.deleteSubtask(5);
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtasks());
    }
}
