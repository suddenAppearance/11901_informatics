package ru.itis.models;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.dto.JournalDto;
import ru.itis.dto.JournalForm;
import ru.itis.services.UsersService;

import javax.persistence.*;
import javax.persistence.criteria.Join;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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
