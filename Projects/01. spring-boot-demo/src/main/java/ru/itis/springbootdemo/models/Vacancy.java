package ru.itis.springbootdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.sql.Timestamp;

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
    @ManyToOne
    @JoinColumn(name = "account_id")
    User account;
}

