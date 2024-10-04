package com.place4code.clone.service;

import com.place4code.clone.exception.NotFoundException;
import com.place4code.clone.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProfileService {

    private final UserService userService;

    public User findUserByIdAndCheckAuthentication(final Long userId) {
        final User user = userService.findById(userId);
        if (!user.getId().equals(userService.findLoggedInUser().getId())) {
            throw new NotFoundException("Nie masz dostępu do tego profilu!");
        }
        return user;
    }

}
