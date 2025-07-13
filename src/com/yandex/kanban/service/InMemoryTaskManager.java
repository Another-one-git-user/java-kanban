package com.yandex.kanban.service;

import com.yandex.kanban.module.Epic;
import com.yandex.kanban.module.Subtask;
import com.yandex.kanban.module.Task;
import com.yandex.kanban.module.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private static int taskId;
    private HashMap<Integer, Task> hashMapTasks;
    private HashMap<Integer, Epic> hashMapEpics;
    private HashMap<Integer, Subtask> hashMapSubtasks;
    private InMemoryHistoryManager history = Managers.getDefaultHistory();

    public InMemoryTaskManager() {
        hashMapTasks = new HashMap<>();
        hashMapEpics = new HashMap<>();
        hashMapSubtasks = new HashMap<>();
        taskId = 0;
    }

    //методы для id задач
    @Override
    public int generateNewTaskId() {
        taskId ++;
        return taskId;
    }

    //гетеры таблиц
    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(hashMapTasks.values());
    }
    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(hashMapEpics.values());
    }
    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(hashMapSubtasks.values());
    }

    @Override
    public InMemoryHistoryManager getHistory() {
        return history;
    }

    //Удаление всего списка задач
    @Override
    public void deleteAllTasks() {
        hashMapTasks.clear();
    }
    @Override
    public void deleteAllEpics() { //нет эпиков, значит, нет подзадач
        hashMapEpics.clear();
        hashMapSubtasks.clear();
    }
    @Override
    public void deleteAllSubtasks() {
        hashMapSubtasks.clear();
        for (Epic epic: hashMapEpics.values()) {
            epic.clearSubtasks();
            setEpicStatus(epic);
        }
    }

    //Добавление в список последних 10
//    private void addToLastTenViewedTasks(Task task){
//         if (lastTenViewedTasks.size() == 10) {
//             lastTenViewedTasks.remove(0);
//         }
//        lastTenViewedTasks.add(task);
//    }

    //Получение задачи по id
    @Override
    public Task getTask(Integer id){
        Task task = hashMapTasks.get(id);
        if (hashMapTasks.containsKey(id)) {
            history.add(task);
        }
        return task;
    }
    @Override
    public Epic getEpic(Integer id){
        Epic epic = hashMapEpics.get(id);
        if (hashMapEpics.containsKey(id)) {
            history.add(epic);
        }
        return epic;
    }
    @Override
    public Subtask getSubtask(Integer id){
        Subtask subtask = hashMapSubtasks.get(id);
        if (hashMapSubtasks.containsKey(id)) {
            history.add(subtask);
        }
        return subtask;
    }

    // Добавление новой задачи в менеджер
    @Override
    public void addNewTask(Task newTask){
        int id = generateNewTaskId();
        newTask.setId(id);
        hashMapTasks.put(id, newTask);
    }
    @Override
    public void addNewEpic(Epic newEpic){
        int id = generateNewTaskId();
        newEpic.setId(id);
        hashMapEpics.put(id, newEpic);
    }
    @Override
    public void addNewSubtask(Subtask newSubtask){
        int id = generateNewTaskId();
        int epicId = newSubtask.getEpicId();
        newSubtask.setId(id);
        addSubtaskToEpic(id, epicId);
        hashMapSubtasks.put(id, newSubtask);
        setEpicStatus(hashMapEpics.get(epicId));
    }

    //Добавляем подзадачу в Эпик
    private void addSubtaskToEpic(int id, int epicId) {
        Epic epic = hashMapEpics.get(epicId);
        if (epic != null) { //Если эпика нет, то подзадача не добавляется никуда
            if (!epic.getSubtasks().contains(id)) {
                epic.addSubtask(id);
            }
        }
    }

    //Получить список всех подзадач Эпика
    @Override
    public ArrayList<Subtask> getEpicSubtasks(Epic epic) {
        ArrayList<Integer> ids = epic.getSubtasks();
        ArrayList<Subtask> copies = new ArrayList<>();
        for (Integer id : ids) {
            copies.add(new Subtask(hashMapSubtasks.get(id)));
        }
        return copies;
    }

    //Метод вычисления статуса эпика
    @Override
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
    @Override
    public void updateTask(Task updateTask) {
        int id = updateTask.getId();
        if (hashMapTasks.containsKey(id)) {
            Task task = hashMapTasks.get(id);
            task.setTaskTitle(updateTask.getTaskTitle());
            task.setDescription(updateTask.getDescription());
            task.setTaskStatus(updateTask.getTaskStatus());
        }
    }
    @Override
    public void updateEpic(Epic updateEpic) {
        int id = updateEpic.getId();
        if (hashMapEpics.containsKey(id)) {
            Epic oldEpic = hashMapEpics.get(id);
            oldEpic.setTaskTitle(updateEpic.getTaskTitle());
            oldEpic.setDescription(updateEpic.getDescription());
        }
    }
    @Override
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
    @Override
    public void deleteTask(int id) {
        if (getTask(id) != null) {
            hashMapTasks.remove(id);
        }
    }
    @Override
    public void deleteEpic(int id) {
        Epic epic = getEpic(id);
        if (epic != null) {
            //Вместе с эпиком удаляются и его подзадачи
            ArrayList<Integer> epicSubtasksIds = epic.getSubtasks();
            for (Integer subId : epicSubtasksIds) {
                hashMapSubtasks.remove(subId);
            }
            hashMapEpics.remove(id);
        }
    }
    @Override
    public void deleteSubtask(int id) {
        Epic epic = hashMapEpics.get(getSubtask(id).getEpicId());
        if (epic != null) {
            epic.removeSubtask(id);
            hashMapSubtasks.remove(id);
            setEpicStatus(epic);
        }
    }

//    @Override
//    public ArrayList<Task> getHistory() {
//        return new ArrayList<Task>(lastTenViewedTasks);
//    }

    @Override
    public String toString() {
        return "TaskManager{" +
                "hashMapTasks=" + hashMapTasks +
                ", hashMapEpics=" + hashMapEpics +
                ", hashMapSubtasks=" + hashMapSubtasks +
                '}';
    }

}
