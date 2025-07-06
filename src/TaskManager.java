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
    private int getLastGeneratedId() {
        return taskId;
    }
    public int generateNewTaskId() {
        return getLastGeneratedId() + 1;
    }

    //гетеры таблиц
    public HashMap<Integer, Task> getHashMapTasks() {
        return hashMapTasks;
    }
    public HashMap<Integer, Epic> getHashMapEpics() {
        return hashMapEpics;
    }
    public HashMap<Integer, Subtask> getHashMapSubtasks() {
        return hashMapSubtasks;
    }

    //Удаление всего списка задач
    public void deleteAllTasks() {
        this.getHashMapTasks().clear();
    }
    public void deleteAllEpics() {
        this.getHashMapEpics().clear();
    }
    public void deleteAllSubtasks() {
        this.getHashMapSubtasks().clear();
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
    public void addNewTask(Task task){
        this.getHashMapTasks().put(generateNewTaskId(), task);
    }
    public void addNewEpic(Epic epic){
        this.getHashMapEpics().put(generateNewTaskId(), epic);
    }
    public void addNewSubtask(Subtask subtask){
        this.getHashMapSubtasks().put(generateNewTaskId(), subtask);
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
        this.getHashMapTasks().put(updateTask.getId(), updateTask);
    }
    public void updateEpic(Epic updateEpic) {
        this.getHashMapEpics().put(updateEpic.getId(), updateEpic);
    }
    public void updateSubtask(Subtask updateSubtask) {
        this.getHashMapTasks().put(updateSubtask.getId(), updateSubtask);
    }

    // Удаление задачи из списка
    public void deleteTask(int id) {
        this.getHashMapTasks().remove(id);
    }
    public void deleteEpic(int id) {
        this.getHashMapEpics().remove(id);
    }
    public void deleteSubtask(int id) {
        this.getHashMapSubtasks().remove(id);
    }
}
