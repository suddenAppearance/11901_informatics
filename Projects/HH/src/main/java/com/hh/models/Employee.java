package com.hh.models;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Employee {
    Long id;
    String name;
    String surname;
    String patronymics;
    Integer age;
    Boolean male;
    String citizenship;
    String registration_address;
    String factual_address;
    Education_level education_level;
    Integer experience;
    String[] spheres;
    Contact contact;
    String login;
    String password_hash;
}
