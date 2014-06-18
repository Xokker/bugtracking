package ru.hse.esadykov.dao;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.hse.esadykov.model.*;

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

    @Autowired
    private UserDao userDao;
    
    private Bug extractBug(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        Date created = rs.getTimestamp("created");
        Date closed = rs.getTimestamp("closed");
        BugPriority priority = BugPriority.valueOf(rs.getString("priority"));
        String title = rs.getString("title");
        String description = rs.getString("description");
        Integer responsibleId = rs.getInt("responsible_id");
        Integer creatorId = rs.getInt("creator_id");
        Integer projectId = rs.getInt("project_id");
        String projectName = rs.getString("project_name");
        BugStatus status = BugStatus.valueOf(rs.getString("status"));
        IssueType issueType = IssueType.valueOf(rs.getString("type"));

        return new Bug(id, created, closed, title, description, responsibleId, creatorId, status, priority, issueType, projectId, projectName);
    }

    public List<Bug> getBugs() {
        return getBugs(-1, null);
    }

    public List<Bug> getBugs(Integer projectId, Boolean showClosed) {
        boolean projectPresented = projectId != null && projectId > 0;
        return template.query("select b.id, created, closed, p.title as priority, b.title, b.description, responsible_id, creator_id, status, type, project_id, pr.name as project_name " +
                        "from bug b join priority p on p.id = b.priority join project pr on project_id = pr.id " +
                        (projectPresented ? "and project_id = " + projectId : "") +
                        (showClosed == null || !showClosed ? " where status = '" + BugStatus.NEW + "'" : "") +
                        " order by p.id asc ",
                new ResultSetExtractor<List<Bug>>() {
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

    public Bug getBug(int bugId) {
        final Bug bug = template.query("select b.id, created, closed, p.title as priority, b.title, b.description, responsible_id, creator_id, status, type, project_id, pr.name as project_name " +
                        "from bug b join priority p on p.id = b.priority join project pr on project_id = pr.id where b.id = :bugId",
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
        template.query("select t1.id, title from bug join (select bug1_id as id from dependencies " +
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

        return template.query("select * from observers where bug_id = :bugId",
                Collections.singletonMap("bugId", bugId),
                new ResultSetExtractor<Bug>() {
                    @Override
                    public Bug extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (rs.next()) {
                            User user = userDao.getUser(rs.getInt("observer_id"));
                            bug.addObserver(new User(user.getId(), user.getUsername(), user.getFullName(), user.getEmail(), user.getPassword()));
                        }
                        return bug;
                    }
                });
    }

    public boolean saveBug(Bug bug) {
        Map<String, Object> params = new HashMap<>();
        params.put("priority", ObjectUtils.defaultIfNull(bug.getPriority(), BugPriority.MAJOR).getId());
        params.put("title", bug.getTitle());
        params.put("description", bug.getDescription());
        params.put("responsibleId", bug.getResponsibleId());
        params.put("creatorId", bug.getCreatorId());
        params.put("type", ObjectUtils.defaultIfNull(bug.getIssueType(), IssueType.BUG).toString());
        params.put("projectId", bug.getProjectId());

        return template.update("insert into bug (priority, title, description, responsible_id, creator_id, type, project_id) values " +
                "(:priority, :title, :description, :responsibleId, :creatorId, :type, :projectId)", params) > 0;
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

    public boolean addObserver(Bug bug, User observer) {
        Map<String, Object> params = new HashMap<>();
        params.put("bug_id", bug.getId());
        params.put("observer_id", observer.getId());

        return template.update("insert into observers(bug_id, observer_id) values (:bug_id, :observer_id)", params) > 0;
    }

    public boolean removeObserver(Bug bug, User observer) {
        Map<String, Object> params = new HashMap<>();
        params.put("bug_id", bug.getId());
        params.put("observer_id", observer.getId());

        return template.update("delete from observers where bug_id = :bug_id and observer_id = :observer_id", params) > 0;
    }
}
