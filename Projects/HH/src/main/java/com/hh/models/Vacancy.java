package com.hh.models;

import com.hh.dto.VacancyDto;
import com.hh.dto.VacancyForm;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Vacancy {
    Long id;
    Date creationDate;
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
    Integer salary;
    String contact_info;
    User account;

    public static Vacancy from(VacancyForm vacancyForm){
        return Vacancy.builder()
                .id(vacancyForm.getId())
                .name(vacancyForm.getName())
                .schedule(vacancyForm.getSchedule())
                .sphere(vacancyForm.getSphere())
                .type(vacancyForm.getType())
                .paymentSchedule(vacancyForm.getPaymentSchedule())
                .experience(vacancyForm.getExperience())
                .place(vacancyForm.getPlace())
                .address(vacancyForm.getAddress())
                .requirements(vacancyForm.getRequirements())
                .description(vacancyForm.getDescription())
                .tags(vacancyForm.getTags())
                .contact_info(vacancyForm.getContact_info())
                .salary(vacancyForm.getSalary()).build();
    }
}
