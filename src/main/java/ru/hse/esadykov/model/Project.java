package ru.hse.esadykov.model;

/**
 * @author Anton Galaev
 * @since 14.06.2014
 */
public class Project {
    private Integer id;
    private String name;
    private String description;
    private Integer managerId;
    private transient User manager;

    public Project() {
    }

    public Project(Integer id, String name, String description, Integer managerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.managerId = managerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", managerId='").append(managerId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
