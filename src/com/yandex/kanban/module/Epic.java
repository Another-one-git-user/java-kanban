package com.yandex.kanban.module;

import java.util.ArrayList;

public class Epic extends Task{
    private ArrayList<Integer> subtasksIds;

    public Epic(String taskTitle, String description) {
        super(taskTitle, description);
        subtasksIds = new ArrayList<>();
    }
    // Конструктор для обновлённой задачи
    public Epic(String taskTitle, String description, int id) {
        super(taskTitle, description, id);
        subtasksIds = new ArrayList<>();
    }
    // Конструктор копирования
    public Epic(Epic epic) {
        super(epic.getTaskTitle(), epic.getDescription());
        this.setTaskStatus(epic.getTaskStatus());
        this.id = epic.id;
        this.subtasksIds = new ArrayList<>(epic.subtasksIds);
    }

    public ArrayList<Integer> getSubtasks() {
        return new ArrayList<>(subtasksIds);

    }

    public void removeSubtask(int subId) {
        subtasksIds.remove(Integer.valueOf(subId));
    }

    public void clearSubtasks() {
        subtasksIds.clear();
    }

    public void addSubtask(int subId) {
        subtasksIds.add(subId);
    }

    @Override
    public String toString() {

        return "Epic{" +
                "taskStatus=" + getTaskStatus() +
                ", taskTitle='" + getTaskTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + id +
                ", subtasks=" + subtasksIds +
                ", hashCode=" + hashCode() +
                '}';
    }
}
