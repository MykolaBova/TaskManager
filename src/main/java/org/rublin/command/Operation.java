package org.rublin.command;

/**
 * ???
 *
 * @author Ruslan Sheremet
 * @see
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
            case 1: return Operation.ADD_TASK;
            case 2: return Operation.SHOW_ACTIVE_TASKS;
            case 3: return Operation.CLOSE_TASK;
            case 4: return Operation.SHOW_CLOSED_TASKS;
            case 5: return Operation.RETURN;
            case 0: return Operation.EXIT;

            default:throw new IllegalArgumentException("Operation not support");
        }
    }
}
