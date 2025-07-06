public class Subtask extends Task{
    private Epic epic;

    public Subtask(TaskStatus taskStatus, String taskTitle, int taskId, Epic epic) {
        super(taskStatus, taskTitle, taskId);
        this.epic = epic;
        epic.addSubtask(this); //Подзадача автоматически добавляется в список своего Эпика
    }
}
