package ru.hse.esadykov.dao;

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
public class UserDao {

    private User extractUser(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String username = rs.getString("username");
        String fullName = rs.getString("full_name");
        String email = rs.getString("email");
        return new User(id, username, fullName, email);
    }

    public List<User> getUsers() throws SQLException {
        List<User> result = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery("select id, username, full_name, email from user");

            while (resultSet.next()) {
                result.add(extractUser(resultSet));
            }
        }

        return result;
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
}
