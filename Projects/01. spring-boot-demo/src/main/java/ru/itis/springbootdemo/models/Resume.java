package ru.itis.springbootdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
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
    String sphere;
    String schedule;
    String type;
    String description;
    Integer salary;
    String contact_info;
    @ManyToOne
    @JoinColumn(name = "account_id")
    User account;
    @OneToMany(mappedBy = "resume")
    List<Workplace> workplaces;
}
