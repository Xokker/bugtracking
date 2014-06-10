package ru.hse.esadykov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.hse.esadykov.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
@Repository("userDao")
public class UserDao {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private User extractUser(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String username = rs.getString("username");
        String fullName = rs.getString("full_name");
        String email = rs.getString("email");
        return new User(id, username, fullName, email);
    }

    public List<User> getUsers() throws SQLException {
        return template.query("select id, username, full_name, email from user", new ResultSetExtractor<List<User>>() {
            @Override
            public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<User> result = new ArrayList<>();
                while (rs.next()) {
                    result.add(extractUser(rs));
                }
                return result;
            }
        });
    }

    public User getUser(int userId) throws SQLException {
        return template.query("select id, username, full_name, email from user", new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (!rs.next()) {
                    return null;
                }

                return extractUser(rs);
            }
        });
    }

    public boolean saveUser(User user) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("fullName", user.getFullName());
        params.put("email", user.getEmail());

        return template.update("insert into user (username, full_name, email) values (:username, :fullName, :email)", params) > 0;
    }

    public boolean deleteUser(int userId) throws SQLException {
        return template.update("delete from user where id = :id", Collections.singletonMap("id", userId)) > 0;
    }

    public boolean addUser(User user) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("fullName", user.getFullName());
        params.put("email", user.getEmail());

        return template.update("insert into user (username, full_name, email) values (:username, :fullName, :email)", params) > 0;
    }
}
