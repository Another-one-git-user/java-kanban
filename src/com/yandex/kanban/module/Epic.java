package com.yandex.kanban.module;

import java.util.ArrayList;

public class Epic extends Task{

    private ArrayList<Subtask> subtasks;


    public Epic(String taskTitle, String description) {
        super(taskTitle, description);
        this.subtasks = new ArrayList<>();
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Subtask newSubtask) {
        boolean isNew = true;
        for (int i = 0; i < subtasks.size(); i++) {
            if (newSubtask.equals(subtasks.get(i))) {
                subtasks.set(i, newSubtask);
                isNew = false;
                break;
            }
        }
        if (isNew) {
            this.subtasks.add(newSubtask);
        }
    }

    public void setEpicStatus() {
        if (this.subtasks.isEmpty()) {
            this.taskStatus = TaskStatus.NEW;
        } else {
            boolean isAllNew = true;
            boolean isAllDone = true;
            for (Subtask subtask : subtasks) {
                if (subtask.getTaskStatus() != TaskStatus.NEW) {
                    isAllNew = false;
                }
                if (subtask.getTaskStatus() != TaskStatus.DONE) {
                    isAllDone = false;
                }
                if (!isAllNew && !isAllDone) {
                    break;
                }
            }
            if (isAllNew) {
                this.taskStatus = TaskStatus.NEW;
            } else if (isAllDone) {
                this.taskStatus = TaskStatus.DONE;
            } else {
                this.taskStatus = TaskStatus.IN_PROGRESS;
            }
        }
    }

    public void setSubtasks(ArrayList<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    @Override
    public String toString() {
        String subtasksString = "{ ";
        for (Subtask subtask : subtasks) {
            subtasksString += subtask.getId() + " ";
        }
        subtasksString += "}";

        return "Epic{" +
                "taskStatus=" + taskStatus +
                ", taskTitle='" + taskTitle + '\'' +
                ", id=" + id +
                ", subtasks=" + subtasksString +
                ", hashCode=" + hashCode() +
                '}';
    }
}
