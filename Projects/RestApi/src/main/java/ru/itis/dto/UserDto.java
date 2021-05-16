package ru.itis.dto;

import lombok.*;
import ru.itis.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    Long id;
    String role;
    String fullName;
    List<Short> history;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .role(user.getRole().toString())
                .fullName(user.getFullName())
                .history(user.getHistory() == null ? new ArrayList<>() : user.getHistory().stream().map(
                        (s) -> s.getClassroom().getNumber()).collect(Collectors.toList()))
                .build();
    }

    public static List<UserDto> from(List<User> users){
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
