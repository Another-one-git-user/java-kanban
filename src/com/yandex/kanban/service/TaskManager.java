package com.yandex.kanban.service;

import com.yandex.kanban.module.Epic;
import com.yandex.kanban.module.Subtask;
import com.yandex.kanban.module.Task;

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
        taskId += 1;
        return taskId;
    }

    //гетеры таблиц
    public ArrayList<Task> getTasks() {
        ArrayList<Task> copies = new ArrayList<>();
        for (Task task : hashMapTasks.values()) {
            copies.add(new Task(task));
        }
        return copies;
    }
    public ArrayList<Epic> getEpics() {
        ArrayList<Epic> copies = new ArrayList<>();
        for (Epic epic: hashMapEpics.values()) {
            copies.add(new Epic(epic));
        }
        return copies;
    }
    public ArrayList<Subtask> getSubtasks() {
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
    }

    //Получение задачи по id
    public Task getTaskById(Integer id){
        return this.getHashMapTasks().get(id);
    }
    public Epic getEpicById(Integer id){
        return this.getHashMapEpics().get(id);
    }
    public Subtask getSubtaskById(Integer id){
        return this.getHashMapSubtasks().get(id);
    }

    // Добавление новой задачи в список
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
    public void addNewSubtask(Subtask newSubtask){
        int id = this.generateNewTaskId();
        newSubtask.setId(id);
        this.hashMapSubtasks.put(id, newSubtask);
    }

    //Получить список всех подзадач Эпика
    public ArrayList<Subtask> getSubtasks(Epic epic) {
        return epic.getSubtasks();
    }

    /*
    Обновление задачи
    (Если я правильно понял, то для обновления будет создаа новая задача с id уже существующей,
    которую хотим обновить. Другими словами id таким задачам задаём вручную, а не через taskManager)
    */
    public void updateTask(Task updateTask) {
        int id = updateTask.getId();
        if (this.getHashMapTasks().containsKey(id)) {
            this.getHashMapTasks().put(updateTask.getId(), updateTask);
        }
    }
    public void updateEpic(Epic updateEpic) {
        int id = updateEpic.getId();
        if (this.getHashMapEpics().containsKey(id)) {
            //При обновлении эпика, обновлённый (новый объект по сути) не будет в себе иметь связь с подзадачами
            updateEpic.setSubtasks(this.getEpicById(id).getSubtasks());
            updateEpic.setEpicStatus();//Вычисляем статус для нового эпика
            this.getHashMapEpics().put(updateEpic.getId(), updateEpic);
        }

    }
    public void updateSubtask(Subtask updateSubtask) {
        int id = updateSubtask.getId();
        if (this.getHashMapSubtasks().containsKey(id)) {
            this.getHashMapSubtasks().put(updateSubtask.getId(), updateSubtask);
            updateSubtask.getEpic().addSubtask(updateSubtask);//Обновляем статус эпика и список его подзадач
        }
    }

    // Удаление задачи из списка
    public void deleteTask(int id) {
        this.getHashMapTasks().remove(id);
    }
    public void deleteEpic(int id) {
        //Вместе с эпиком удаляются и его подзадачи
        ArrayList<Subtask> epicSubtasks= this.getEpicById(id).getSubtasks();
        HashMap<Integer, Subtask> allSubtasks = this.getHashMapSubtasks();
        for (Subtask subtask : epicSubtasks) {
            allSubtasks.remove(subtask.getId());
        }
        this.getHashMapEpics().remove(id);
    }
    public void deleteSubtask(int id) {
        Epic epic = this.getSubtaskById(id).getEpic();
        ArrayList<Subtask> epicSubtasks= epic.getSubtasks();
        HashMap<Integer, Subtask> allSubtasks = this.getHashMapSubtasks();
        for (int i = 0; i < epicSubtasks.size(); i++) {
            if (epicSubtasks.get(i).equals(allSubtasks.get(id))) {
                epicSubtasks.remove(i);
                epic.setEpicStatus();
                break;
            }
        }
        allSubtasks.remove(id);
    }

    @Override
    public String toString() {
        return "com.yandex.kanban.service.TaskManager{" +
                "hashMapTasks=" + hashMapTasks +
                ", hashMapEpics=" + hashMapEpics +
                ", hashMapSubtasks=" + hashMapSubtasks +
                '}';
    }
}
