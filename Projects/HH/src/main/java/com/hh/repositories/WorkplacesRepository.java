package com.hh.repositories;

import com.hh.models.Resume;
import com.hh.models.Workplace;

import java.util.List;
import java.util.Optional;

public interface WorkplacesRepository extends CrudRepository<Workplace> {
    List<Workplace> workplacesAtResume(Resume resume);
    Optional<Workplace> findById(Long id);
    Long saveEntity(Workplace entity);
}
