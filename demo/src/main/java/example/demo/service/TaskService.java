package example.demo.service;

import example.demo.entities.Task;
import example.demo.entities.TaskStatus;

import java.util.List;

public interface TaskService {
    /**
     * возвращает все задачи контректтного пользователя
     *
     * @param userId id пользователя
     * @return
     */
    List<Task> getAllTask(Long userId);

    /**
     * Добавление задачи
     * @param task
     */
    void addTask(Task task);


    void addStatusTask(Task task, Long statusId);

    void finishTask(Task task);

    List<TaskStatus> getAllTaskStatus(Long userId);
}
