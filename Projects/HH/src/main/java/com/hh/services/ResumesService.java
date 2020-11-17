package com.hh.services;

import com.hh.models.Resume;
import com.hh.repositories.ResumesRepository;

import java.util.List;
import java.util.Optional;

public class ResumesService {
    ResumesRepository resumesRepository;

    public ResumesService(ResumesRepository resumesRepository) {
        this.resumesRepository = resumesRepository;
    }

    public Long save(Resume resume){
        return resumesRepository.saveEntity(resume);
    }

    public List<Resume> resumesOf(String login){
        return resumesRepository.resumesOf(login);
    }
    public List<Resume> allResumes(){
        return resumesRepository.findAll();
    }
    public void delete(Long id){
        resumesRepository.delete(id);
    }

    public Optional<Resume> findResume(Long id){
        return resumesRepository.findById(id);
    }
}
