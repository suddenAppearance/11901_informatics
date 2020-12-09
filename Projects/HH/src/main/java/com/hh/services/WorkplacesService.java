package com.hh.services;

import com.hh.models.Resume;
import com.hh.models.Workplace;

import java.util.List;
import java.util.Optional;

public interface WorkplacesService {
    Optional<Workplace> findById(Long id);
    Long save(Workplace workplace);
    void update(Workplace workplace);
    void delete(Long id);
    List<Workplace> workplacesOf(Resume resume);
}
