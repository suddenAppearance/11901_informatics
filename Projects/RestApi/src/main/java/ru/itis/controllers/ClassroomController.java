package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.itis.dto.ClassroomDto;
import ru.itis.dto.ClassroomForm;
import ru.itis.models.Classroom;
import ru.itis.services.ClassroomsService;

import java.util.List;

@RestController
public class ClassroomController {

    @Autowired
    ClassroomsService classroomsService;

    @CrossOrigin(origins = "http://localhost")
    @GetMapping("/classrooms")
    public ResponseEntity<List<ClassroomDto>> listClassrooms() {
        return ResponseEntity.ok(classroomsService.all());
    }

    @CrossOrigin(origins = "http://localhost")
    @GetMapping("/classrooms/{pk}")
    public ResponseEntity<ClassroomDto> classroomInstance(@PathVariable("pk") Long pk) {
        return ResponseEntity.ok(classroomsService.findById(pk));
    }

    @CrossOrigin(origins = "http://localhost")
    @PostMapping("/classrooms")
    public ResponseEntity<ClassroomDto> createClassroom(@RequestBody ClassroomForm classroomForm) {
        return ResponseEntity.ok(classroomsService.save(Classroom.from(classroomForm)));
    }

    @CrossOrigin(origins = "http://localhost")
    @PutMapping("/classrooms/{pk}")
    public ResponseEntity<ClassroomDto> updateClassroom(@PathVariable("pk") Long pk, @RequestBody ClassroomForm classroomForm) {
        return ResponseEntity.ok(classroomsService.update(pk, classroomForm));
    }

    @CrossOrigin(origins = "http://localhost")
    @DeleteMapping("/classrooms/{pk}")
    public ResponseEntity<?> deleteClassroom(@PathVariable("pk") Long pk) {
        classroomsService.delete(pk);
        return ResponseEntity.ok().build();
    }
}
