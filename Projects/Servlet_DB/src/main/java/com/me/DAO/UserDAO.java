package com.me.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    public Boolean createUser(String name){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into db.Person(name)" +
                    " values (?)");

            statement.setString(1, name);
            return statement.execute();
        } catch (SQLException exception){
            return false;
        }
    }
}
