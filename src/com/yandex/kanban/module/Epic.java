package com.yandex.kanban.module;

import java.util.ArrayList;

public class Epic extends Task{
    ArrayList<Integer> subtasks_ids;

    public Epic(String taskTitle, String description) {
        super(taskTitle, description);
        this.subtasks_ids = new ArrayList<>();
    }
    // Конструктор копирования
    public Epic(Epic epic) {
        super(epic.taskTitle, epic.description);
        this.subtasks_ids = new ArrayList<>(epic.subtasks_ids);
    }

    public ArrayList<Integer> getSubtasks() {
        ArrayList<Integer> copies = new ArrayList<>(subtasks_ids);
        return copies;
    }

    public void setSubtasks(int subId) {
        this.subtasks_ids.add(subId);
    }

    @Override
    public String toString() {

        return "Epic{" +
                "taskStatus=" + taskStatus +
                ", taskTitle='" + taskTitle + '\'' +
                ", id=" + id +
                ", subtasks=" + subtasks_ids +
                ", hashCode=" + hashCode() +
                '}';
    }
}
