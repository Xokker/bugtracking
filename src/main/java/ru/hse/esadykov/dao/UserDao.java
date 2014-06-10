package ru.hse.esadykov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.hse.esadykov.ConnectionFactory;
import ru.hse.esadykov.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        try (Connection connection = ConnectionFactory.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery("select id, username, full_name, email from user");
            if (!resultSet.next()) {
                return null;
            }

            return extractUser(resultSet);
        }
    }

    public boolean saveUser(User user) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into user (username, full_name, email) values (?, ?, ?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFullName());
            preparedStatement.setString(3, user.getEmail());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean deleteUser(int userId) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from user where id = ?");
            preparedStatement.setInt(1, userId);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean addUser(User user) throws SQLException {
        try (Connection con = ConnectionFactory.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "insert into user (username, full_name, email) values (?, ?, ?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getEmail());

            return ps.executeUpdate() > 0;
        }
    }
}
