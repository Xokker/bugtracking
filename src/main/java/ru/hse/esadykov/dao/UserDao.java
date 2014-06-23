package ru.hse.esadykov.dao;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.esadykov.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
@Repository("userDao")
public class UserDao {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public List<User> getUsers() {
        return template.query("select id, username, full_name, email from user", new UserMapper());
    }

    @Transactional
    public User getUser(int userId) {
        User result = template.queryForObject("select id, username, full_name, email from user where id = :id",
                Collections.singletonMap("id", userId), new UserMapper());
        try {
            template.queryForObject("SELECT 1 FROM user_roles WHERE user_id=:userId AND role_id = 2",
                    Collections.singletonMap("userId", userId),
                    Integer.class);
            result.setAdmin(true);
        } catch (EmptyResultDataAccessException e) {
            result.setAdmin(false);
        }

        return result;
    }

    @Transactional
    public boolean saveUser(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("fullName", user.getFullName());
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());

        KeyHolder holder = new GeneratedKeyHolder();

        int res = template.update("insert into user (username, full_name, email, password) " +
                "values (:username, :fullName, :email, :password)", new MapSqlParameterSource(params), holder);
        if (res == 0) {
            return false;
        }

        Map<String, Object> params2 = new HashMap<>();
        params2.put("userId", holder.getKey().intValue());
        params2.put("roleId", user.isAdmin() ? 2 : 1);

        template.update("insert into user_roles value (:userId, :roleId)", params2);
        return true;
    }

    public boolean deleteUser(int userId) {
        return template.update("delete from user where id = :id",
                Collections.singletonMap("id", userId)) > 0;
    }

    public boolean deleteUser(String username) {
        return template.update("delete from user where username = :username",
                Collections.singletonMap("username", username)) > 0;
    }

    public User getUser(String username) {
        return template.queryForObject("select id, username, full_name, email from user where username = :username",
                Collections.singletonMap("username", username), new UserMapper());
    }

    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            Integer id = rs.getInt("id");
            String username = rs.getString("username");
            String fullName = rs.getString("full_name");
            String email = rs.getString("email");

            return new User(id, username, fullName, email, null);
        }
    }

    @Transactional
    public boolean updateUser(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("fullName", user.getFullName());
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());

        int res = template.update("UPDATE user SET " +
                "full_name = :fullName, email = :email WHERE id = :id", params);
        ImmutableMap<String, Integer> role = ImmutableMap.of("userId", user.getId(), "roleId", 2);
        if (user.isAdmin()) {
            template.update("insert ignore into user_roles(user_id, role_id) values (:userId, :roleId)", role);
        } else {
            template.update("delete from user_roles where user_id = :userId and role_id = :roleId", role);
        }

        if (user.getPassword() != null) {
            res *= template.update("UPDATE user SET password = :password WHERE id=:id", params);
        }

        return res != 0;
    }

}
