package com.hh.repositories;

import com.hh.models.Vacancy;
import com.hh.models.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VacanciesRepositoryJdbcTemplateImpl implements VacanciesRepository {
    private final JdbcTemplate jdbcTemplate;
    private UsersRepository usersRepository;

    private final RowMapper<Vacancy> rowMapper = (row, rowNumber) -> Vacancy.builder()
            .id(row.getLong("id"))
            .creationDate(row.getDate("creation_date"))
            .name(row.getString("name"))
            .sphere(row.getString("sphere"))
            .schedule(row.getString("schedule"))
            .type(row.getString("type"))
            .paymentSchedule(row.getString("payment_schedule"))
            .experience(row.getInt("experience"))
            .place(row.getString("place"))
            .address(row.getString("address"))
            .requirements(row.getString("requirements"))
            .description(row.getString("description"))
            .tags((String[]) row.getArray("tags").getArray())
            .contact_info(row.getString("contact_info"))
            .salary(row.getInt("salary"))
            .account(usersRepository.findByLogin(row.getString("account")).orElse(null)).build();

    public VacanciesRepositoryJdbcTemplateImpl(DataSource dataSource, UsersRepository usersRepository) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.usersRepository = usersRepository;
    }

    @Override
    public void save(Vacancy entity) {
        //language=sql
        String sql_save = "insert into vacancy(" +
                "creation_date, " +
                "name, " +
                "sphere, " +
                "schedule, " +
                "type, " +
                "payment_schedule, " +
                "experience, " +
                "place, " +
                "address, " +
                "requirements, " +
                "description, " +
                "tags, " +
                "contact_info, " +
                "salary, " +
                "account) values (current_timestamp,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql_save, entity.getName(), entity.getSphere(),
                entity.getSchedule(), entity.getType(), entity.getPaymentSchedule(), entity.getExperience(),
                entity.getPlace(), entity.getAddress(), entity.getRequirements(), entity.getDescription(),
                entity.getTags(), entity.getContact_info(), entity.getSalary(), entity.getAccount().getLogin());

    }

    @Override
    public void update(Vacancy entity) {
        //language=sql
        String sql_update = "update vacancy set " +
                "name = ?," +
                "sphere = ?," +
                "schedule = ?," +
                "type = ?," +
                "payment_schedule = ?," +
                "experience = ?," +
                "place =?," +
                "address = ?," +
                "requirements = ?," +
                "description = ?," +
                "tags = ?," +
                "contact_info = ?," +
                "salary = ?" +
                "where id = ?";
        jdbcTemplate.update(sql_update, entity.getName(), entity.getSphere(),
                entity.getSchedule(), entity.getType(), entity.getPaymentSchedule(), entity.getExperience(),
                entity.getPlace(), entity.getAddress(), entity.getRequirements(), entity.getDescription(),
                entity.getTags(), entity.getContact_info(), entity.getSalary(), entity.getId());

    }

    @Override
    public void delete(Long id) {
        //language=SQL
        String sql_delete = "delete from vacancy where id = ?; delete from favorite_vacancies where vacancy = ?";
        jdbcTemplate.update(sql_delete, id, id);
    }

    @Override
    public List<Vacancy> findAll() {
        //language=sql
        String sql_find_all = "select * from vacancy";
        return jdbcTemplate.query(sql_find_all, rowMapper);
    }

    @Override
    public List<Vacancy> vacanciesOf(String login) {
        //language=sql
        String sql_vacancies_of = "select * from vacancy where account = ?";
        return jdbcTemplate.query(sql_vacancies_of, rowMapper, login);
    }

    @Override
    public Optional<Vacancy> findById(Long id) {
        //language=sql
        String sql_find_by_id = "select * from vacancy where id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql_find_by_id, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void like(Vacancy vacancy, User user) {
        //language=sql
        String sql_like = "insert into favorite_vacancies(vacancy, account) values (?, ?)";
        jdbcTemplate.update(sql_like, vacancy.getId(), user.getLogin());
    }

    @Override
    public void unlike(Vacancy vacancy, User user) {
        //language=sql
        String sql_unlike = "delete from favorite_vacancies where vacancy = ? and account = ?";
        jdbcTemplate.update(sql_unlike, vacancy.getId(), user.getLogin());
    }

    public boolean is_liked(Vacancy vacancy, User user) {
        //language=sql
        String sql_is_liked = "select count(*) from favorite_vacancies where vacancy = ? and account = ?";

        return jdbcTemplate.queryForObject(sql_is_liked, Long.class, vacancy.getId(), user.getLogin()) >= 1;
    }

    public List<Vacancy> liked(User user){
        List<Vacancy> list = new ArrayList<>();
        //language=sql
        String sql_liked = "select vacancy from favorite_vacancies where account = ?";
        List<Long> listOfVacancyIds = jdbcTemplate.queryForList(sql_liked, Long.class, user.getLogin());
        for (Long id: listOfVacancyIds
        ) {
            list.add(findById(id).orElse(null));
        }
        return list;
    }
}
