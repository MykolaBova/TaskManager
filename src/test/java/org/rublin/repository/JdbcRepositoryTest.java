package org.rublin.repository;

import org.junit.Before;
import org.junit.Test;
import org.rublin.model.Task;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.rublin.repository.JdbcRepositoryTestData.*;

/**
 * Testing task repository
 *
 * @author Ruslan Sheremet
 * @see
 * @since 1.0
 */
public class JdbcRepositoryTest {
    private JdbcRepository repository = JdbcRepository.getRepository();

    @Before
    public void setUp() throws Exception {
        repository.executeQuery(POPULATE_TABLES);
    }

    @Test
    public void testCreateTask() throws Exception {
        repository.createTask(NEW_TASK);
        Task[] expected = {TASK_11_ACTIVE, TASK_12, NEW_TASK};
        assertArrayEquals(expected, repository.getActiveTasks().toArray());
    }

    @Test
    public void testGetActiveTasks() throws Exception {
        Task[] expected = {TASK_11_ACTIVE, TASK_12};
        assertArrayEquals(expected, repository.getActiveTasks().toArray());
    }

    @Test
    public void testGetClosedTasks() throws Exception {
        Task[] expected = {TASK_10};
        assertArrayEquals(expected, repository.getClosedTasks().toArray());
    }

    @Test
    public void testCloseTask() throws Exception {
        repository.closeTask(11);
        Task[] expectedActive = {TASK_12};
        Task[] expectedClosed = {TASK_10, TASK_11_CLOSED};
        assertArrayEquals(expectedActive, repository.getActiveTasks().toArray());
        assertArrayEquals(expectedClosed, repository.getClosedTasks().toArray());
    }
}