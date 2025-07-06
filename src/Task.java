public class Task {
    private TaskStatus taskStatus;
    private String taskTitle;
    private final int taskId;

    public Task(TaskStatus taskStatus, String taskTitle, int taskId) {
        this.taskStatus = taskStatus;
        this.taskTitle = taskTitle;
        this.taskId = taskId;
    }
}
