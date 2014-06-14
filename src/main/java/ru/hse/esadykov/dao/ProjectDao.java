package ru.hse.esadykov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.hse.esadykov.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<Project> getProjects() throws SQLException {
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

    public Project getProject(int projectId) throws SQLException {
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

    public boolean deleteProject(int projectId) throws SQLException {
        return template.update("delete from project where id = :id", Collections.singletonMap("id", projectId)) > 0;
    }

}
