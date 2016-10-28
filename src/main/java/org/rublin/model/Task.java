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
}
