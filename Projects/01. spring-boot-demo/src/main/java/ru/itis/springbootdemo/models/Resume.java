package ru.itis.springbootdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.springbootdemo.dto.ResumeForm;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Timestamp created;
    Boolean readyToBusinessTrip;
    Boolean moving;
    String schedule;
    String type;
    String description;
    Integer salary;
    String contactInfo;
    @ManyToOne
    @JoinColumn(name = "account_id")
    User account;
    @ManyToMany(mappedBy = "favouriteResumes")
    List<User> likes;

    public static Resume from(ResumeForm resumeForm){
        return Resume.builder()
                .name(resumeForm.getName())
                .created(Timestamp.from(Instant.now()))
                .readyToBusinessTrip(resumeForm.getReadyToBusinessTrip())
                .moving(resumeForm.getMoving())
                .schedule(resumeForm.getSchedule())
                .type(resumeForm.getType())
                .description(resumeForm.getDescription())
                .salary(resumeForm.getSalary())
                .contactInfo(resumeForm.getContactInfo()).build();
    }
}
