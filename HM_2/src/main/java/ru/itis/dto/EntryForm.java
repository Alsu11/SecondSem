package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntryForm {
    private String model;
    private String carNumber;
    private String color;
    private Instant startTime;
    private Instant endTime;
    private Long userId;
    private String address;
}
