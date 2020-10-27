package com.hh.models;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Workplace {
    Long id;
    String company_name;
    Timestamp started;
    Timestamp finished;
    String description;
}
