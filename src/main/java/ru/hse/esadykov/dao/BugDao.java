package ru.hse.esadykov.dao;

import ru.hse.esadykov.ConnectionFactory;
import ru.hse.esadykov.model.Bug;
import ru.hse.esadykov.model.BugStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
public class BugDao {
    
    private Bug extractBug(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        Date created = rs.getTimestamp("created");
        int priority = rs.getInt("priority");
        String title = rs.getString("title");
        String description = rs.getString("description");
        Integer responsibleId = rs.getInt("responsible_id");
        BugStatus status = BugStatus.valueOf(rs.getString("status"));

        return new Bug(id, created, priority, title, description, responsibleId, status);
    }
    
    public List<Bug> getBugs() throws SQLException {
        List<Bug> result = new ArrayList<>();
        try (Connection con = ConnectionFactory.getConnection()) {
            ResultSet resultSet = con.createStatement().executeQuery(
                    "select id, created, priority, title, description, responsible_id, status " +
                            "from bug order by created desc");
            while (resultSet.next()) {
                result.add(extractBug(resultSet));
            }
        }

        return result;
    }
}
