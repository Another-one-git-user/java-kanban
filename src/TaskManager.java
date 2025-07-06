import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private static int taskId;
    //private HashMap<TaskStatus, ArrayList<Task>> allTasksByStatus;
    //private HashMap<TaskTypes, ArrayList<Task>> allTasksByTypes;
    private HashMap<Integer, Task> hashMapTasks;
    private HashMap<Integer, Epic> hashMapEpics;
    private HashMap<Integer, Subtask> hashMapSubtasks;

    private static int getTaskId() {
        return taskId;
    }


    public HashMap<Integer, Task> getHashMapTasks() {
        return hashMapTasks;
    }

    public HashMap<Integer, Epic> getHashMapEpics() {
        return hashMapEpics;
    }

    public HashMap<Integer, Subtask> getHashMapSubtasks() {
        return hashMapSubtasks;
    }

    public void deleteAllTasks() {
        this.getHashMapTasks().clear();
    }
    public void deleteAllEpics() {
        this.getHashMapEpics().clear();
    }
    public void deleteAllSubtasks() {
        this.getHashMapSubtasks().clear();
    }

    public Task getTaskById(Integer id){
        return this.getHashMapTasks().get(id);
    }
    public Task getEpicById(Integer id){
        return this.getHashMapEpics().get(id);
    }
    public Task getSubtaskById(Integer id){
        return this.getHashMapSubtasks().get(id);
    }

    private static int generateNewTaskId() {
        return getTaskId() + 1;
    }

    public void addNewTask(Task task){
        this.getHashMapTasks().put(generateNewTaskId(), task);
    }
    public void addNewEpic(Epic epic){
        this.getHashMapEpics().put(generateNewTaskId(), epic);
    }
    public void addNewSubtask(Subtask subtask){
        this.getHashMapSubtasks().put(generateNewTaskId(), subtask);
    }

    public void updateTask(Task newTask) {
        this.getHashMapTasks().put(newTask.getId(), newTask);
    }
    public void updateTask(Epic newEpic) {
        this.getHashMapEpics().put(newEpic.getId(), newEpic);
    }
    public void updateTask(Subtask newSubtask) {
        this.getHashMapTasks().put(newSubtask.getId(), newSubtask);
    }
}
