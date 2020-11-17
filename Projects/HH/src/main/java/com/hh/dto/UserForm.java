package com.hh.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
@Setter
public class UserForm {
    String login;
    String password;
}
