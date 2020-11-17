package com.hh.dto;

import com.hh.models.Vacancy;
import lombok.*;

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
    String[] tags;
    String contact_info;
    Integer salary;

    public static VacancyDto from(Vacancy vacancy){
        return VacancyDto.builder()
                .id(vacancy.getId())
                .creationDate(vacancy.getCreationDate())
                .name(vacancy.getName())
                .schedule(vacancy.getSchedule())
                .sphere(vacancy.getSphere())
                .type(vacancy.getType())
                .paymentSchedule(vacancy.getPaymentSchedule())
                .experience(vacancy.getExperience())
                .place(vacancy.getPlace())
                .address(vacancy.getAddress())
                .requirements(vacancy.getRequirements())
                .description(vacancy.getDescription())
                .tags(vacancy.getTags())
                .contact_info(vacancy.getContact_info())
                .salary(vacancy.getSalary()).build();
    }
}
