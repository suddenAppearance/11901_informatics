package ru.itis.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.itis.dto.ClassroomDto;
import ru.itis.dto.ClassroomForm;
import ru.itis.models.Classroom;
import ru.itis.repositories.ClassroomsRepository;
import ru.itis.services.ClassroomsService;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomsServiceImpl implements ClassroomsService {
    @Autowired
    ClassroomsRepository classroomsRepository;

    @Override
    public List<ClassroomDto> all() {
        return ClassroomDto.from(classroomsRepository.findAll());
    }

    @Override
    public ClassroomDto findById(Long id) {
        return ClassroomDto.from(classroomsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such classroom")));
    }

    @Override
    public ClassroomDto save(Classroom classroom) {
        return ClassroomDto.from(classroomsRepository.save(classroom));
    }

    @Override
    public ClassroomDto update(Long id, ClassroomForm newForm) {
        Classroom classroom = classroomsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such classroom"));
        classroom.setNumber(newForm.getNumber());
        return ClassroomDto.from(classroomsRepository.save(classroom));
    }

    @Override
    public void delete(Long id) {
        if (classroomsRepository.existsById(id)) {
            classroomsRepository.deleteById(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such classroom");
        }
    }

    @Override
    public Classroom getById(Long classroomId) {
        return classroomsRepository.findById(classroomId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such classroom"));
    }
}
