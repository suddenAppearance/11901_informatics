package ru.itis.models;

import lombok.*;
import ru.itis.dto.ClassroomForm;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    Short number;
    @OneToMany(mappedBy = "classroom")
    List<Journal> history;

    public static Classroom from(ClassroomForm classroomForm){
        return Classroom.builder()
                .number(classroomForm.getNumber())
                .build();
    }
}
