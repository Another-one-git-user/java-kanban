import java.util.ArrayList;

public class Epic extends Task{
    private ArrayList<Subtask> subtasks;
    public Epic(TaskStatus taskStatus, String taskTitle, int taskId) {
        super(taskStatus, taskTitle, taskId);
    }
}
