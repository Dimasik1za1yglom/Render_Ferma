package example.demo.service;

import example.demo.dao.StatusRepository;
import example.demo.dao.TaskRepository;
import example.demo.dao.TaskStatusRepository;
import example.demo.entities.Task;
import example.demo.entities.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final StatusRepository statusRepository;

    @Override
    public List<Task> getAllTask(Long userId) {
        return taskRepository.findAllByAccount_Id(userId);
    }

    @Override
    public void addTask(Task task) {
        try {
            Task saveTask = taskRepository.save(task);
            addStatusTask(saveTask, 1L);
        } catch (Exception e) {
            throw new IllegalArgumentException("Не удалось добавить задачу");
        }

    }

    @Override
    public void addStatusTask(Task task, Long statusId) {
        try {
            taskStatusRepository.save(TaskStatus.builder()
                    .task(task)
                    .status(statusRepository.getReferenceById(statusId))
                    .dataTime(LocalDateTime.now())
                    .build());
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Не удалось добавить cтатус задаче № %d", task.getId()));
        }
    }

    @Override
    public void finishTask(Task task) {
        addStatusTask(task, 2L);
    }

    @Override
    public List<TaskStatus> getAllTaskStatus(Long userId) {
        try {
            return taskStatusRepository.getTaskStatusByAccountId(userId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Нет созданных задач");
        }
    }
}
