package com.yandex.kanban.module;

public class Subtask extends Task{
    private Epic epic;

    public Subtask(TaskStatus taskStatus, String taskTitle, int taskId, Epic epic) {
        super(taskStatus, taskTitle, taskId);
        this.epic = epic;
        epic.addSubtask(this); //Подзадача автоматически добавляется в список своего Эпика
        epic.setEpicStatus(); //После добавления подзадачи, эпик переопределяет свой статус
    }

    public Subtask(String taskTitle, int taskId, Epic epic) {
        super(taskTitle, taskId);
        this.epic = epic;
        epic.addSubtask(this); //Подзадача автоматически добавляется в список своего Эпика
        epic.setEpicStatus(); //После добавления подзадачи, эпик переопределяет свой статус
    }

    public Epic getEpic() {
        return epic;
    }

    @Override
    public String toString() {
        return "com.yandex.kanban.module.Subtask{" +
                "taskStatus=" + taskStatus +
                ", taskTitle='" + taskTitle + '\'' +
                ", id=" + id +
                ", epic=" + epic.getId() +
                ", hashCode=" + hashCode() +
                '}';
    }
}
