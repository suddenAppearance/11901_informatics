package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.itis.models.Journal;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class JournalDto {
    String userFullName;
    Short classroomNumber;
    Date taken_at;
    Date returned_at;

    public static JournalDto from(Journal journal) {
        return JournalDto.builder()
                .userFullName(journal.getUser().getFullName())
                .classroomNumber(journal.getClassroom().getNumber())
                .taken_at(journal.getTaken_at())
                .returned_at(journal.getReturned_at()).build();
    }

    public static List<JournalDto> from(List<Journal> journals){
        return journals.stream().map(JournalDto::from).collect(Collectors.toList());
    }
}
