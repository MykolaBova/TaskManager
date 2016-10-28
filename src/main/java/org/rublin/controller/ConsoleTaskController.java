package org.rublin.controller;

import org.rublin.ConsoleHelper;
import org.rublin.command.CommandExecutor;
import org.rublin.command.Operation;
import org.rublin.exception.TaskNotFoundException;
import org.rublin.model.Priority;
import org.rublin.model.Task;

import java.time.LocalDateTime;

/**
 * ???
 *
 * @author Ruslan Sheremet
 * @see
 * @since 1.0
 */
public class ConsoleTaskController implements TaskController {

    @Override
    public Operation mainMenu() {
        return null;
    }

    @Override
    public void addTask() {
        ConsoleHelper.writeMessage("Description: ");
        String description = ConsoleHelper.readString();
        Priority priority = Priority.valueOf(ConsoleHelper.getValidPriority());
        LocalDateTime dateTime = LocalDateTime.parse(ConsoleHelper.getValidDateTime(), ConsoleHelper.FORMATTER);
        if (ConsoleHelper.repository.createTask(new Task(description, dateTime, priority, false)) == -1) {
            ConsoleHelper.writeMessage("Task adding failed");
        } else {
            ConsoleHelper.writeMessage("Task added successfully ");
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
                ConsoleHelper.repository.closeTask(Integer.valueOf(ConsoleHelper.readString()));
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
