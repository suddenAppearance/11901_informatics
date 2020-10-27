package com.hh.models;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
public class Vacancy {
    Long id;
    Timestamp creation_date;
    String name;
    String sphere;
    String schedule;
    String type;
    String payment_schedule;
    Integer experience;
    String place;
    String address;
    String requirements;
    String description;
    String[] tags;
    Contact contact;
    Employer employer;
    Integer salary;
}
