package ru.hse.esadykov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.hse.esadykov.model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
@Repository("commentDao")
public class CommentDao {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public List<Comment> getComments(int bugId) throws SQLException {
        return template.query(
                "select id, body, created, author_id, bug_id from comment " +
                        "where bug_id = :bugId order by created desc",
                Collections.singletonMap("bugId", bugId),
                new ResultSetExtractor<List<Comment>>() {
                    @Override
                    public List<Comment> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<Comment> result = new ArrayList<>();
                        while (rs.next()) {
                            result.add(extractComment(rs));
                        }
                        return result;
                    }
                });
    }

    private Comment extractComment(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String body = resultSet.getString("body");
        Date created = resultSet.getTimestamp("created");
        Integer authorId = resultSet.getInt("author_id");
        Integer bugId = resultSet.getInt("bug_id");

        return new Comment(id, body, created, authorId, bugId);
    }

    public boolean saveComment(Comment comment) throws SQLException {
            Map<String, Object> params = new HashMap<>();
            params.put("body", comment.getBody());
            params.put("bugId", comment.getBugId());
            String sql;
            if (comment.getAuthorId() != null) {
                params.put("authorId", comment.getAuthorId());
                sql = "insert into comment (body, author_id, bug_id) values " +
                        "(:body, :authorId, :bugId)";
            } else if (comment.getAuthor() != null && comment.getAuthor().getUsername() != null) {
                params.put("username", comment.getAuthor().getUsername());
                sql = "insert into comment (body, author_id, bug_id) " +
                        "select :body, id, :bugId from user where username = :username";
            } else {
                throw new IllegalArgumentException("Author id or username must be specified");
            }

            return template.update(sql, params) > 0;
    }
}
