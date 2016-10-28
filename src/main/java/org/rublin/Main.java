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
    }
}
