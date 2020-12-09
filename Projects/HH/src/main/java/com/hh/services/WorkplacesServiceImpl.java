package com.hh.services;

import com.hh.models.Resume;
import com.hh.models.Workplace;
import com.hh.repositories.WorkplacesRepository;

import java.util.List;
import java.util.Optional;

public class WorkplacesServiceImpl implements WorkplacesService{
    WorkplacesRepository workplacesRepository;
    public WorkplacesServiceImpl(WorkplacesRepository workplacesRepository){
        this.workplacesRepository = workplacesRepository;
    }
    public Optional<Workplace> findById(Long id){
        return workplacesRepository.findById(id);
    }
    public Long save(Workplace workplace){
        return workplacesRepository.saveEntity(workplace);
    }
    public void update(Workplace workplace){
        workplacesRepository.update(workplace);
    }

    public void delete(Long id){
        workplacesRepository.delete(id);
    }

    public List<Workplace> workplacesOf(Resume resume){
        return workplacesRepository.workplacesAtResume(resume);
    }
}
