package ru.dmkuranov.examplewebsecurity.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dmkuranov.examplewebsecurity.balance.service.BalanceService;
import ru.dmkuranov.examplewebsecurity.security.dao.UserDao;
import ru.dmkuranov.examplewebsecurity.security.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserManager {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BalanceService balanceService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByLogin(username);
        if(user!=null) {
            return new AppUserDetails(user.getLogin(), user.getPassword(), user.getId());
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Transactional
    public User registerUser(String username, String password) {
        User user = userDao.createUser(username, passwordEncoder.encode(password));
        balanceService.initBalance(user);
        return user;
    }

    public static class AppUserDetails<T extends GrantedAuthority> implements org.springframework.security.core.userdetails.UserDetails {

        private List<T> authorities = new ArrayList<T>();
        private String password;
        private String username;
        private Integer id;

        private AppUserDetails(String username, String password, Integer id) {
            this.password = password;
            this.username = username;
            this.id = id;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        public Integer getId() {
            return id;
        }
    }
}
