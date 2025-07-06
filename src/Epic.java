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
        this.taskStatus = TaskStatus.NEW; //Каждый новый эпик будет иметь статус NEW, ведь подзадачи можно создать только при существовании Эпика
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Subtask subtask) {
        this.getSubtasks().add(subtask);
    }

    public void setEpicStatus() {
        if (this.subtasks.isEmpty()) {
            this.taskStatus = TaskStatus.NEW;
        } else {
            boolean isAllNew = true;
            boolean isAllDone = true;
            for (Subtask subtask : subtasks) {
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
            if (isAllNew) {
                this.taskStatus = TaskStatus.NEW;
            } else if (isAllDone) {
                this.taskStatus = TaskStatus.DONE;
            } else {
                this.taskStatus = TaskStatus.IN_PROGRESS;
            }
        }
    }

    public void setSubtasks(ArrayList<Subtask> subtasks) {
        this.subtasks = subtasks;
    }
}
