package ru.hse.esadykov.model;

import java.util.Date;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
public class Bug {
    private Integer id;
    private Date created;
    private Date closed;
    private int priority;
    private String title;
    private String description;
    private Integer responsibleId;
    private Integer creatorId;
    private BugStatus status;
    private transient User responsible;
    private transient User creator;
    private List<Comment> comments;

    public Bug() {
    }

    public Bug(Integer id, Date created, Date closed, int priority, String title, String description, Integer responsibleId, Integer creatorId, BugStatus status) {
        this.id = id;
        this.created = created;
        this.closed = closed;
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.responsibleId = responsibleId;
        this.creatorId = creatorId;
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

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
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

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bug{");
        sb.append("id=").append(id);
        sb.append(", created=").append(created);
        sb.append(", closed=").append(closed);
        sb.append(", priority=").append(priority);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", responsibleId=").append(responsibleId);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
