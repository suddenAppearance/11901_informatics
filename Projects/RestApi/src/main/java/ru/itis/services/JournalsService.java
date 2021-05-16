package ru.itis.services;

import ru.itis.dto.JournalDto;
import ru.itis.dto.JournalForm;
import ru.itis.models.Journal;

import java.util.List;
import java.util.Optional;

public interface JournalsService {
    List<JournalDto> all();
    JournalDto findById(Long id);
    JournalDto save(Journal journal);
    JournalDto returnKey(Journal journal);
    void delete(Long id);
    Optional<Journal> getLastByClassId(Long classroomId);
    Journal getById(Long pk);
}
