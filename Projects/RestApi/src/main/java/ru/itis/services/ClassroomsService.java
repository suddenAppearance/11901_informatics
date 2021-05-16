package ru.itis.services;

import ru.itis.dto.ClassroomDto;
import ru.itis.dto.ClassroomForm;
import ru.itis.models.Classroom;

import java.util.List;
import java.util.Optional;

public interface ClassroomsService {
    List<ClassroomDto> all();

    ClassroomDto findById(Long id);

    ClassroomDto save(Classroom classroom);

    ClassroomDto update(Long id, ClassroomForm newForm);

    void delete(Long id);

    Classroom getById(Long classroomId);
}
