package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.springbootdemo.dto.ResumeForm;
import ru.itis.springbootdemo.models.Resume;
import ru.itis.springbootdemo.services.UsersService;
import ru.itis.springbootdemo.services.ResumesService;

@Controller
public class ResumesController {
    @Autowired
    ResumesService resumesService;
    @Autowired
    UsersService usersService;


    @GetMapping("/resumes")
    public String getResumes(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
        }
        model.addAttribute("resumes", resumesService.resumes());
        return "resumes_page";
    }

    @GetMapping("/resume")
    public String getResume(@RequestParam("id") Long resumeId, Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
        }
        Resume resume = resumesService.findResume(resumeId).orElseThrow(IllegalStateException::new);
        model.addAttribute("resume", resume);
        model.addAttribute("owner", authentication != null && resume.getAccount().getEmail().equals(authentication.getName()));
        model.addAttribute("isLiked", authentication != null && resumesService.isLiked(resumeId, authentication.getName()));
        return "resume_page";
    }

    @GetMapping("/resume/create")
    public String getResumeCreate(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
        }
        return "resume_create";
    }

    @PostMapping("resume/create")
    public String postResumeCreate(ResumeForm resumeForm, Authentication authentication) {
        resumesService.createResume(resumeForm, authentication.getName());
        return "redirect:/profile/resumes";
    }

    @GetMapping("resume/edit")
    public String getResumeEdit(@RequestParam("id") Long resumeId, Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
        }
        Resume resume = resumesService.findResume(resumeId).orElseThrow(IllegalStateException::new);
        model.addAttribute("resume", resume);
        return "resume_edit";
    }

    @PostMapping("resume/edit")
    public String postResumeEdit(@RequestParam("id") Long id, ResumeForm resumeForm, Authentication authentication) {
        Resume resume = Resume.from(resumeForm);
        resume.setId(id);
        resume.setAccount(usersService.findByEmail(authentication.getName()));
        resumesService.update(resume);
        return "redirect:/profile/resumes";
    }

    @PostMapping("resume/delete")
    public String postResumeDelete(@RequestParam("id") Long id) {
        resumesService.delete(id);
        return "redirect:/profile/resumes";
    }

    @PostMapping("/resume/like")
    @ResponseBody
    public ResponseEntity<String> postResumeLike(@RequestParam("id") Long id, Authentication authentication) {
        resumesService.like(id, authentication.getName());
        return ResponseEntity.ok("success");
    }

    @PostMapping("/resume/unlike")
    @ResponseBody
    public ResponseEntity<String> postResumeUnLike(@RequestParam("id") Long id, Authentication authentication) {
        resumesService.unlike(id, authentication.getName());
        return ResponseEntity.ok("success");
    }

    @GetMapping("/profile/resumes")
    public String getMyResumes(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("resumes", resumesService.resumesOf(authentication.getName()));
        return "resumes_page";
    }
    @GetMapping("/profile/resumes/saved")
    public String getMyFavouriteResumes(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("resumes", usersService.findByEmail(authentication.getName()).getFavouriteResumes);
        return "resumes_page";
    }

}
