package ru.hse.esadykov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.hse.esadykov.model.Bug;
import ru.hse.esadykov.model.BugStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
@Repository("bugDao")
public class BugDao {

    @Autowired
    private NamedParameterJdbcTemplate template;
    
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

    public List<Bug> getBugs() {
        return template.query("(select id, created, priority, title, description, responsible_id, status " +
                "from bug where status = 'NEW' order by priority desc)" +
                " union " +
                "(select id, created, priority, title, description, responsible_id, status " +
                "from bug where status <> 'NEW' order by priority desc)", new ResultSetExtractor<List<Bug>>() {
            @Override
            public List<Bug> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Bug> result = new ArrayList<>();
                while (rs.next()) {
                    result.add(extractBug(rs));
                }

                return result;
            }
        });
    }

    public Bug getBug(int bugId) throws SQLException {
        return template.query("select id, created, priority, title, description, responsible_id, status " +
                        "from bug where id = :bugId",
                Collections.singletonMap("bugId", bugId),
                new ResultSetExtractor<Bug>() {
                    @Override
                    public Bug extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (!rs.next()) {
                            return null;
                        }
                        return extractBug(rs);
                    }
                });
    }

    public boolean addBug(Bug bug) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("priority", bug.getPriority());
        params.put("title", bug.getTitle());
        params.put("description", bug.getDescription());
        params.put("responsibleId", bug.getResponsibleId());

        return template.update("insert into bug (priority, title, description, responsible_id) values " +
                "(:priority, :title, :description, :responsibleId)", params) > 0;
        }
    }
}
