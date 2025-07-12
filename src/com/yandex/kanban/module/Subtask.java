package com.yandex.kanban.module;

public class Subtask extends Task{
    private int epicId;

    public Subtask(TaskStatus taskStatus, String taskTitle, String description, int epicId) {
        super(taskStatus, taskTitle, description);
        this.epicId = epicId;
    }
    public Subtask(String taskTitle, String description, int epicId) {
        super(taskTitle, description);
        this.epicId = epicId;
    }
    // Конструктор для обновлённой задачи
    public Subtask(TaskStatus taskStatus, String taskTitle, String description, int epicId, int id) {
        super(taskStatus, taskTitle, description, id);
        this.epicId = epicId;
    }
    // Конструктор копирования
    public Subtask(Subtask subtask) {
        super(subtask.getTaskStatus(), subtask.getTaskTitle(), subtask.getDescription());
        this.epicId = subtask.epicId;
        this.setId(subtask.getId());
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
                ", id=" + getId() +
                ", epicId=" + epicId +
                ", hashCode=" + hashCode() +
                '}';
    }

    public int getEpicId() {
        return epicId;
    }
}
