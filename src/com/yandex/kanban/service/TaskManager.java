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
        hashMapTasks = new HashMap<>();
        hashMapEpics = new HashMap<>();
        hashMapSubtasks = new HashMap<>();
        taskId = 0;
    }

    //методы для id задач
    public int generateNewTaskId() {
        taskId ++;
        return taskId;
    }

    //гетеры таблиц
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(hashMapTasks.values());
    }
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(hashMapEpics.values());
    }
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(hashMapSubtasks.values());
    }

    //Удаление всего списка задач
    public void deleteAllTasks() {
        hashMapTasks.clear();
    }
    public void deleteAllEpics() { //нет эпиков, значит, нет подзадач
        hashMapEpics.clear();
        hashMapSubtasks.clear();
    }
    public void deleteAllSubtasks() {
        hashMapSubtasks.clear();
        for (Epic epic: hashMapEpics.values()) {
            epic.clearSubtasks();
            setEpicStatus(epic);
        }
    }

    //Получение задачи по id
    public Task getTaskById(Integer id){
        return hashMapTasks.get(id);
    }
    public Epic getEpicById(Integer id){
        return hashMapEpics.get(id);
    }
    public Subtask getSubtaskById(Integer id){
        return hashMapSubtasks.get(id);
    }

    // Добавление новой задачи в менеджер
    public void addNewTask(Task newTask){
        int id = generateNewTaskId();
        newTask.setId(id);
        hashMapTasks.put(id, newTask);
    }
    public void addNewEpic(Epic newEpic){
        int id = generateNewTaskId();
        newEpic.setId(id);
        hashMapEpics.put(id, newEpic);
    }
    public void addNewSubtask(Subtask newSubtask, int epicId){
        int id = generateNewTaskId();
        newSubtask.setId(id);
        newSubtask.setEpicId(epicId);
        addSubtaskToEpic(id, epicId);
        hashMapSubtasks.put(id, newSubtask);
        setEpicStatus(hashMapEpics.get(epicId));
    }
    public void addNewSubtask(Subtask newSubtask, Epic epic){
        int id = generateNewTaskId();
        newSubtask.setId(id);
        newSubtask.setEpicId(epic.getId());
        addSubtaskToEpic(id, epic.getId());
        hashMapSubtasks.put(id, newSubtask);
        setEpicStatus(epic);
    }

    //Добавляем подзадачу в Эпик
    private void addSubtaskToEpic(int id, int epicId) {
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
    public void updateTask(Task updateTask) {
        int id = updateTask.getId();
        if (hashMapTasks.containsKey(id)) {
            Task task = hashMapTasks.get(id);
            task.setTaskTitle(updateTask.getTaskTitle());
            task.setDescription(updateTask.getDescription());
            task.setTaskStatus(updateTask.getTaskStatus());
        }
    }
    public void updateEpic(Epic updateEpic) {
        int id = updateEpic.getId();
        if (hashMapEpics.containsKey(id)) {
            Epic oldEpic = hashMapEpics.get(id);
            oldEpic.setTaskTitle(updateEpic.getTaskTitle());
            oldEpic.setDescription(updateEpic.getDescription());
        }
    }
    public void updateSubtask(Subtask updateSubtask) {
        int id = updateSubtask.getId();
        if (hashMapSubtasks.containsKey(id)) {
            Subtask subtask = hashMapSubtasks.get(id);
            subtask.setTaskTitle(updateSubtask.getTaskTitle());
            subtask.setDescription(updateSubtask.getDescription());
            subtask.setTaskStatus(updateSubtask.getTaskStatus());
            setEpicStatus(hashMapEpics.get(subtask.getEpicId()));
        }
    }

    // Удаление задачи из списка
    public void deleteTask(int id) {
        hashMapTasks.remove(id);
    }
    public void deleteEpic(int id) {
        //Вместе с эпиком удаляются и его подзадачи
        ArrayList<Integer> epicSubtasksIds = getEpicById(id).getSubtasks();
        for (Integer subId : epicSubtasksIds) {
            hashMapSubtasks.remove(subId);
        }
        hashMapEpics.remove(id);
    }
    public void deleteSubtask(int id) {
        Epic epic = hashMapEpics.get(getSubtaskById(id).getEpicId());
        epic.removeSubtask(id);
        hashMapSubtasks.remove(id);
        setEpicStatus(epic);
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
