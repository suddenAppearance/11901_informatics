package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    Classroom classroom;

    Date taken_at;
    Date returned_at;
}
