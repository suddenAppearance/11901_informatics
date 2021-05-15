package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.dto.ResumeForm;
import ru.itis.springbootdemo.models.Resume;
import ru.itis.springbootdemo.models.User;

import java.util.List;
import java.util.Optional;

public interface ResumesService {
    void save(Resume resume);

    List<Resume> resumesOf(String accountEmail);

    void createResume(ResumeForm resumeForm, String userEmail);

    List<Resume> resumes();

    void delete(Long id, String username);

    Optional<Resume> findResume(Long id);

    void update(Resume resume, String username);

    void like(Long resumeId, String userEmail);

    void unlike(Long resumeId, String userEmail);

    boolean isLiked(Long resumeId, String userEmail);

}
