package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.VacancyForm;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.models.Vacancy;
import ru.itis.springbootdemo.repositories.UsersRepository;
import ru.itis.springbootdemo.repositories.VacanciesRepository;

import java.util.List;
import java.util.Optional;

@Component
public class VacanciesServiceImpl implements VacanciesService {
    @Autowired
    VacanciesRepository vacanciesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<Vacancy> vacanciesOf(String email) {
        return vacanciesRepository.findByAccountEmail(email);
    }

    @Override
    public List<Vacancy> vacancies() {
        return vacanciesRepository.findAll();
    }

    @Override
    public void createVacancy(VacancyForm vacancyForm, String userEmail) {
        Vacancy vacancy = Vacancy.from(vacancyForm);
        vacancy.setAccount(usersRepository.findByEmail(userEmail).orElseThrow(IllegalStateException::new));
        vacanciesRepository.save(vacancy);
    }

    @Override
    public void delete(Long id) {
        Vacancy vacancy = vacanciesRepository.findById(id).orElseThrow(IllegalStateException::new);
        List<User> users = vacancy.getLikes();
        int i = 0;
        while (i < users.size()) {
            User user = users.get(i);
            user.unlike_vacancy(vacancy);
            usersRepository.save(user);
            i++;
        }
        vacanciesRepository.save(vacancy);
        vacanciesRepository.deleteById(id);
    }

    @Override
    public Optional<Vacancy> findVacancy(Long id) {
        return vacanciesRepository.findById(id);
    }

    @Override
    public void update(Vacancy vacancy) {
        vacanciesRepository.save(vacancy);
    }



    @Override
    public void like(Long vacancyId, String userEmail) {
        Vacancy vacancy = vacanciesRepository.findById(vacancyId).orElseThrow(IllegalStateException::new);
        User user = usersRepository.findByEmail(userEmail).orElseThrow(IllegalStateException::new);
        user.like_vacancy(vacancy);
        vacanciesRepository.save(vacancy);
        }

    @Override
    public void unlike(Long vacancyId, String userEmail) {
        Vacancy vacancy = vacanciesRepository.findById(vacancyId).orElseThrow(IllegalStateException::new);
        User user = usersRepository.findByEmail(userEmail).orElseThrow(IllegalStateException::new);
        user.unlike_vacancy(vacancy);
        vacanciesRepository.save(vacancy);
    }

    @Override
    public boolean isLiked(Long vacancyId, String userEmail) {
        Vacancy vacancy = vacanciesRepository.findById(vacancyId).orElseThrow(IllegalStateException::new);
        User user = usersRepository.findByEmail(userEmail).orElseThrow(IllegalStateException::new);
        return user.getFavouriteVacancies().contains(vacancy);
    }
}
