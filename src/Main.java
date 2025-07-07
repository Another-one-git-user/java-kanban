public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");

        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Task 1", taskManager.generateNewTaskId());
        Task task2 = new Task(TaskStatus.IN_PROGRESS,"Task 2 with status", taskManager.generateNewTaskId());

        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);

        Epic epic = new Epic("Epic", taskManager.generateNewTaskId());
        Subtask subtask1 = new Subtask("Subtask 1", taskManager.generateNewTaskId(), epic);
        Subtask subtask2 = new Subtask(TaskStatus.DONE, "Subtask 2", taskManager.generateNewTaskId(), epic);

        taskManager.addNewEpic(epic);
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);

        Epic epic2 = new Epic("Epic 2", taskManager.generateNewTaskId());
        Subtask subtask3 = new Subtask("Subtask 3 in epic2", taskManager.generateNewTaskId(), epic2);

        taskManager.addNewEpic(epic2);
        taskManager.addNewSubtask(subtask3);

        System.out.println(taskManager.getHashMapTasks());
        System.out.println(taskManager.getHashMapEpics());
        System.out.println(taskManager.getHashMapSubtasks());

//        taskManager.updateSubtask(new Subtask(TaskStatus.DONE, "Subtask 1 but DONE", 4, epic));
//        System.out.println(taskManager.getHashMapEpics());

        taskManager.deleteSubtask(5);
        System.out.println(taskManager.getHashMapEpics());
        System.out.println(taskManager.getHashMapSubtasks());
    }
}
