package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.itis.dto.JournalDto;
import ru.itis.dto.JournalForm;
import ru.itis.models.Journal;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.services.ClassroomsService;
import ru.itis.services.JournalsService;
import ru.itis.services.UsersService;

import java.util.List;

@RestController
public class JournalController {

    @Autowired
    JournalsService journalsService;

    @Autowired
    UsersService usersService;

    @Autowired
    ClassroomsService classroomsService;

    @CrossOrigin(origins = "http://localhost")
    @GetMapping("/journals")
    public ResponseEntity<List<JournalDto>> listJournals() {
        return ResponseEntity.ok(journalsService.all());
    }

    @CrossOrigin(origins = "http://localhost")
    @GetMapping("/journals/{pk}")
    public ResponseEntity<JournalDto> journalInstance(@PathVariable("pk") Long pk) {
        return ResponseEntity.ok(journalsService.findById(pk));
    }

    @CrossOrigin(origins = "http://localhost")
    @PostMapping("/journals")
    public ResponseEntity<JournalDto> createJournal(@RequestBody JournalForm journalForm) {
        Journal journal = journalsService.getLastByClassId(journalForm.getClassroomId()).orElse(null);
        if (journal != null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Key need to be returned before it can be taken");
        }
        return ResponseEntity.ok(journalsService.save(Journal.builder()
                .user(usersService.findByToken(journalForm.getToken()))
                .classroom(classroomsService.getById(journalForm.getClassroomId())).build()));
    }

    @CrossOrigin(origins = "http://localhost")
    @PostMapping("/journals/{pk}/returnKey")
    public ResponseEntity<JournalDto> updateJournal(@PathVariable("pk") Long pk, @RequestBody JournalForm form) {
        String token = form.getToken();
        User user = usersService.findByToken(token);
        Journal journal = journalsService.getById(pk);
        if (journal.getReturned_at() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already returned");
        }
        if (!journal.getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Key was taken by other user");
        }
        return ResponseEntity.ok(journalsService.returnKey(journal));
    }

    @CrossOrigin(origins = "http://localhost")
    @DeleteMapping("/journals/{pk}")
    public ResponseEntity<?> deleteJournal(@PathVariable("pk") Long pk) {
        journalsService.delete(pk);
        return ResponseEntity.ok().build();
    }
}
