package org.rublin.command;

import org.rublin.controller.ConsoleTaskController;
import org.rublin.controller.TaskController;

/**
 * Execute controller method for each operations
 *
 * @author Ruslan Sheremet
 * @see TaskController,Operation
 * @since 1.0
 */
public class CommandExecutor {

    /**
     * Using {@link ConsoleTaskController}
     */
    private static TaskController controller = new ConsoleTaskController();

    public static void execute(Operation operation) {
        switch (operation) {
            case ADD_TASK: controller.addTask();
                break;
            case SHOW_ACTIVE_TASKS: controller.showActiveTasks();
                break;
            case SHOW_CLOSED_TASKS: controller.showClosedTasks();
                break;
            case CLOSE_TASK: controller.closeTask();
                break;
        }
    }
}
