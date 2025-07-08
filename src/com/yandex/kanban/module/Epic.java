package com.yandex.kanban.module;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{

    private ArrayList<Integer> subtasks_ids;


    public Epic(String taskTitle, String description) {
        super(taskTitle, description);
        this.subtasks_ids = new ArrayList<>();
    }

    // Конструктор копирования
    public Epic(Epic epic) {
        super(epic.taskTitle, epic.description);
        this.subtasks_ids = (ArrayList<Integer>) List.copyOf(epic.subtasks_ids);
    }

    public ArrayList<Integer> getSubtasks() {
        ArrayList<Integer> copies = new ArrayList<>(subtasks_ids);
        return copies;
    }

    public void addSubtask(Subtask newSubtask) {
        int newSubId = newSubtask.getId();
        if (!subtasks_ids.contains(newSubId)) {
            this.subtasks_ids.add(newSubtask.getId());
        }
    }


    public void setSubtasks(ArrayList<Integer> subtasks_ids) {
        this.subtasks_ids = subtasks_ids;
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
