package ru.itis.springbootdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @ManyToMany
    @JoinTable(
            name = "vacancy_like",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "vacancy_id"))
    private List<Vacancy> favouriteVacancies;


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public void like(Vacancy vacancy){
        this.favouriteVacancies.add(vacancy);
        vacancy.likes.add(this);
    }
    public void unlike(Vacancy vacancy){
        this.favouriteVacancies.remove(vacancy);
        vacancy.likes.remove(this);
    }
}
