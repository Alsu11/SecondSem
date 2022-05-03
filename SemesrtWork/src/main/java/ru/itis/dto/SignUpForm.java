package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpForm {

    @Email
    private String email;

    @Size(min = 5, max = 15)
    private String password;
    private String firstName;
    private String lastName;
}
