package ru.hse.esadykov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.hse.esadykov.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Anton Galaev
 * @since 14.06.2014
 */
@Repository("projectDao")
public class ProjectDao {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private Project extractProject(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        Integer managerId = rs.getInt("manager_id");

        return new Project(id, name, description, managerId);
    }

    public List<Project> getProjects() {
        return template.query("select id, name, description, manager_id from project",  new ResultSetExtractor<List<Project>>() {
            @Override
            public List<Project> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Project> result = new ArrayList<>();
                while (rs.next()) {
                    result.add(extractProject(rs));
                }
                return result;
            }
        });
    }

    public Project getProject(int projectId) {
        return template.query("select id, name, description, manager_id from project where id = :id",
                Collections.singletonMap("id", projectId), new ResultSetExtractor<Project>() {
                    @Override
                    public Project extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (!rs.next()) {
                            return null;
                        }

                        return extractProject(rs);
                    }
                });
    }

    public boolean addProject(Project project) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", project.getName());
        params.put("description", project.getDescription());
        params.put("managerId", project.getManagerId());

        return template.update("insert into project (name, description, manager_id) values " +
                "(:name, :description, :managerId)", params) > 0;
    }

    public boolean deleteProject(int projectId) {
        return template.update("delete from project where id = :id", Collections.singletonMap("id", projectId)) > 0;
    }

    public boolean deleteProject(String name) {
        return template.update("delete from project where name = :name", Collections.singletonMap("name", name)) > 0;
    }
}
