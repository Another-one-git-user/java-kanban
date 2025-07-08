package com.yandex.kanban.module;

import java.util.Objects;

public class Task {
    TaskStatus taskStatus;
    String taskTitle;
    String description;
    int id; //задаётся через сеттер. Значит, не может быть final

    public Task(TaskStatus taskStatus, String taskTitle, String description) {
        this.taskStatus = taskStatus;
        this.taskTitle = taskTitle;
        this.description = description;
    }
    public Task(String taskTitle, String description) {
        this.taskTitle = taskTitle;
        this.description = description;
        this.taskStatus = TaskStatus.NEW;
    }

    // Конструктор копирования
    public Task(Task other) {
        this.id = other.id;
        this.taskTitle = other.taskTitle;
        this.description = other.description;
        this.taskStatus = other.taskStatus;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return this.id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskStatus=" + taskStatus +
                ", taskTitle='" + taskTitle + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", hashCode=" + hashCode() +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
