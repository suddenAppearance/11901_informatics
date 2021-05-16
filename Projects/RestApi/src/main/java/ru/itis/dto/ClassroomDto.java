package ru.itis.dto;

import lombok.*;
import ru.itis.models.Classroom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClassroomDto {
    Long id;
    Short number;
    List<String> history;

    public static ClassroomDto from(Classroom classroom) {

        return ClassroomDto.builder()
                .id(classroom.getId())
                .number(classroom.getNumber())
                .history(classroom.getHistory() == null ? new ArrayList<>() : classroom.getHistory().stream().map((s) -> s.getUser().getFullName()).collect(Collectors.toList()))
                .build();

    }

    public static List<ClassroomDto> from(List<Classroom> list) {
        return list.stream().map(ClassroomDto::from).collect(Collectors.toList());
    }
}
