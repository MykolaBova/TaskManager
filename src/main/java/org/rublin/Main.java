package org.rublin;

import org.rublin.command.CommandExecutor;
import org.rublin.command.Operation;

/**
 * Created by Ruslan Sheremet (rublin) on 26.10.2016.
 */
public class Main {
    public static void main(String[] args) {


        Operation operation;
        do {
            operation = ConsoleHelper.mainMenu();
            CommandExecutor.execute(operation);
        } while (operation != Operation.EXIT);

        /*System.out.println(JdbcRepository.getRepository().createTask(new Task("Yeva's birthday", LocalDateTime.of(2016, 11, 16, 16, 00), Priority.HIGH, false)));
        System.out.println("Active tasks:");
        JdbcRepository.getRepository().getActiveTasks().forEach(System.out::println);
        System.out.println("closing task...");
        try {
            JdbcRepository.getRepository().closeTask(12);
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Closed task:");
        JdbcRepository.getRepository().getClosedTasks().forEach(System.out::println);

        JdbcRepository.getRepository().closeRepository();*/
    }
}
