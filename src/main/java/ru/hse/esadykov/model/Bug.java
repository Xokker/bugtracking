package ru.hse.esadykov.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
public class Bug {
    private Integer id;
    private List<Bug> dependencies;
    private Date created;
    private int priority;
    private String title;
    private String description;
    private Integer responsibleId;
    private BugStatus status;
    private transient User responsible;
    private List<Comment> comments;

    public Bug() {
        dependencies = new ArrayList<Bug>();
    }

    public Bug(Integer id, String title) {
        this();
        this.id = id;
        this.title = title;
    }

    public Bug(Integer id, Date created, int priority, String title, String description, Integer responsibleId, BugStatus status) {
        this();
        this.id = id;
        this.created = created;
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.responsibleId = responsibleId;
        this.status = status;
    }

    public void addDependency(Bug bug) {
        dependencies.add(bug);
    }

    public void removeDependency(Bug bug) {
        dependencies.remove(bug);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(Integer responsibleId) {
        this.responsibleId = responsibleId;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Bug getDependentBug(Integer bug_id) {
        for (Bug b : dependencies) {
            if (b.getId().equals(bug_id)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bug bug = (Bug) o;

        if (!id.equals(bug.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bug{");
        sb.append("id=").append(id);
        sb.append(", created=").append(created);
        sb.append(", priority=").append(priority);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", responsibleId=").append(responsibleId);
        sb.append(", status=").append(status);
        if (!dependencies.isEmpty()) {
            sb.append(", dependent bugs: ");
            for (Bug b : dependencies) {
                sb.append(b.toString() + ";");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
