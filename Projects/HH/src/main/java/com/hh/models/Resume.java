package com.hh.models;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Resume {
    Long id;
    String name;
    String created;
    Boolean ready_to_business_trip;
    Boolean moving;
    String sphere;
    String schedule;
    String type;
    Integer experience;
    String description;
    Employee employee;
    Integer salary;
}
