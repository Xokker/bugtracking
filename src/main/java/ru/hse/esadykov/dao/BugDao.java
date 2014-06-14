package ru.hse.esadykov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.hse.esadykov.model.Bug;
import ru.hse.esadykov.model.BugPriority;
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
        Date closed = rs.getTimestamp("closed");
        BugPriority priority = BugPriority.values()[rs.getInt("priority") - 1];
        String title = rs.getString("title");
        String description = rs.getString("description");
        Integer responsibleId = rs.getInt("responsible_id");
        Integer creatorId = rs.getInt("creator_id");
        BugStatus status = BugStatus.valueOf(rs.getString("status"));

        return new Bug(id, created, closed, title, description, responsibleId, creatorId, status, priority);
    }

    public List<Bug> getBugs() {
        // TODO: [FIXME] rewrite - http://stackoverflow.com/a/12268963/1970544
        return template.query("(select id, created, closed, priority, title, description, responsible_id, creator_id, status " +
                "from bug where status = 'NEW' order by priority desc)" +
                " union " +
                "(select id, created, closed, priority, title, description, responsible_id, creator_id, status " +
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
        final Bug bug = template.query("select id, created, closed, priority, title, description, responsible_id, creator_id, status " +
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
        return template.query("select t1.id, title from bug join (select bug1_id as id from dependencies " +
                        "where bug2_id = :bugId union select bug2_id as id from dependencies where bug1_id = :bugId) as t1",
                Collections.singletonMap("bugId", bugId),
                new ResultSetExtractor<Bug>() {
                    @Override
                    public Bug extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (rs.next()) {
                            bug.addDependency(new Bug(rs.getInt("id"), rs.getString("title")));
                        }
                        return bug;
                    }
                });
    }

    public boolean addBug(Bug bug) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("priority", bug.getPriority().getId());
        params.put("title", bug.getTitle());
        params.put("description", bug.getDescription());
        params.put("responsibleId", bug.getResponsibleId());
        params.put("creatorId", bug.getCreatorId());

        return template.update("insert into bug (priority, title, description, responsible_id, creator_id) values " +
                "(:priority, :title, :description, :responsibleId, :creatorId)", params) > 0;
    }

    public boolean updateBug(Bug bug) {
        Map<String, Object> params = new HashMap<>();
        params.put("bugId", bug.getId());
        params.put("statusId", bug.getStatus().getId());
        params.put("priorityId", bug.getPriority().getId());
        params.put("responsibleId", bug.getResponsibleId());
        params.put("closedDate", bug.getStatus().isClosed() ? new Date() : null);

        return template.update("update bug set status = :statusId, closed = :closedDate, priority = :priorityId" +
                ", responsible_id = :responsibleId where id = :bugId", params) > 0;
    }

    public boolean addDependency(Bug bug1, Bug bug2) {
        Map<String, Object> params = new HashMap<>();
        params.put("bug1_id", bug1.getId());
        params.put("bug2_id", bug2.getId());

        return template.update("insert into dependencies (bug1_id, bug2_id) select :bug1_id, :bug2_id from dual " +
                "where not exists (select * from dependencies where (bug1_id = :bug1_id and bug2_id = :bug2_id)" +
                "or (bug1_id = :bug2_id and bug2_id = :bug1_id))", params) > 0;
    }

    public boolean removeDependency(Bug bug1, Bug bug2) {
        Map<String, Object> params = new HashMap<>();
        params.put("bug1_id", bug1.getId());
        params.put("bug2_id", bug2.getId());

        return template.update("delete from  dependencies " +
                "where (bug1_id = :bug1_id and bug2_id = :bug2_id)" +
                "or (bug1_id = :bug2_id and bug2_id = :bug1_id)", params) > 0;
    }
}
