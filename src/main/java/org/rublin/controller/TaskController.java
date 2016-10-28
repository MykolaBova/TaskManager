package org.rublin.controller;

import org.rublin.command.Operation;

/**
 * Interface of controller to manage tasks
 *
 * @author Ruslan Sheremet
 * @see
 * @since 1.0
 */
public interface TaskController {
    /**
     * Add new task
     */
    void addTask();

    /**
     * Show all active tasks
     */
    void showActiveTasks();

    /**
     * Close active task
     */
    void closeTask();

    /**
     * Show all closed tasks
     */
    void showClosedTasks();
}
