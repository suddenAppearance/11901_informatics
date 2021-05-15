package ru.itis.springbootdemo.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ResumeForm {
    String id;
    String name;
    Date created;
    Boolean readyToBusinessTrip;
    Boolean moving;
    String schedule;
    String type;
    String description;
    String contactInfo;
    Integer salary;
}