package ru.hse.esadykov.model;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
public enum BugStatus {
    NEW(1, false),
    INVALID(2, true),
    WONTFIX(3, true),
    ACCEPTED(4, true);

    private final int id;
    private final boolean closed;


    private BugStatus(int id, boolean closed) {
        this.closed = closed;
        this.id = id;
    }

    public boolean isClosed() {
        return closed;
    }

    public int getId() {
        return id;
    }
}