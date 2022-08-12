package example.demo.dao;

import example.demo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * вернуть все бдлюда по id акаунта
     *
     * @param accountId
     * @return
     */
    List<Task> findAllByAccount_Id(Long accountId);

    @Query(nativeQuery = true,
    value = "insert into task(name, account_id) values (:name , :accountId)")
    void addTask(String name, Long accountId);
}
