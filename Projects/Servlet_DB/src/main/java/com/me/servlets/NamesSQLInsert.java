package com.me.servlets;

import com.me.repositories.UsersRepositoryJdbcImpl;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class NamesSQLInsert {
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "d06042001";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static Connection connection;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            for (int i = 0; i < 100; i++) {
                PreparedStatement preparedStatement = connection.
                        prepareStatement(
                                "insert into person(login, p_hash, firstname, lastname, age) values(?,?,?,?,?)"
                        );
                preparedStatement.setString(1, "user"+i);
                preparedStatement.setString(2, "password" +i);
                String[] name = s.nextLine().split(" ");
                preparedStatement.setString(3, name[0]);
                preparedStatement.setString(4, name[1]);
                preparedStatement.setInt(5, new Random().nextInt(60));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
