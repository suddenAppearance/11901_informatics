package com.hh.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hh.dto.WorkplaceForm;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Workplace {
    Long id;
    String companyName;
    Date started;
    Date finished;
    String description;
    Resume   resume;

    public static Workplace from(WorkplaceForm workplaceForm){
        return Workplace.builder().id(workplaceForm.getId())
                .companyName(workplaceForm.getCompanyName())
                .started(workplaceForm.getStarted())
                .finished(workplaceForm.getFinished())
                .description(workplaceForm.getDescription())
                .resume(Resume.builder().id(workplaceForm.getResume()).build()) // only id needed to save
                .build();
    }
}
