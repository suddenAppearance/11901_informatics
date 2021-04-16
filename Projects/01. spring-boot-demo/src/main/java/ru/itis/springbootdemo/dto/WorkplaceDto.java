package ru.itis.springbootdemo.dto;

import lombok.*;
import ru.itis.springbootdemo.models.Resume;
import ru.itis.springbootdemo.models.Workplace;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class WorkplaceDto {
    Long id;
    String companyName;
    String started;
    String finished;
    String description;
    Resume resume;

    public static WorkplaceDto from(Workplace workplace){
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        return WorkplaceDto.builder().companyName(workplace.getCompanyName())
                .id(workplace.getId())
                .started(df.format(workplace.getStarted()))
                .finished(df.format(workplace.getFinished()))
                .description(workplace.getDescription()).build();
    }
    public static List<WorkplaceDto> listFrom(List<Workplace> workplaces){
        List<WorkplaceDto> workplaceDtos = new ArrayList<>();
        for (Workplace workplace: workplaces
        ) {
            workplaceDtos.add(WorkplaceDto.from(workplace));
        }
        return workplaceDtos;
    }
}