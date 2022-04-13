package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dao.UserRepository;
import ru.itis.dto.SignInForm;
import ru.itis.dto.SignUpForm;
import ru.itis.dto.UserDto;
import ru.itis.exceptions.*;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import java.time.Instant;
import java.util.Optional;

import static ru.itis.dto.UserDto.from;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    @Override
    public UserDto signIn(SignInForm signInForm) {
        User user = userRepository.findByEmail(signInForm.getEmail()).orElseThrow(() -> new NotFoundException(ErrorEntity.INVALID_EMAIL));

        if(user.getHashPassword().equals(signInForm.getPassword())) {
            return from(user);
        } else {
            throw new IncorrectInput(ErrorEntity.INCORRECT_PASSWORD);
        }
    }

    @Override
    public UserDto signUp(SignUpForm signUpForm) {
        Optional<User> userOptional = userRepository.findByEmail(signUpForm.getEmail());

        if(userOptional.isEmpty()) {

            User user = User.builder()
                    .email(signUpForm.getEmail())
                    .firstName(signUpForm.getFirstName())
                    .lastName(signUpForm.getLastName())
                    .hashPassword(signUpForm.getPassword())
                    .money(signUpForm.getMoney())
                    .build();

            user.setCreatedAt(Instant.now());

            return from(userRepository.save(user));
        }
        else {
            throw new AlreadyExistsException(ErrorEntity.EMAIL_ALREADY_TAKEN);
        }
    }
}
