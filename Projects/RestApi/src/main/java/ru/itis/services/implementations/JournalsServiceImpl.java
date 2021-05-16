package ru.itis.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.itis.dto.JournalDto;
import ru.itis.models.Journal;
import ru.itis.repositories.JournalsRepository;
import ru.itis.services.JournalsService;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class JournalsServiceImpl implements JournalsService {
    @Autowired
    JournalsRepository journalsRepository;
    @Override
    public List<JournalDto> all() {
        return JournalDto.from(journalsRepository.findAll());
    }

    @Override
    public JournalDto findById(Long id) {
        return JournalDto.from(journalsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")));
    }

    @Override
    public JournalDto save(Journal journal) {
        journal.setTaken_at(Date.from(Instant.now()));
        return JournalDto.from(journalsRepository.save(journal));
    }

    @Override
    public JournalDto returnKey(Journal journal) {
        journal.setReturned_at(Date.from(Instant.now()));
        return JournalDto.from(journalsRepository.save(journal));
    }

    @Override
    public void delete(Long id) {
        if (!journalsRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
        journalsRepository.deleteById(id);
    }

    @Override
    public Journal getById(Long pk) {
        return journalsRepository.findById(pk).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }
}
