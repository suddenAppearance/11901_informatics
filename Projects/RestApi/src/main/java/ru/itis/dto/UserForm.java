package ru.itis.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserForm {
    String role;
    String fullName;
    String token;
}
