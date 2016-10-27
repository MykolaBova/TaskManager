package org.rublin.model;

import java.time.LocalDateTime;

/**
 * ???
 *
 * @author Ruslan Sheremet
 * @see
 * @since 1.0
 */
public class Task {
    private String description;
    private LocalDateTime time;
    private Priority priority;
    private boolean closed;

    public Task(String description, LocalDateTime time, Priority priority, boolean closed) {
        this.description = description;
        this.time = time;
        this.priority = priority;
        this.closed = closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
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
