package com.hh.services;

import com.hh.dto.UserDto;
import com.hh.dto.VacancyForm;
import com.hh.models.User;
import com.hh.models.Vacancy;
import com.hh.repositories.VacanciesRepository;

import java.util.List;
import java.util.Optional;

public class VacanciesService {
    private VacanciesRepository vacanciesRepository;

    public VacanciesService(VacanciesRepository vacanciesRepository) {
        this.vacanciesRepository = vacanciesRepository;
    }

    public List<Vacancy> vacanciesOf(String login){
        return vacanciesRepository.vacanciesOf(login);
    }

    public List<Vacancy> vacancies(){
        return vacanciesRepository.findAll();
    }
    public void createVacancy(VacancyForm vacancyForm, UserDto userDto){
        Vacancy vacancy = Vacancy.from(vacancyForm);
        vacancy.setAccount(User.from(userDto));
        vacanciesRepository.save(vacancy);
    }

    public void delete(Long id){
        vacanciesRepository.delete(id);
    }

    public Optional<Vacancy> findVacancy(Long id){
        return vacanciesRepository.findById(id);
    }

    public void update(Vacancy vacancy){
        vacanciesRepository.update(vacancy);
    }
}
