package org.rublin.repository;

import org.rublin.exception.TaskNotFoundException;
import org.rublin.model.Priority;
import org.rublin.model.Task;
import org.slf4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.slf4j.LoggerFactory.getLogger;
/**
 * Repository provides JDBC connections to SQL database
 *
 * @author Ruslan Sheremet
 * @see Connection
 * @see Statement
 * @see ResultSet
 * @since 1.0
 */
public class JdbcRepository implements TaskRepository {

    private static final Logger LOG = getLogger(JdbcRepository.class);

    private static final ResourceBundle MYSQL = ResourceBundle.getBundle("db.mysql");
    private static final String DATABASE_NAME = MYSQL.getString("database.dbName");
    private static final String LOGIN = MYSQL.getString("database.username");
    private static final String PASSWORD = MYSQL.getString("database.password");
    private static final String TABLE_ACTIVE_TASKS = DATABASE_NAME + "." + MYSQL.getString("database.activeTasks");
    private static final String TABLE_CLOSED_TASKS = DATABASE_NAME + "." + MYSQL.getString("database.closedTasks");
    private static final JdbcRepository repository = new JdbcRepository();
    private Connection connection;
    private Statement statement;
    private JdbcRepository() {
        try {
            Class.forName(MYSQL.getString("database.driverClassName"));
            connection = DriverManager.getConnection(String.format("%s?user=%s&password=%s", MYSQL.getString("database.url"), LOGIN, PASSWORD));
            connection.setAutoCommit(false);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JdbcRepository getRepository() {
        return repository;
    }

    /**
     * Insert new task {@link Task} into active table
     *
     * @param task the task to be added to this list
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) -1 if was Exception
     */
    @Override
    public int createTask(Task task) {
        return createTask(task, TABLE_ACTIVE_TASKS);
    }

    /**
     * Insert new task into some table
     * @param task the task to be added to this list
     * @param table
     * @return
     */
    private int createTask(Task task, String table) {
        int result;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("INSERT INTO %s VALUES (DEFAULT , ?, ?, ?)", table));
            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setString(2, task.getPriority().toString());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(task.getTime()));
            result = preparedStatement.executeUpdate();
            connection.commit();
            LOG.info("New task {} created", task);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            return -1;
        }
        return result;
    }

    /**
     * Returns the list of all tasks {@link Task} from task_manager.active_tasks
     *
     * @return the list of active tasks
     * @see Task
     */
    @Override
    public List<Task> getActiveTasks() {
        return getTaskList(TABLE_ACTIVE_TASKS, false);
    }

    /**
     * Returns the list of all tasks {@link Task} from task_manager.closed_tasks
     *
     * @return the list of closed tasks
     */
    @Override
    public List<Task> getClosedTasks() {
        return getTaskList(TABLE_CLOSED_TASKS, true);
    }

    /**
     * Close active task {@link Task} and move it to closed tasks
     *
     * @param id id of task to be closed
     * @throws TaskNotFoundException
     */
    @Override
    public void closeTask(int id) throws TaskNotFoundException {
        Task task = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s WHERE id=%d", TABLE_ACTIVE_TASKS, id));
            while (resultSet.next()) {
                task = getTask(resultSet, true);
            }
            if (task == null) {
                LOG.error("Task with id: {} not found", id);
                throw new TaskNotFoundException();
            }
            statement.executeUpdate(String.format("DELETE FROM %s WHERE id=%d", TABLE_ACTIVE_TASKS, id));
            createTask(task, TABLE_CLOSED_TASKS);
            LOG.info("Task {} closed successfully", task);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage());
            }
            LOG.error(e.getMessage());
        }
    }

    /**
     * Close statement and connection
     */
    @Override
    public void closeRepository() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    /**
     * Create task {@link Task} from resultSet
     *
     * @param resultSet
     * @param closed if task is closed
     * @return task
     * @throws SQLException
     */
    private Task getTask(ResultSet resultSet, boolean closed) throws SQLException {
        int id = resultSet.getInt("id");
        String description = resultSet.getString("description");
        String priority = resultSet.getString("priority");
        LocalDateTime dateTime = resultSet.getTimestamp("time").toLocalDateTime();
        return new Task(id, description, dateTime, Priority.valueOf(priority), closed);
    }

    /**
     * Select all from table and create list {@link ArrayList} of tasks {@link Task}
     *
     * @param table table name
     * @param closed true if task is closed and false if task is active
     * @return list {@link ArrayList} of tasks {@link Task}
     */
    private List<Task> getTaskList(String table, boolean closed) {
        List<Task> tasks = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s", table));
            while (resultSet.next()) {
                tasks.add(getTask(resultSet, closed));
            }
            LOG.debug("Get task list success");
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return tasks;
    }
}
