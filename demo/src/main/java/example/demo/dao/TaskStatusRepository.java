package example.demo.dao;

import example.demo.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {

    @Query(nativeQuery = true,
    value = "select * from task_status where task_status.task_id in (select id from task where account_id = :accountId)")
    List<TaskStatus> getTaskStatusByAccountId(Long accountId);
}
