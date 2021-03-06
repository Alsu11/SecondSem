package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.validation.annotations.CorrectCarNumber;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveDto {

    @NotBlank(message = "The car number of must be filled in")
    @CorrectCarNumber(carNumber = "carNumber")
    private String carNumber;

}
