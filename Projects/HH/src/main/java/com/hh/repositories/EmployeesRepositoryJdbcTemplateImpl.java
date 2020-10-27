package com.hh.repositories;

import com.hh.models.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class EmployeesRepositoryJdbcTemplateImpl implements EmployeesRepository{
    private JdbcTemplate jdbcTemplate;

    public EmployeesRepositoryJdbcTemplateImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private RowMapper<Employee> employeeRowMapper = (row, rowNumber) -> Employee.builder().
            id(row.getLong("id"))
            .age(row.getInt("age"))
            .citizenship(row.getString("citizenship"))
            // .contact() to be done
            // .education_level() to be done
            .experience(row.getInt("experience"))
            .factual_address(row.getString("factual_address"))
            .male(row.getBoolean("male"))
            .name(row.getString("name"))
            .patronymics(row.getString("patronymics"))
            .registration_address(row.getString("registration_address"))
            .spheres((String[]) row.getArray("spheres").getArray())
            .surname(row.getString("surname"))
            .build();
    @Override
    public void save(Employee entity) {

    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }
}
