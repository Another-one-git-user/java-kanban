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
        taskId += 1;
        return taskId;
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
    public void addNewTask(Task newTask){
        this.getHashMapTasks().put(newTask.getId(), newTask);
    }
    public void addNewEpic(Epic newEpic){
        this.getHashMapEpics().put(newEpic.getId(), newEpic);
    }
    public void addNewSubtask(Subtask newSubtask){
        this.getHashMapSubtasks().put(newSubtask.getId(), newSubtask);
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
            updateSubtask.getEpic().setEpicStatus();//Обновляем статус эпика после обновления его подзадачи
        }
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
