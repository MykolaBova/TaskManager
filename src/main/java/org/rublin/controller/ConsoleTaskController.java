package org.rublin.controller;

import org.rublin.ConsoleHelper;
import org.rublin.command.Operation;
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
        ConsoleHelper.writeMessage("Priority (high, middle, low): ");
        Priority priority = Priority.valueOf(ConsoleHelper.readString().toUpperCase());
        ConsoleHelper.writeMessage("Date and Time (for example, 30.01.2016 15:03): ");
        LocalDateTime dateTime = LocalDateTime.parse(ConsoleHelper.readString(), ConsoleHelper.FORMATTER);
        if (ConsoleHelper.repository.createTask(new Task(description, dateTime, priority, false)) == -1) {
            ConsoleHelper.writeMessage("Task adding failed");
        } else {
            ConsoleHelper.writeMessage("Task success added");
        }
    }

    @Override
    public void showActiveTasks() {
        ConsoleHelper.writeMessage("### All active tasks ###");
        ConsoleHelper.repository.getActiveTasks().forEach(task -> ConsoleHelper.writeMessage(String.format("Description: %s; Time: %s; Priority: %s", task.getDescription(), task.getTime().format(ConsoleHelper.FORMATTER), task.getPriority())));
    }

    @Override
    public void closeTask() {

    }

    @Override
    public void showClosedTasks() {
        ConsoleHelper.writeMessage("### All closed tasks ###");
        ConsoleHelper.repository.getClosedTasks().forEach(task -> ConsoleHelper.writeMessage(String.format("Description: %s; Time: %s; Priority: %s", task.getDescription(), task.getTime().format(ConsoleHelper.FORMATTER), task.getPriority())));
    }
}
