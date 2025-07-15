package com.yandex.kanban;

import com.yandex.kanban.module.*;
import com.yandex.kanban.service.HistoryManager;
import com.yandex.kanban.service.Managers;
import com.yandex.kanban.service.TaskManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    private TaskManager manager;

    @BeforeEach
    void beforeEach() {
        manager = Managers.getDefault();
    }

    @Test
    void shouldBeEqualSinceSameIdTasks() {

        Task task1 = new Task("title", "description");
        manager.addNewTask(task1);
        int task1Id = task1.getId();
        Task task2 = new Task(TaskStatus.DONE,"different title", "different description", task1Id);
        assertEquals(task1, task2, "Задачи не совпадают.");
    }

    @Test
    void shouldBeEqualSinceSameId() {

        Task task1 = new Task("title", "description");
        manager.addNewTask(task1);
        int task1Id = task1.getId();
        Epic epic = new Epic("epic", "different description", task1Id);
        Subtask subtask = new Subtask(TaskStatus.IN_PROGRESS,"subtask", "sub description", 5, task1Id);
        assertEquals(task1, epic, "Задачи не совпадают.");
        assertEquals(task1, subtask, "Задачи не совпадают.");
        assertEquals(epic, subtask, "Задачи не совпадают.");
    }

    @Test
    void shouldBeNotEqualsEvenThoSubtaskHasSameIdAsEpic() {

        //По сути, чтоб это проверить, нужно создать подзадачу равную эпику и добавить её в менеджер
        //Создаём epic и присваеваем ему id через manager
        Epic epic = new Epic("epic", "different description");
        manager.addNewEpic(epic);
        int epicId = epic.getId();
        //Создаём subtask, но даём ему id - epicId, что в целом эквивалентно тому, что эпик добавляется сам в себя
        Subtask subtask = new Subtask(TaskStatus.IN_PROGRESS, "subtask", "sub description", epicId, epicId);
        //Должны быть равны до добавление подзадачи в менеджер
        assertEquals(epic, subtask);
        // Подзадаче был передан id эпика, что автоматически должно было сделать её равным с эпиком.
        // Но, т.к. добавление новой подзадачи генерирует ей новый id, то неважно какой id мы задавали подзадаче, у неё будет
        // свой уникальный id после добавления в менеджер
        manager.addNewSubtask(subtask);
        assertNotEquals(epic, subtask, "Задачи совпадают.");
    }

    @Test
    void shouldBeNullEvenThoEpicHasSameIdAsSubtask() {


        Epic epic = new Epic("epic", "different description");
        manager.addNewEpic(epic);
        int epicId = epic.getId();
        Subtask subtask = new Subtask(TaskStatus.IN_PROGRESS, "subtask", "sub description", epicId);

        manager.addNewSubtask(subtask);
        int subtaskId = subtask.getId();

        Epic newEpic = new Epic("epicToSubtask", "description", subtaskId);
        manager.updateEpic(newEpic);

        assertNull(manager.getEpic(subtaskId), "такой эпик есть!");
    }

    @Test
    void shouldNotBeNullWhenNewManger() {

        TaskManager manager2 = Managers.getDefault();

        assertNotNull(manager);
        assertNotNull(manager2);
    }

    @Test
    void shouldNotBeNullWhenNewTasks() {


        Task task = new Task("title", "description");
        Epic epic = new Epic("epic", "different description");
        Subtask subtask = new Subtask("subtask", "description", 2);
        manager.addNewTask(task);
        manager.addNewEpic(epic);
        manager.addNewSubtask(subtask);

        assertNotNull(manager.getTask(1));
        assertNotNull(manager.getEpic(2));
        assertNotNull(manager.getSubtask(3));
    }

    @Test
    void shouldReturnTitleAndDescriptionOfTaskInManager() {


        Task taskInManager = new Task("titleInManager", "descriptionInManager");
        Task taskNotInManager = new Task("titleNotInManager", "descriptionNotInManager", 1);
        manager.addNewTask(taskInManager);

        assertEquals("titleInManager", manager.getTask(1).getTaskTitle(), "Не тот заголовок!");
        assertEquals("descriptionInManager", manager.getTask(1).getDescription(), "Не то описание!");
    }

    @Test
    void shouldFieldsBeTheEqualBeforeAndAfter() {


        Task taskBefore = new Task(TaskStatus.IN_PROGRESS,"titleInManager", "descriptionInManager");
        String titleBefore = taskBefore.getTaskTitle();
        String descriptionBefore = taskBefore.getDescription();
        TaskStatus statusBefore = taskBefore.getTaskStatus();
        manager.addNewTask(taskBefore);
        Task taskAfter = manager.getTask(1);
        assertEquals(titleBefore, taskAfter.getTaskTitle());
        assertEquals(descriptionBefore, taskAfter.getDescription());
        assertEquals(statusBefore, taskAfter.getTaskStatus());

        Epic epicBefore = new Epic("epic", "epic description");
        String epicTitleBefore = epicBefore.getTaskTitle();
        String epicDescriptionBefore = epicBefore.getDescription();
        manager.addNewEpic(epicBefore);
        Epic epicAfter = manager.getEpic(2);
        assertEquals(epicTitleBefore, epicAfter.getTaskTitle());
        assertEquals(epicDescriptionBefore, epicAfter.getDescription());

        Subtask subtaskBefore = new Subtask(TaskStatus.IN_PROGRESS, "subtask", "description", 2);
        String subtaskTitleBefore = subtaskBefore.getTaskTitle();
        String subtaskDescriptionBefore = subtaskBefore.getDescription();
        TaskStatus subtaskStatusBefore = subtaskBefore.getTaskStatus();
        int subtaskEpicIdBefore = subtaskBefore.getEpicId();
        manager.addNewSubtask(subtaskBefore);
        Subtask subtaskAfter = manager.getSubtask(3);
        assertEquals(subtaskTitleBefore, subtaskAfter.getTaskTitle());
        assertEquals(subtaskDescriptionBefore, subtaskAfter.getDescription());
        assertEquals(subtaskStatusBefore, subtaskAfter.getTaskStatus());
        assertEquals(subtaskEpicIdBefore, subtaskAfter.getEpicId());
    }

    @Test
    void shouldSaveOldVersion() {

        Task task = new Task("title", "description");
        manager.addNewTask(task);
        manager.getTask(1);

        Task updateTask = new Task("new title", "new description", 1);
        manager.updateTask(updateTask);
        manager.getTask(1);

        Task oldTask = manager.getHistory().getHistory().get(0);
        assertEquals("title", oldTask.getTaskTitle());
        assertEquals("description", oldTask.getDescription());
    }
}