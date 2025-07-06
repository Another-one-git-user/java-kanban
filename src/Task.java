public class Task {
    private TaskStatus taskStatus;
    private String taskTitle;
    private final int id;

    public Task(TaskStatus taskStatus, String taskTitle, int taskId) {
        this.taskStatus = taskStatus;
        this.taskTitle = taskTitle;
        this.id = taskId;
    }

    public int getId() {
        return id;
    }
}
