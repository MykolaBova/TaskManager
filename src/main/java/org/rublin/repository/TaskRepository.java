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

    /**
     * Insert new task {@link Task} into active table
     *
     * @param task the task to be added to this list
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) -1 if was Exception
     */
    int createTask(Task task);

    /**
     * Returns the list of all tasks {@link Task} from task_manager.active_tasks
     *
     * @return the list of active tasks
     * @see Task
     */
    List<Task> getActiveTasks();

    /**
     * Returns the list of all tasks {@link Task} from task_manager.closed_tasks
     *
     * @return the list of closed tasks
     */
    List<Task> getClosedTasks();

    /**
     * Close active task {@link Task} and move it to closed tasks
     *
     * @param id id of task to be closed
     * @throws TaskNotFoundException
     */
    void closeTask(int id) throws TaskNotFoundException;

    /**
     * Close statement and connection
     */
    void closeRepository();
}
