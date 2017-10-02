package ru.dmkuranov.examplewebsecurity.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*
    Отдельный класс в связи с особенностями конструирования(CGLIB, не используется weaver)
    Но это не точно(то, что обойтись без этого невозможно).
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userManager.loadUserByUsername(username);
    }
}
