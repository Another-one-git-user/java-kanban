package com.yandex.kanban.module;

public class Subtask extends Task{
    int epicId;

    public Subtask(TaskStatus taskStatus, String taskTitle, String description) {
        super(taskStatus, taskTitle, description);
    }

    public Subtask(String taskTitle, String description) {
        super(taskTitle, description);
    }

    // Конструктор копирования
    public Subtask(Subtask subtask) {
        super(subtask.taskStatus, subtask.taskTitle, subtask.description);
        this.epicId = subtask.epicId;
        this.id = subtask.id;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "taskStatus=" + taskStatus +
                ", taskTitle='" + taskTitle + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", epicId=" + epicId +
                ", hashCode=" + hashCode() +
                '}';
    }

    public int getEpicId() {
        return epicId;
    }
}
