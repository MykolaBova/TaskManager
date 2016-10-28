package org.rublin;

import org.rublin.command.Operation;
import org.rublin.repository.JdbcRepository;
import org.rublin.repository.TaskRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

/**
 * ???
 *
 * @author Ruslan Sheremet
 * @see
 * @since 1.0
 */
public class ConsoleHelper {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static String CHOOSE_OPERATION = "Please choose an operation";
    private static String ADD_TASK = "1 - Add new task";
    private static final String SHOW_ACTIVE_TASK = "2 - Show tasks";
    private static final String CLOSE_TASK = "3 - Close task";
    private static final String SHOW_CLOSED_TASK = "4 - Show closed tasks";
    private static final String RETURN = "5 - Return to main menu";
    private static final String EXIT = "0 - Exit";
//    public static final String

    public static final String INVALID = "Please specify valid data";

    public static TaskRepository repository = JdbcRepository.getRepository();

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message) {
        System.out.println(message);
    }
    public static String readString() {
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static Operation mainMenu() {
        do {
            writeMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            writeMessage(CHOOSE_OPERATION);
            writeMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            writeMessage(ADD_TASK);
            writeMessage(SHOW_ACTIVE_TASK);
            writeMessage("");
            writeMessage(EXIT);
            writeMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            try {
                return Operation.getOperationByOrdinal(Integer.parseInt(readString()));
            } catch (IllegalArgumentException e) {
                writeMessage(INVALID);
            }
        } while (true);
    }
    public static Operation taskMenu() {
        do {
            writeMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            writeMessage(CHOOSE_OPERATION);
            writeMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            writeMessage(CLOSE_TASK);
            writeMessage(SHOW_CLOSED_TASK);
            writeMessage(RETURN);
            writeMessage("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            try {
                return Operation.getOperationByOrdinal(Integer.valueOf(readString()));
            } catch (IllegalArgumentException e) {
                writeMessage(INVALID);
            }
        } while (true);

    }
}
