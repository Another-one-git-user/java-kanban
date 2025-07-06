import java.util.ArrayList;

public class Epic extends Task{
    private TaskStatus taskStatus;
    private ArrayList<Subtask> subtasks;

//    public Epic(TaskStatus taskStatus, String taskTitle, int taskId) {
//        super(taskStatus, taskTitle, taskId);
//        this.subtasks = new ArrayList<>();
//    }

    public Epic(String taskTitle, int taskId) {
        super(taskTitle, taskId);
        this.subtasks = new ArrayList<>();
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Subtask subtask) {
        this.getSubtasks().add(subtask);
    }
}
