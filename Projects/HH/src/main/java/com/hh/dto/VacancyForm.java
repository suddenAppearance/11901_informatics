package com.hh.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class VacancyForm {
    Long id;
    String name;
    String sphere;
    String schedule;
    String type;
    String paymentSchedule;
    Integer experience;
    String place;
    String address;
    String requirements;
    String description;
    String[] tags;
    String contact_info;
    Integer salary;
}
