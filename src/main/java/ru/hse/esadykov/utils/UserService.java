package ru.hse.esadykov.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.hse.esadykov.dao.UserDao;
import ru.hse.esadykov.model.User;

/**
 * @author Ernest Sadykov
 * @since 14.06.2014
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("No authentication in current context");
        }

        return userDao.getUser(authentication.getName());
    }
}
