package com.hh.services;

import com.hh.models.Resume;
import com.hh.models.User;

import java.util.List;
import java.util.Optional;

public interface ResumesService {
    public Long save(Resume resume);
    List<Resume> resumesOf(String login);
    List<Resume> allResumes();
    void delete(Long id);
    Optional<Resume> findResume(Long id);
    void update(Resume resume);
    void like(Resume resume, User user);
    void unlike(Resume resume, User user);
    boolean is_liked(Resume resume, User user);
    List<Resume> saved(User user);
}
