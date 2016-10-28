package org.rublin.controller;

import org.rublin.ConsoleHelper;
import org.rublin.command.CommandExecutor;
import org.rublin.exception.TaskNotFoundException;
import org.rublin.model.Priority;
import org.rublin.model.Task;
import org.slf4j.Logger;

import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Controller for managing tasks using CLI
 *
 * @author Ruslan Sheremet
 * @since 1.0
 */
public class ConsoleTaskController implements TaskController {

    @Override
    public void addTask() {
        String description = ConsoleHelper.getValidDescription();
        Priority priority = Priority.valueOf(ConsoleHelper.getValidPriority());
        LocalDateTime dateTime = ConsoleHelper.getValidDateTime();
        if (ConsoleHelper.repository.createTask(new Task(description, dateTime, priority, false)) == -1) {
            ConsoleHelper.writeOperationFailedMessage();
        } else {
            ConsoleHelper.writeOperationOkMessage();
        }
    }

    @Override
    public void showActiveTasks() {
        ConsoleHelper.writeMessage("#####\tAll active tasks\t#####");
        ConsoleHelper.repository.getActiveTasks().forEach(task -> ConsoleHelper.writeMessage(taskFormat(task)));
        CommandExecutor.execute(ConsoleHelper.taskMenu());
    }

    @Override
    public void closeTask() {
        ConsoleHelper.writeMessage("Type task id");
        do {
            try {
                ConsoleHelper.repository.closeTask(ConsoleHelper.getValidInt());
                break;
            } catch (TaskNotFoundException e) {
                ConsoleHelper.writeInvalidWarning();
                //e.printStackTrace();
            }
        } while (true);

    }

    @Override
    public void showClosedTasks() {
        ConsoleHelper.writeMessage("#####\tAll closed tasks\t#####");
        ConsoleHelper.repository.getClosedTasks().forEach(task -> ConsoleHelper.writeMessage(taskFormat(task)));
    }

    /**
     * Formatting message to display for user
     *
     * @param task
     * @return
     */
    private String taskFormat(Task task) {
        LocalDateTime now = LocalDateTime.now();
        return String.format("id: %d\t" +
                "\tDescription:\t%s\n" +
                "\t\t\tTime:\t\t\t%s\n" +
                "\t\t\tPriority:\t\t%s\n" +
                "\t\t\t%s\n",
                task.getId(),
                task.getDescription(),
                task.getTime().format(ConsoleHelper.FORMATTER),
                task.getPriority(),
                task.isClosed() ? "" : task.getTime().compareTo(now) > 0 ? "" : "EXPIRED");
    }
}
