package ru.itis.mappers;

import org.springframework.stereotype.Component;
import ru.itis.dto.SignUpForm;
import ru.itis.dto.UserDto;
import ru.itis.models.User;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-03T14:08:26+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)"
)
*/
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setEmail( user.getEmail() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setMoney( user.getMoney() );

        return userDto;
    }

    @Override
    public User toRequest(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setEmail( userDto.getEmail() );
        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        user.setMoney( userDto.getMoney() );

        return user;
    }

    @Override
    public User toRequest(SignUpForm signUpForm) {
        if ( signUpForm == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( signUpForm.getEmail() );
        user.setPassword( signUpForm.getPassword() );
        user.setFirstName( signUpForm.getFirstName() );
        user.setLastName( signUpForm.getLastName() );
        user.setMoney( signUpForm.getMoney() );

        return user;
    }
}
