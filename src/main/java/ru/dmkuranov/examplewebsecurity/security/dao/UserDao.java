package ru.dmkuranov.examplewebsecurity.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ru.dmkuranov.examplewebsecurity.security.domain.User;

import java.sql.*;
import java.util.List;

@Service
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User createUser(String username, String encodedPassword) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = insertStatementCreator.createFor(username, encodedPassword);
        jdbcTemplate.update(psc, keyHolder);
        int userId = keyHolder.getKey().intValue();
        return getUserById(userId);
    }

    public User getUserByLogin(String login) {
        List<User> users = jdbcTemplate.query("select id, login, password from users where login=?", userRowMapper, new Object[]{login});
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }


    public User getUserById(int id) {
        List<User> users = jdbcTemplate.query("select id, login, password from users where id=?", userRowMapper, new Object[]{id});
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    private static RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new User(
                    resultSet.getInt("id")
                    , resultSet.getString("login")
                    , resultSet.getString("password")
            );
        }
    };

    private static final InsertStatementCreator insertStatementCreator = new InsertStatementCreator();

    private static class InsertStatementCreator {
        public PreparedStatementCreator createFor(final String username, final String password) {
            return new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    final PreparedStatement ps = connection.prepareStatement(
                            "insert into users (login, password) values (?, ?)"
                            , Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, username);
                    ps.setString(2, password);
                    return ps;
                }
            };
        }
    }
}
