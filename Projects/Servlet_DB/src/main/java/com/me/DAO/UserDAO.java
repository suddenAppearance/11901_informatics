package com.me.DAO;

import com.me.Models.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public Boolean createUser(String name, String surname, Integer age) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into db.person(firstname, lastname, age)" +
                    " values (?, ?, ?)");

            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setInt(3, age);
            return statement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }
    public ArrayList<User> allUsers(){
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from db.person");
            while (resultSet.next()){
                users.add(new User(resultSet.getLong("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age")));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return users;
    }
}
