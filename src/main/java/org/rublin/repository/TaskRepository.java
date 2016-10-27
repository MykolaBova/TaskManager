package org.rublin.repository;

import org.rublin.exception.TaskNotFoundException;
import org.rublin.model.Task;

import java.util.List;

/**
 * Repository provides connections to SQL database to store tasks {@link Task}
 *
 * @author Ruslan Sheremet
 * @see Task
 * @since 1.0
 */
public interface TaskRepository {
    int createTask(Task task);
    List<Task> getActiveTasks();
    List<Task> getClosedTasks();
    void closeTask(int id) throws TaskNotFoundException;
    void closeRepository();
}
