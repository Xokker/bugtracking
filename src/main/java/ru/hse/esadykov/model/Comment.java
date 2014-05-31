package ru.hse.esadykov.model;

import java.util.Date;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
public class Comment {
    private Integer id;
    private String body;
    private Date created;
    private Integer authorId;
    private transient User author;
    private Integer bugId;
    private transient Bug bug;

    public Comment() {
    }

    public Comment(Integer id, String body, Date created, Integer authorId, Integer bugId) {
        this.id = id;
        this.body = body;
        this.created = created;
        this.authorId = authorId;
        this.bugId = bugId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getBugId() {
        return bugId;
    }

    public void setBugId(Integer bugId) {
        this.bugId = bugId;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("id=").append(id);
        sb.append(", body='").append(body).append('\'');
        sb.append(", created=").append(created);
        sb.append(", authorId=").append(authorId);
        sb.append(", author=").append(author);
        sb.append(", bugId=").append(bugId);
        sb.append(", bug=").append(bug);
        sb.append('}');
        return sb.toString();
    }
}
