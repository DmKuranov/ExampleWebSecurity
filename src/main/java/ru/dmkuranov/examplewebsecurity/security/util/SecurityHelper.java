package ru.dmkuranov.examplewebsecurity.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.dmkuranov.examplewebsecurity.security.dao.UserDao;
import ru.dmkuranov.examplewebsecurity.security.domain.User;
import ru.dmkuranov.examplewebsecurity.security.service.UserManager;

@Service("securityHelper")
public class SecurityHelper {

    @Autowired
    private UserDao userDao;

    public UserManager.AppUserDetails getCurrentUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserManager.AppUserDetails) {
            return (UserManager.AppUserDetails) principal;
        }
        return null;
    }

    public User getCurrentUser() {
        UserManager.AppUserDetails details = getCurrentUserDetails();
        if (details != null) {
            return userDao.getUserById(details.getId());
        }
        return null;
    }
}
