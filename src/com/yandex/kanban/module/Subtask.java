package com.yandex.kanban.module;

public class Subtask extends Task{
    private int epic_id;

    public Subtask(TaskStatus taskStatus, String taskTitle, String description, int epic_id) {
        super(taskStatus, taskTitle, description);
        this.epic_id = epic_id;
//        epic.addSubtask(this); //Подзадача автоматически добавляется в список своего Эпика
//        epic.setEpicStatus(); //После добавления подзадачи, эпик переопределяет свой статус
    }

    public Subtask(String taskTitle, String description, int epic_id) {
        super(taskTitle, description);
        this.epic_id = epic_id;
//        epic.addSubtask(this); //Подзадача автоматически добавляется в список своего Эпика
//        epic.setEpicStatus(); //После добавления подзадачи, эпик переопределяет свой статус
    }

    // Конструктор копирования
    public Subtask(Subtask subtask) {
        super(subtask.taskStatus, subtask.taskTitle, subtask.description);
        this.epic_id = subtask.epic_id;
    }

//    public Epic getEpic() {
//        return epic;
//    }

    @Override
    public String toString() {
        return "Subtask{" +
                "taskStatus=" + taskStatus +
                ", taskTitle='" + taskTitle + '\'' +
                ", id=" + id +
                ", epic_id=" + epic_id +
                ", hashCode=" + hashCode() +
                '}';
    }
}
