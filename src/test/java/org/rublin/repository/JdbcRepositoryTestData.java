package org.rublin.repository;

import org.rublin.model.Priority;
import org.rublin.model.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ???
 *
 * @author Ruslan Sheremet
 * @see
 * @since 1.0
 */
public class JdbcRepositoryTestData {
    static final String[] POPULATE_TABLES = {
            "DROP TABLE IF EXISTS active_tasks;",
            "DROP TABLE IF EXISTS closed_tasks;",
            "CREATE TABLE active_tasks (\n" +
                    "  id INT NOT NULL AUTO_INCREMENT,\n" +
                    "  description VARCHAR(100) NOT NULL ,\n" +
                    "  priority VARCHAR(10) NOT NULL ,\n" +
                    "  time DATETIME NOT NULL ,\n" +
                    "  PRIMARY KEY (id)\n" +
                    ");",
            "CREATE TABLE closed_tasks (\n" +
                    "  id INT NOT NULL AUTO_INCREMENT,\n" +
                    "  description VARCHAR(100) NOT NULL ,\n" +
                    "  priority VARCHAR(10) NOT NULL ,\n" +
                    "  time DATETIME NOT NULL ,\n" +
                    "  PRIMARY KEY (id)\n" +
                    ");",
            "INSERT INTO closed_tasks (id, description, priority, time) VALUES (\n" +
                    "    10, 'Save world', 'HIGH', '2016-10-26 09:00:00'\n" +
                    ");",
                    "INSERT INTO active_tasks (id, description, priority, time) VALUES\n" +
                    "  (11, 'Create test task for ProgForce', 'HIGH', '2016-10-30 15:00:00'),\n" +
                    "  (12, 'Do something', 'LOW', '2016-10-20 11:00:00');"
    };
    static final Task TASK_10 = new Task(10, "Save world", LocalDateTime.of(2016, 10, 26, 9, 0), Priority.HIGH, true);
    static final Task TASK_11_ACTIVE = new Task(11, "Create test task for ProgForce", LocalDateTime.of(2016, 10, 30, 15, 00), Priority.HIGH, false);
    static final Task TASK_11_CLOSED = new Task(11, "Create test task for ProgForce", LocalDateTime.of(2016, 10, 30, 15, 00), Priority.HIGH, true);
    static final Task TASK_12 = new Task(12, "Do something", LocalDateTime.of(2016, 10, 20, 11, 00), Priority.LOW, false);
    static final Task NEW_TASK = new Task(12, "New test task", LocalDateTime.of(2020, 12, 15, 12, 00), Priority.MIDDLE, false);

}
