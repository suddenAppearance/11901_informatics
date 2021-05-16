package ru.itis.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JournalForm {
    String token;
    Long classroomId;
}
