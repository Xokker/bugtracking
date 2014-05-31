package ru.hse.esadykov.dao;

import ru.hse.esadykov.ConnectionFactory;
import ru.hse.esadykov.model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
public class CommentDao {

    public List<Comment> getComments(int bugId) throws SQLException {
        List<Comment> result = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "select id, body, created, author_id, bug_id from comment where bug_id = ?");
            ps.setInt(1, bugId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                result.add(extractComment(resultSet));
            }
        }

        return result;
    }

    private Comment extractComment(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String body = resultSet.getString("body");
        Date created = new Date(resultSet.getDate("created").getTime());
        Integer authorId = resultSet.getInt("author_id");
        Integer bugId = resultSet.getInt("bug_id");

        return new Comment(id, body, created, authorId, bugId);
    }
}
