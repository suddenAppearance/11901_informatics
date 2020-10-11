package com.me.repositories;

import com.me.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ALL_USERS
            = "select * from person";
    //language=SQL
    private static final String SQL_FIND_ALL_USERS_BY_AGE
            = "select * from person where age = ?";
    //language=SQL
    private static final String SQL_INSERT_USER
            = "insert into person(login, p_hash, firstname, lastname, age) values (?, ?, ?, ?, ?)";
    private Connection connection;

    private SimpleJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
        this.jdbcTemplate = new SimpleJdbcTemplate(connection);
    }

    private RowMapper<User> usersRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .age(row.getInt("age"))
            .build();

    @Override
    public List<User> findAllByAge(Integer age) {
        return jdbcTemplate.queryForList(SQL_FIND_ALL_USERS_BY_AGE, usersRowMapper, age);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.queryForList(SQL_FIND_ALL_USERS, usersRowMapper);
    }

    @Override
    public void save(User entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword_hash());
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getLastName());
            preparedStatement.setInt(5, entity.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new IllegalArgumentException("wrong input");
        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
