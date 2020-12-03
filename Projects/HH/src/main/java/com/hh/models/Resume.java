package com.hh.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class    Resume {
    Long id;
    String name;
    Date created;
    Boolean readyToBusinessTrip;
    Boolean moving;
    String sphere;
    String schedule;
    String type;
    String description;
    Integer salary;
    String contact_info;
    User account;
}
