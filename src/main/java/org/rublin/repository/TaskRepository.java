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
    Task create(Task task);
    List<Task> getAllActive();
    List<Task> getAllClosed();
    void close(int id) throws TaskNotFoundException;
}
