package ru.itis.models;

import lombok.*;
import ru.itis.dto.UserForm;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "Person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(value = EnumType.STRING)
    Role role;
    String fullName;
    @Column(unique = true)
    String token;
    @Column(unique = true)
    @OneToMany(mappedBy = "user")
    List<Journal> history;

    public static User from(UserForm userForm){
        return User.builder()
                .role(Role.valueOf(userForm.getRole()))
                .fullName(userForm.getFullName())
                .token(userForm.getToken())
                .build();
    }
}
