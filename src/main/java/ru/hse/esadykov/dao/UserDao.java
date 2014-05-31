package ru.hse.esadykov.dao;

import ru.hse.esadykov.ConnectionFactory;
import ru.hse.esadykov.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
public class UserDao {

    public List<User> getUsers() throws SQLException {
        List<User> result = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            ResultSet resultSet = connection.createStatement().executeQuery("select id, username, full_name, email from user");

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                result.add(new User(id, username, fullName, email));
            }
        }

        return result;
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
