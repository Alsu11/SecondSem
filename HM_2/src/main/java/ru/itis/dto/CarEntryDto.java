package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.CarEntry;
import ru.itis.models.Parking;
import ru.itis.models.User;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarEntryDto {

    private String model;
    private String carNumber;
    private String color;
    private Instant startTime;
    private Instant endTime;
    private User user;
    private Parking parking;

    public static CarEntryDto from(CarEntry entry) {
        return CarEntryDto.builder()
                .model(entry.getModel())
                .carNumber(entry.getCarNumber())
                .color(entry.getColor())
                .startTime(entry.getStartTime())
                .endTime((entry.getEndTime()))
                .user(entry.getUser())
                .parking(entry.getParking())
                .build();
    }

    public static List<CarEntryDto> from(List<CarEntry> entry) {
        return entry.stream().map(CarEntryDto::from).collect(Collectors.toList());
    }

}
