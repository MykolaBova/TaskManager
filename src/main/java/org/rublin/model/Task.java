package org.rublin.model;

import java.time.LocalDateTime;

/**
 * Task include id {@link Integer},
 *              description {@link String},
 *              priority {@link Priority},
 *              closed {@link Boolean} - true if task is closed and false if not
 *
 * @author Ruslan Sheremet
 * @see LocalDateTime
 * @see Priority
 * @since 1.0
 */
public class Task {
    private String description;
    private LocalDateTime time;
    private Priority priority;
    private boolean closed;
    private Integer id;

    public Task(String description, LocalDateTime time, Priority priority, boolean closed) {
        this.description = description;
        this.time = time;
        this.priority = priority;
        this.closed = closed;
    }

    public Task(Integer id, String description, LocalDateTime time, Priority priority, boolean closed) {
        this.description = description;
        this.time = time;
        this.priority = priority;
        this.closed = closed;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isClosed() {
        return closed;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", time=" + time +
                ", priority=" + priority +
                ", closed=" + closed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (closed != task.closed) return false;
        if (!description.equals(task.description)) return false;
        if (!time.equals(task.time)) return false;
        return priority == task.priority;

    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + priority.hashCode();
        result = 31 * result + (closed ? 1 : 0);
        return result;
    }
}
