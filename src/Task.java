import java.util.Objects;

public class Task {
    TaskStatus taskStatus;
    String taskTitle;
    final int id;

    public Task(TaskStatus taskStatus, String taskTitle, int taskId) {
        this.taskStatus = taskStatus;
        this.taskTitle = taskTitle;
        this.id = taskId;
    }
    public Task(String taskTitle, int taskId) {
        this.taskTitle = taskTitle;
        this.id = taskId;
        this.taskStatus = TaskStatus.NEW;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return this.id == task.id;
        //return id == task.id && taskStatus == task.taskStatus && Objects.equals(taskTitle, task.taskTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskStatus=" + taskStatus +
                ", taskTitle='" + taskTitle + '\'' +
                ", id=" + id +
                '}';
    }
}
