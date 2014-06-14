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
    private Date closed;
    private String title;
    private String description;
    private Integer responsibleId;
    private Integer creatorId;
    private Integer projectId;
    private BugStatus status;
    private BugPriority priority;
    private IssueType issueType;
    private transient User responsible;
    private transient User creator;
    private transient Project project;
    private List<Comment> comments;

    public Bug() {
        dependencies = new ArrayList<>();
    }

    public Bug(Integer id, String title) {
        this();
        this.id = id;
        this.title = title;
    }
    
    public Bug(Integer id, Date created, Date closed, String title, String description, Integer responsibleId, Integer creatorId, BugStatus status, BugPriority priority, IssueType issueType, Integer projectId) {
        this(id, title);
        this.created = created;
        this.closed = closed;
        this.description = description;
        this.responsibleId = responsibleId;
        this.creatorId = creatorId;
        this.status = status;
        this.priority = priority;
        this.issueType = issueType;
        this.projectId = projectId;
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

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public BugPriority getPriority() {
        return priority;
    }

    public void setPriority(BugPriority priority) {
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

    public IssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
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
        sb.append(", closed=").append(closed);
        sb.append(", priority=").append(priority);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", responsibleId=").append(responsibleId);
        sb.append(", creatorId=").append(creatorId);
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
