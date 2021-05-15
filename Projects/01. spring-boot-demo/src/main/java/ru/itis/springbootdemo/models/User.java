package ru.itis.springbootdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.itis.springbootdemo.dto.UserDto;

import javax.persistence.*;
import java.util.List;

/**
 * 10.02.2021
 * spring-boot-demo
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String avatarUrl;
    @Column(unique = true)
    private String email;
    @Enumerated(value = EnumType.STRING)
    private State state;
    private String phone;
    private String confirmCode;
    @Column(unique = true)
    private String username;
    private String password;
    @OneToMany(mappedBy = "account")
    private List<Vacancy> vacancies;
    @OneToMany(mappedBy = "account")
    private List<Resume> resumes;
    private Role role;
    @ManyToMany()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "vacancy_like",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "vacancy_id"))
    private List<Vacancy> favouriteVacancies;
    @ManyToMany()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
            name = "resume_like",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "vacancy_id"))
    private List<Resume> favouriteResumes;

    public void like_vacancy(Vacancy vacancy) {
        this.favouriteVacancies.add(vacancy);
        vacancy.likes.add(this);
    }

    public void unlike_vacancy(Vacancy vacancy) {
        this.favouriteVacancies.remove(vacancy);
        vacancy.likes.remove(this);
    }

    public void like_resume(Resume resume) {
        this.favouriteResumes.add(resume);
        resume.likes.add(this);
    }

    public void unlike_resume(Resume resume){
        this.favouriteResumes.remove(resume);
        resume.likes.remove(this);
    }
}
