package ru.itis.springbootdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import ru.itis.springbootdemo.dto.VacancyForm;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Timestamp created;
    String name;
    String schedule;
    String type;
    String paymentSchedule;
    Integer experience;
    String place;
    String address;
    String requirements;
    String description;
    Integer salary;
    String contactInfo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    User account;
    @ManyToMany(mappedBy = "favouriteVacancies")
    List<User> likes;

    public static Vacancy from(VacancyForm vacancyForm){
        return Vacancy.builder()
                .id(vacancyForm.getId())
                .name(vacancyForm.getName())
                .schedule(vacancyForm.getSchedule())
                .type(vacancyForm.getType())
                .paymentSchedule(vacancyForm.getPaymentSchedule())
                .experience(vacancyForm.getExperience())
                .place(vacancyForm.getPlace())
                .created(Timestamp.from(Instant.now()))
                .address(vacancyForm.getAddress())
                .requirements(vacancyForm.getRequirements())
                .description(vacancyForm.getDescription())
                .contactInfo(vacancyForm.getContactInfo())
                .salary(vacancyForm.getSalary()).build();
    }

}

