package ru.hse.esadykov.model;

import java.util.Date;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
public class Bug {
    private Integer id;
    private Date created;
    private int priority;
    private String title;
    private String description;
    private Integer responsibleId;
    private BugStatus status;
    private transient User responsible;

    public Bug() {
    }

    public Bug(Integer id, Date created, int priority, String title, String description, Integer responsibleId, BugStatus status) {
        this.id = id;
        this.created = created;
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.responsibleId = responsibleId;
        this.status = status;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bug{");
        sb.append("id=").append(id);
        sb.append(", created=").append(created);
        sb.append(", priority=").append(priority);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", responsibleId=").append(responsibleId);
        sb.append(", responsible=").append(responsible);
        sb.append('}');
        return sb.toString();
    }
}
