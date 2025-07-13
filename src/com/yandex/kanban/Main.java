package com.yandex.kanban;
import com.yandex.kanban.module.*;
import com.yandex.kanban.service.*;


public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");
        TaskManager manager = Managers.getDefault();

        Task task1 = new Task("Task 1", "description 1");
        Epic epic = new Epic("Epic1", "Epic 1 Description");
        Subtask subtask1 = new Subtask("Subtask 1", "none", 2);
        manager.addNewTask(task1);
        manager.addNewEpic(epic);
        manager.addNewSubtask(subtask1);

        printAllTasks(manager);
    }
    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);

            for (Task task : manager.getEpicSubtasks(epic)) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}
