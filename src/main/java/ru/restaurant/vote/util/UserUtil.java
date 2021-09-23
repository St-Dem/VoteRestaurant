package ru.restaurant.vote.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.restaurant.vote.model.Role;
import ru.restaurant.vote.model.User;
import ru.restaurant.vote.to.UserDto;
import ru.restaurant.vote.to.UserTo;

@UtilityClass
public class UserUtil {

    public static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static UserTo createNewFromUser(User user){
        return new UserTo(user.id(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static UserDto createDtoFromUser(User user){
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}