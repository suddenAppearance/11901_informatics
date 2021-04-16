package ru.itis.springbootdemo.dto;


import lombok.*;
import ru.itis.springbootdemo.models.Vacancy;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class VacancyDto {
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
    String contactInfo;
    Integer salary;

    public static VacancyDto from(Vacancy vacancy){
        return VacancyDto.builder()
                .id(vacancy.getId())
                .creationDate(vacancy.getCreated())
                .name(vacancy.getName())
                .schedule(vacancy.getSchedule())
                .type(vacancy.getType())
                .paymentSchedule(vacancy.getPaymentSchedule())
                .experience(vacancy.getExperience())
                .place(vacancy.getPlace())
                .address(vacancy.getAddress())
                .requirements(vacancy.getRequirements())
                .description(vacancy.getDescription())
                .contactInfo(vacancy.getContactInfo())
                .salary(vacancy.getSalary()).build();
    }
}