package com.hh.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class ResumeDto {
    String id;
    String name;
    Date created;
    Boolean readyToBusinessTrip;
    Boolean moving;
    String sphere;
    String schedule;
    String type;
    Integer experience;
    String description;
    String contact_info;
    Integer salary;
}
