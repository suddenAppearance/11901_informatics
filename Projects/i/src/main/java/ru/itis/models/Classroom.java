package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Short number;
    @OneToMany(mappedBy = "classroom")
    List<Journal> history;
}
