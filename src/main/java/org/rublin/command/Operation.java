package org.rublin.command;

/**
 * Operations for tasks
 *
 * @author Ruslan Sheremet
 * @see org.rublin.model.Task
 * @since 1.0
 */
public enum Operation {
    ADD_TASK,
    CLOSE_TASK,
    SHOW_ACTIVE_TASKS,
    SHOW_CLOSED_TASKS,
    RETURN,
    EXIT;
    public static Operation getOperationByOrdinal (int i) {
        switch (i) {
            /**
             * Add new task
             */
            case 1: return Operation.ADD_TASK;

            /**
             * Show active tasks
             */
            case 2: return Operation.SHOW_ACTIVE_TASKS;

            /**
             * Close active task
             */
            case 3: return Operation.CLOSE_TASK;

            /**
             * Show closed tasks
             */
            case 4: return Operation.SHOW_CLOSED_TASKS;

            /**
             * Return to main menu
             */
            case 5: return Operation.RETURN;

            /**
             * Exit
             */
            case 0: return Operation.EXIT;

            default:throw new IllegalArgumentException("Operation not support");
        }
    }
}
