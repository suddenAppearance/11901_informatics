package com.hh.repositories;

import com.hh.models.Resume;
import com.hh.models.User;

import java.util.List;
import java.util.Optional;

public interface ResumesRepository extends CrudRepository<Resume> {
    Long saveEntity(Resume resume);
    List<Resume> resumesOf(String login);
    Optional<Resume> findById(Long id);
    void like(Resume resume, User user);
    void unlike(Resume resume, User user);
}
