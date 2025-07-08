package com.yandex.kanban.service;

import com.yandex.kanban.module.Epic;
import com.yandex.kanban.module.Subtask;
import com.yandex.kanban.module.Task;
import com.yandex.kanban.module.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private static int taskId;
    private HashMap<Integer, Task> hashMapTasks;
    private HashMap<Integer, Epic> hashMapEpics;
    private HashMap<Integer, Subtask> hashMapSubtasks;

    public TaskManager() {
        this.hashMapTasks = new HashMap<>();
        this.hashMapEpics = new HashMap<>();
        this.hashMapSubtasks = new HashMap<>();
        taskId = 0;
    }

    //методы для id задач
    public int generateNewTaskId() {
        taskId ++;
        return taskId;
    }

    //гетеры таблиц
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> copies = new ArrayList<>();
        for (Task task : hashMapTasks.values()) {
            copies.add(new Task(task));
        }
        return copies;
    }
    public ArrayList<Epic> getAllEpics() {
        ArrayList<Epic> copies = new ArrayList<>();
        for (Epic epic: hashMapEpics.values()) {
            copies.add(new Epic(epic));
        }
        return copies;
    }
    public ArrayList<Subtask> getAllSubtasks() {
        ArrayList<Subtask> copies = new ArrayList<>();
        for (Subtask subtask : hashMapSubtasks.values()) {
            copies.add(new Subtask(subtask));
        }
        return copies;
    }

    //Удаление всего списка задач
    public void deleteAllTasks() {
        this.hashMapTasks.clear();
    }
    public void deleteAllEpics() { //нет эпиков, значит, нет подзадач
        this.hashMapEpics.clear();
        this.hashMapSubtasks.clear();
    }
    public void deleteAllSubtasks() {
        this.hashMapSubtasks.clear();
        for (Epic epic: this.hashMapEpics.values()) {
            epic.clearSubtasks();
            this.setEpicStatus(epic);
        }
    }

    //Получение задачи по id
    public Task getTaskById(Integer id){
        return new Task(this.hashMapTasks.get(id));
    }
    public Epic getEpicById(Integer id){
        return new Epic(this.hashMapEpics.get(id));
    }
    public Subtask getSubtaskById(Integer id){
        return new Subtask(this.hashMapSubtasks.get(id));
    }

    // Добавление новой задачи в менеджер
    public void addNewTask(Task newTask){
        int id = this.generateNewTaskId();
        newTask.setId(id);
        this.hashMapTasks.put(id, newTask);
    }
    public void addNewEpic(Epic newEpic){
        int id = this.generateNewTaskId();
        newEpic.setId(id);
        this.hashMapEpics.put(id, newEpic);
    }
    public void addNewSubtask(Subtask newSubtask, int epicId){
        int id = this.generateNewTaskId();
        newSubtask.setId(id);
        newSubtask.setEpicId(epicId);
        this.addSubtaskToEpic(id, epicId);
        this.hashMapSubtasks.put(id, newSubtask);
        this.setEpicStatus(hashMapEpics.get(epicId));
    }
    public void addNewSubtask(Subtask newSubtask, Epic epic){
        int id = this.generateNewTaskId();
        newSubtask.setId(id);
        newSubtask.setEpicId(epic.getId());
        this.addSubtaskToEpic(id, epic.getId());
        this.hashMapSubtasks.put(id, newSubtask);
        this.setEpicStatus(epic);
    }

    //Добавляем подзадачу в Эпик
    public void addSubtaskToEpic(int id, int epicId) {
        Epic epic = hashMapEpics.get(epicId);
        if (!epic.getSubtasks().contains(id)) {
            epic.addSubtask(id);
        }
    }

    //Получить список всех подзадач Эпика
    public ArrayList<Subtask> getEpicSubtasks(Epic epic) {
        ArrayList<Integer> ids = epic.getSubtasks();
        ArrayList<Subtask> copies = new ArrayList<>();
        for (Integer id : ids) {
            copies.add(new Subtask(hashMapSubtasks.get(id)));
        }
        return copies;
    }

    //Метод вычисления статуса эпика
    public void setEpicStatus(Epic epic) {
        if (epic.getSubtasks().isEmpty()) {
            epic.setTaskStatus(TaskStatus.NEW);
        } else {
            boolean isAllNew = true;
            boolean isAllDone = true;
            for (Subtask subtask : hashMapSubtasks.values()) {
                if (subtask.getEpicId() == epic.getId()) {
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
            }
            if (isAllNew) {
                epic.setTaskStatus(TaskStatus.NEW);
            } else if (isAllDone) {
                epic.setTaskStatus(TaskStatus.DONE);
            } else {
                epic.setTaskStatus(TaskStatus.IN_PROGRESS);
            }
        }
    }

    /*
    Обновление задачи
    */
    public void updateTask(Task updateTask, int id) {
        if (this.hashMapTasks.containsKey(id)) {
            Task task = this.hashMapTasks.get(id);
            task.setTaskTitle(updateTask.getTaskTitle());
            task.setDescription(updateTask.getDescription());
            task.setTaskStatus(updateTask.getTaskStatus());
        }
    }
    public void updateEpic(Epic updateEpic, int id) {
        if (this.hashMapEpics.containsKey(id)) {
            Epic oldEpic = this.hashMapEpics.get(id);
            oldEpic.setTaskTitle(updateEpic.getTaskTitle());
            oldEpic.setDescription(updateEpic.getDescription());
        }
    }
    public void updateSubtask(Subtask updateSubtask, int id) {
        if (this.hashMapSubtasks.containsKey(id)) {
            Subtask subtask = this.hashMapSubtasks.get(id);
            subtask.setTaskTitle(updateSubtask.getTaskTitle());
            subtask.setDescription(updateSubtask.getDescription());
            subtask.setTaskStatus(updateSubtask.getTaskStatus());
            this.setEpicStatus(hashMapEpics.get(subtask.getEpicId()));
        }
    }

    // Удаление задачи из списка
    public void deleteTask(int id) {
        this.hashMapTasks.remove(id);
    }
    public void deleteEpic(int id) {
        //Вместе с эпиком удаляются и его подзадачи
        ArrayList<Integer> epicSubtasksIds = this.getEpicById(id).getSubtasks();
        for (Integer subId : epicSubtasksIds) {
            this.hashMapSubtasks.remove(subId);
        }
        this.hashMapEpics.remove(id);
    }
    public void deleteSubtask(int id) {
        Epic epic = hashMapEpics.get(this.getSubtaskById(id).getEpicId());
        epic.removeSubtask(id);
        this.hashMapSubtasks.remove(id);
    }

    @Override
    public String toString() {
        return "TaskManager{" +
                "hashMapTasks=" + hashMapTasks +
                ", hashMapEpics=" + hashMapEpics +
                ", hashMapSubtasks=" + hashMapSubtasks +
                '}';
    }

}
