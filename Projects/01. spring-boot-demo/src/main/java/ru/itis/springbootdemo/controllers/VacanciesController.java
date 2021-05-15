package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.springbootdemo.dto.VacancyForm;
import ru.itis.springbootdemo.models.Vacancy;
import ru.itis.springbootdemo.services.UsersService;
import ru.itis.springbootdemo.services.VacanciesService;

@Controller
public class VacanciesController {
    @Autowired
    VacanciesService vacanciesService;
    @Autowired
    UsersService usersService;


    @GetMapping("/vacancies")
    public String getVacancies(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
        }
        model.addAttribute("vacancies", vacanciesService.vacancies());
        return "vacancies_page";
    }

    @GetMapping("/vacancy")
    public String getVacancy(@RequestParam("id") Long vacancyId, Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
        }
        Vacancy vacancy = vacanciesService.findVacancy(vacancyId).orElseThrow(IllegalStateException::new);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("owner", authentication != null && vacancy.getAccount().getEmail().equals(authentication.getName()));
        model.addAttribute("isLiked", authentication != null && vacanciesService.isLiked(vacancyId, authentication.getName()));
        return "vacancy_page";
    }

    @GetMapping("/vacancy/create")
    public String getVacancyCreate(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
        }
        return "vacancy_create";
    }

    @PostMapping("vacancy/create")
    public String postVacancyCreate(VacancyForm vacancyForm, Authentication authentication) {
        vacanciesService.createVacancy(vacancyForm, authentication.getName());
        return "redirect:/profile/vacancies";
    }

    @GetMapping("vacancy/edit")
    public String getVacancyEdit(@RequestParam("id") Long vacancyId, Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
        }
        Vacancy vacancy = vacanciesService.findVacancy(vacancyId).orElseThrow(IllegalStateException::new);
        model.addAttribute("vacancy", vacancy);
        return "vacancy_edit";
    }

    @PostMapping("vacancy/edit")
    public String postVacancyEdit(@RequestParam("id") Long id, VacancyForm vacancyForm, Authentication authentication) {
        Vacancy vacancy = Vacancy.from(vacancyForm);
        vacancy.setId(id);
        vacancy.setAccount(usersService.findByEmail(authentication.getName()));
        vacanciesService.update(vacancy);
        return "redirect:/profile/vacancies";
    }

    @PostMapping("vacancy/delete")
    public String postVacancyDelete(@RequestParam("id") Long id) {
        vacanciesService.delete(id);
        return "redirect:/profile/vacancies";
    }

    @PostMapping("/vacancy/like")
    @ResponseBody
    public ResponseEntity<String> postVacancyLike(@RequestParam("id") Long id, Authentication authentication) {
        vacanciesService.like(id, authentication.getName());
        return ResponseEntity.ok("success");
    }

    @PostMapping("/vacancy/unlike")
    @ResponseBody
    public ResponseEntity<String> postVacancyUnLike(@RequestParam("id") Long id, Authentication authentication) {
        vacanciesService.unlike(id, authentication.getName());
        return ResponseEntity.ok("success");
    }

    @GetMapping("/profile/vacancies")
    public String getMyVacancies(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("vacancies", vacanciesService.vacanciesOf(authentication.getName()));
        return "vacancies_page";
    }

}
