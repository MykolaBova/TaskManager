package org.rublin.controller;

import org.rublin.command.Operation;

/**
 * ???
 *
 * @author Ruslan Sheremet
 * @see
 * @since 1.0
 */
public interface TaskController {
//    TaskController getController();
    Operation mainMenu();
    void addTask();
    void showActiveTasks();
    void closeTask();
    void showClosedTasks();
}
