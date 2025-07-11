package com.yandex.kanban.module;

public class Subtask extends Task{
    private int epicId;

    public Subtask(TaskStatus taskStatus, String taskTitle, String description) {
        super(taskStatus, taskTitle, description);
    }
    public Subtask(String taskTitle, String description) {
        super(taskTitle, description);
    }
    // Конструктор для обновлённой задачи
    public Subtask(TaskStatus taskStatus, String taskTitle, String description, int id) {
        super(taskStatus, taskTitle, description, id);
    }
    // Конструктор копирования
    public Subtask(Subtask subtask) {
        super(subtask.getTaskStatus(), subtask.getTaskTitle(), subtask.getDescription());
        this.epicId = subtask.epicId;
        this.id = subtask.id;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "taskStatus=" + getTaskStatus() +
                ", taskTitle='" + getTaskTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + id +
                ", epicId=" + epicId +
                ", hashCode=" + hashCode() +
                '}';
    }

    public int getEpicId() {
        return epicId;
    }
}
