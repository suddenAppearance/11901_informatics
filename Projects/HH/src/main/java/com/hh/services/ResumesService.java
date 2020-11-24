package com.hh.services;

import com.hh.models.Resume;
import com.hh.models.User;
import com.hh.models.Vacancy;
import com.hh.repositories.ResumesRepository;

import java.util.List;
import java.util.Optional;

public class ResumesService {
    ResumesRepository resumesRepository;

    public ResumesService(ResumesRepository resumesRepository) {
        this.resumesRepository = resumesRepository;
    }

    public Long save(Resume resume) {
        return resumesRepository.saveEntity(resume);
    }

    public List<Resume> resumesOf(String login) {
        return resumesRepository.resumesOf(login);
    }

    public List<Resume> allResumes() {
        return resumesRepository.findAll();
    }

    public void delete(Long id) {
        resumesRepository.delete(id);
    }

    public Optional<Resume> findResume(Long id) {
        return resumesRepository.findById(id);
    }

    public void update(Resume resume) {
        resumesRepository.update(resume);
    }
    public void like(Resume resume, User user){
        resumesRepository.like(resume, user);
    }
    public void unlike(Resume resume, User user){
        resumesRepository.unlike(resume, user);
    }
    public boolean is_liked(Resume resume, User user){
        return resumesRepository.is_liked(resume, user);
    }
    public List<Resume> saved(User user){
        return resumesRepository.liked(user);
    }
}
