package org.rublin.repository;

import org.rublin.exception.TaskNotFoundException;
import org.rublin.model.Task;

import java.util.List;

/**
 * ???
 *
 * @author Ruslan Sheremet
 * @see
 * @since 1.0
 */
public interface TaskRepository {
    int createTask(Task task);
    List<Task> getActiveTasks();
    List<Task> getClosedTasks();
    void closeTask(int id) throws TaskNotFoundException;
    void closeRepository();
}
