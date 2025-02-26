package com.puschiasis.Tasks.repository;


import com.puschiasis.Tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
 List<Task> findByAssignedUser(String assignedUser);

}
