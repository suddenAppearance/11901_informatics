package com.hh.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hh.dto.WorkplaceForm;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public static Workplace from(WorkplaceForm workplaceForm) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        return Workplace.builder().id(workplaceForm.getId())
                .companyName(workplaceForm.getCompanyName())
                .started(simpleDateFormat.parse(workplaceForm.getStarted()))
                .finished(simpleDateFormat.parse(workplaceForm.getStarted()))
                .description(workplaceForm.getDescription())
                .resume(Resume.builder().id(workplaceForm.getResume()).build()) // only id needed to save
                .build();
    }

}
