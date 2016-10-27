package org.rublin.repository;

import org.rublin.exception.TaskNotFoundException;
import org.rublin.model.Priority;
import org.rublin.model.Task;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ???
 *
 * @author Ruslan Sheremet
 * @see
 * @since 1.0
 */
public class JdbcRepository implements TaskRepository {

    private static final ResourceBundle MYSQL = ResourceBundle.getBundle("db.mysql");
    private static final JdbcRepository repository = new JdbcRepository();
    private Connection connection;
    private Statement statement;
    private JdbcRepository() {
        try {
            Class.forName(MYSQL.getString("database.driverClassName"));
            connection = DriverManager.getConnection(String.format("%s?user=%s&password=%s", MYSQL.getString("database.url"), MYSQL.getString("database.username"), MYSQL.getString("database.password")));
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JdbcRepository getRepository() {
        return repository;
    }
    public Task create(Task task) {
        return null;
    }

    public List<Task> getAllActive() {
        List<Task> activeTasks = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM task_manager.active_tasks");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                String priority = resultSet.getString("priority");
                LocalDateTime dateTime = resultSet.getTimestamp("time").toLocalDateTime();
                activeTasks.add(new Task(description, dateTime, Priority.valueOf(priority), false));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activeTasks;
    }

    public List<Task> getAllClosed() {
        List<Task> closedTasks = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM task_manager.closed_tasks");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                String priority = resultSet.getString("priority");
                LocalDateTime dateTime = resultSet.getTimestamp("time").toLocalDateTime();
                closedTasks.add(new Task(description, dateTime, Priority.valueOf(priority), true));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return closedTasks;
    }

    public void close(int id) throws TaskNotFoundException {

    }

}
