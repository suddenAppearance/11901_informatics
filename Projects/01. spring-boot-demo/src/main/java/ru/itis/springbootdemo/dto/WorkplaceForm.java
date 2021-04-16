package ru.itis.springbootdemo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class WorkplaceForm {
    Long id;
    String companyName;
    String started;
    String finished;
    String description;
    Long resume;

}