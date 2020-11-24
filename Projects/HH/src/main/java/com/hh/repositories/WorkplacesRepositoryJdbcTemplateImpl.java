package com.hh.repositories;

import com.hh.models.Resume;
import com.hh.models.Workplace;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class WorkplacesRepositoryJdbcTemplateImpl implements WorkplacesRepository {
    JdbcTemplate jdbcTemplate;
    ResumesRepository resumesRepository;
    RowMapper<Workplace> workplaceRowMapper = (row, RowNumber) -> Workplace.builder()
            .id(row.getLong("id"))
            .companyName(row.getString("company_name"))
            .started(row.getDate("started"))
            .finished(row.getDate("finished"))
            .description(row.getString("description"))
            .resume(resumesRepository.findById(row.getLong("resume")).orElse(null)).build();

    public WorkplacesRepositoryJdbcTemplateImpl(DataSource dataSource, ResumesRepository resumesRepository){
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.resumesRepository = resumesRepository;
    }
    @Override
    public List<Workplace> workplacesAtResume(Resume resume) {
        //language=sql
        String sql_find = "select * from workplace where resume = ?";
        return jdbcTemplate.query(sql_find, workplaceRowMapper, resume.getId());
    }

    @Override
    public Optional<Workplace> findById(Long id) {
        //language=sql
        String sql_find_by_id = "select * from workplace where id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql_find_by_id, workplaceRowMapper, id));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void save(Workplace entity) {
        //I need to get id integer back;
    }

    @Override
    public Long saveEntity(Workplace entity) {
        //language=sql
        String sql_save = "insert into workplace(company_name, started, finished, description, id, resume) VALUES (?, ?, ?,?,?,?)";
        //language=sql
        String sql_get_id = "select nextval('workplace_id_seq')";
        Long id = jdbcTemplate.queryForObject(sql_get_id, Long.class);
        jdbcTemplate.update(sql_save, entity.getCompanyName(), entity.getStarted(), entity.getFinished(), entity.getDescription(),
                id, entity.getResume().getId());
        return id;
    }

    @Override
    public void update(Workplace entity) {
        //language=sql
        String sql_update = "update workplace set company_name = ?, started = ?, finished = ?, description = ? where id = ?;";
        jdbcTemplate.update(sql_update, entity.getCompanyName(), entity.getStarted(), entity.getFinished(),
                entity.getDescription(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        //language=sql
        String sql_delete = "delete from workplace where id = ?";
        jdbcTemplate.update(sql_delete, id);
    }

    @Override
    public List<Workplace> findAll() {
        //language=sql
        String sql_find_all = "select * from workplace";
        return jdbcTemplate.query(sql_find_all, workplaceRowMapper);
    }
}
