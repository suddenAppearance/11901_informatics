package com.hh.repositories;

import com.hh.models.Resume;
import com.hh.models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResumesRepositoryJdbcTemplateImpl implements ResumesRepository {
    UsersRepository usersRepository;
    JdbcTemplate jdbcTemplate;
    RowMapper<Resume> resumeRowMapper = (row, rowNumber) -> Resume.builder()
            .name(row.getString("name"))
            .id(row.getLong("id"))
            .created(row.getTimestamp("created"))
            .readyToBusinessTrip(row.getBoolean("ready_to_business_trip"))
            .moving(row.getBoolean("moving"))
            .sphere(row.getString("sphere"))
            .account(usersRepository.findByLogin(row.getString("account")).orElse(null))
            .schedule(row.getString("schedule"))
            .type(row.getString("type"))
            .description(row.getString("description"))
            .salary(row.getInt("salary"))
            .contact_info(row.getString("contact_info")).build();

    public ResumesRepositoryJdbcTemplateImpl(DataSource dataSource, UsersRepository usersRepository) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.usersRepository = usersRepository;
    }

    @Override
    public Long saveEntity(Resume entity) {
        //language=sql
        String sql_id = "select nextval('resume_id_seq')";
        Long id = jdbcTemplate.queryForObject(sql_id, Long.class);
        //language=sql
        String sql_save = "insert into resume(id, name, created, ready_to_business_trip, moving," +
                " sphere, schedule, type, description, salary, account, contact_info) values" +
                "(?, ?, current_timestamp, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql_save, id, entity.getName(), entity.getReadyToBusinessTrip(), entity.getMoving(),
                entity.getSphere(), entity.getSchedule(), entity.getType(), entity.getDescription(),
                entity.getSalary(), entity.getAccount().getLogin(), entity.getContact_info());
        return id;
    }

    @Override
    public List<Resume> resumesOf(String login) {
        //language=sql
        String sql_resumes_of = "select * from resume where account = ?";
        return jdbcTemplate.query(sql_resumes_of, resumeRowMapper, login);
    }

    @Override
    public Optional<Resume> findById(Long id) {
        //language=sql
        String sql_find_by_id = "select * from resume where id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql_find_by_id, resumeRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void like(Resume resume, User user) {
        //language=sql
        String sql_like = "insert into favorite_resumes(resume, account)  values (?, ?)";
        jdbcTemplate.update(sql_like, resume.getId(), user.getLogin());
    }

    @Override
    public void unlike(Resume resume, User user) {
        //language=sql
        String sql_unlike = "delete from favorite_resumes where resume = ? and account = ?";
        jdbcTemplate.update(sql_unlike, resume.getId(), user.getLogin());
    }

    @Override
    public boolean is_liked(Resume resume, User user) {
        //language=sql
        String sql_is_liked = "select count(*) from favorite_resumes where resume = ? and account = ?";
        return jdbcTemplate.queryForObject(sql_is_liked, Long.class, resume.getId(), user.getLogin()) >= 1;
    }

    @Override
    public List<Resume> liked(User user) {
        List<Resume> list = new ArrayList<>();
        //language=sql
        String sql_liked = "select resume from favorite_resumes where account = ?";
        List<Long> listOfResumeIds = jdbcTemplate.queryForList(sql_liked, Long.class, user.getLogin());
        for (Long id : listOfResumeIds
        ) {
            list.add(findById(id).orElse(null));
        }
        return list;
    }

    @Override
    public void save(Resume entity) {
        //language=sql
        String sql_save = "insert into resume(name, created, ready_to_business_trip, moving," +
                " sphere, schedule, type, description, salary, account, contact_info) values" +
                "(?, current_timestamp, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql_save, entity.getName(), entity.getReadyToBusinessTrip(), entity.getMoving(),
                entity.getSphere(), entity.getSchedule(), entity.getType(), entity.getDescription(),
                entity.getSalary(), entity.getAccount().getLogin(), entity.getContact_info());
    }

    @Override
    public void update(Resume entity) {
        //language=sql
        String sql_update = "update resume set name=?, ready_to_business_trip = ?, moving = ?, sphere = ?, schedule = ?," +
                "type = ?, description = ?, salary = ?, account = ?, contact_info = ? where id = ?";
        jdbcTemplate.update(sql_update, entity.getName(), entity.getReadyToBusinessTrip(), entity.getMoving(),
                entity.getSphere(), entity.getSchedule(), entity.getType(), entity.getDescription(),
                entity.getSalary(), entity.getAccount().getLogin(), entity.getContact_info(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        //language=sql
        String sql_delete = "delete from workplace where resume = ?; delete from favorite_resumes where resume = ?;delete from resume where id = ?;";
        jdbcTemplate.update(sql_delete,id , id, id);
    }

    @Override
    public List<Resume> findAll() {
        //language=sql
        String sql_find_all = "select * from resume";
        return jdbcTemplate.query(sql_find_all, resumeRowMapper);
    }

}
