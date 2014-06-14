package ru.hse.esadykov.model;

/**
 * @author Anton Galaev
 * @since 14.06.2014
 */
public enum BugPriority {
    BLOCKER(1), CRITICAL(2), MAJOR(3), MINOR(4), TRIVIAL(5);

    private final int id;

    private BugPriority(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
