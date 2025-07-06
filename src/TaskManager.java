import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private static int taskId;
    //private HashMap<TaskStatus, ArrayList<Task>> allTasksByStatus;
    //private HashMap<TaskTypes, ArrayList<Task>> allTasksByTypes;
    private HashMap<Integer, Task> hashMapTasks;
    private HashMap<Integer, Epic> hashMapEpics;
    private HashMap<Integer, Subtask> hashMapSubtasks;


    public HashMap<Integer, Task> getHashMapTasks() {
        return hashMapTasks;
    }

    public HashMap<Integer, Epic> getHashMapEpics() {
        return hashMapEpics;
    }

    public HashMap<Integer, Subtask> getHashMapSubtasks() {
        return hashMapSubtasks;
    }
}
