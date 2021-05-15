package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.itis.springbootdemo.dto.ResumeForm;
import ru.itis.springbootdemo.models.Resume;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.models.Vacancy;
import ru.itis.springbootdemo.repositories.ResumesRepository;
import ru.itis.springbootdemo.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ResumesServiceImpl implements ResumesService {

    @Autowired
    ResumesRepository resumesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public void save(Resume resume) {
        resumesRepository.save(resume);
    }

    @Override
    public List<Resume> resumesOf(String accountEmail) {
        return resumesRepository.findByAccountEmail(accountEmail);
    }

    @Override
    public void createResume(ResumeForm resumeForm, String userEmail) {
        Resume resume = Resume.from(resumeForm);
        resume.setAccount(usersRepository.findByEmail(userEmail).orElseThrow(IllegalStateException::new));
        resumesRepository.save(resume);
    }

    @Override
    public List<Resume> resumes() {
        return resumesRepository.findAll();
    }

    @Override
    public void delete(Long id, String username) {
        Resume resume = resumesRepository.findById(id).orElseThrow(IllegalStateException::new);
        if (!resume.getAccount().getEmail().equals(username)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        List<User> users = resume.getLikes();
        int i = 0;
        while (i < users.size()) {
            User user = users.get(i);
            user.unlike_resume(resume);
            usersRepository.save(user);
            i++;
        }
        resumesRepository.save(resume);
        resumesRepository.deleteById(id);
    }

    @Override
    public Optional<Resume> findResume(Long id) {
        return resumesRepository.findById(id);
    }

    @Override
    public void update(Resume resume, String username) {
        if(!resume.getAccount().getEmail().equals(username)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        resumesRepository.save(resume);
    }

    @Override
    public void like(Long resumeId, String userEmail) {
        Resume resume = resumesRepository.findById(resumeId).orElseThrow(IllegalArgumentException::new);
        User user = usersRepository.findByEmail(userEmail).orElseThrow(IllegalArgumentException::new);
        user.like_resume(resume);
        resumesRepository.save(resume);
    }

    @Override
    public void unlike(Long resumeId, String userEmail) {
        Resume resume = resumesRepository.findById(resumeId).orElseThrow(IllegalArgumentException::new);
        User user = usersRepository.findByEmail(userEmail).orElseThrow(IllegalArgumentException::new);
        user.unlike_resume(resume);
        resumesRepository.save(resume);
    }

    @Override
    public boolean isLiked(Long resumeId, String userEmail) {
        Resume resume = resumesRepository.findById(resumeId).orElseThrow(IllegalArgumentException::new);
        User user = usersRepository.findByEmail(userEmail).orElseThrow(IllegalArgumentException::new);
        return resume.getLikes().contains(user);
    }
}
