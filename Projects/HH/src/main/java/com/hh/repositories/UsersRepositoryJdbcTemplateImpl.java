package com.hh.repositories;

import com.hh.models.User;
import org.postgresql.util.PSQLException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .login(row.getString("login"))
                    .password_hash(row.getString("password_hash")).build();

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        //language=sql
        String sql_find = "select * from account where login = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql_find, userRowMapper, login));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User entity) {
        //language=sql
        String sql_save = "insert into account(login, password_hash) values (?, ?)";
        jdbcTemplate.update(sql_save, entity.getLogin(), entity.getPassword_hash());
    }

    @Override
    public boolean changeLogin(String oldLogin, String login) {
        //language=sql
        String sql_changeLogin = "update account set login = ? where login = ?";
        try {
            jdbcTemplate.update(sql_changeLogin, login, oldLogin);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public void changePassword(String login, String password_hash) {
        //language=sql
        String sql_changePassword = "update account set password_hash = ? where login = ?";
        jdbcTemplate.update(sql_changePassword, password_hash, login);
    }

    @Override
    public void update(User entity) {
        //Useless
    }

    @Override
    public void delete(Long id) {
        //No id support
    }

    @Override
    public void delete(String login){
        //language=sql
        String sql_delete = "delete from account where login = ?";
        jdbcTemplate.update(sql_delete, login);
    }

    @Override
    public List<User> findAll() {
        //language=sql
        String sql_findAll = "select * from account";
        return jdbcTemplate.query(sql_findAll, userRowMapper);
    }
}
