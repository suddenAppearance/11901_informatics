package com.hh.dto;

import com.hh.models.Resume;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class WorkplaceForm {
    Long id;
    String companyName;
    Date started;
    Date finished;
    String description;
    Long resume;
}
