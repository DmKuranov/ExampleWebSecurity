package ru.dmkuranov.examplewebsecurity.balance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dmkuranov.examplewebsecurity.security.dao.UserDao;
import ru.dmkuranov.examplewebsecurity.security.domain.User;
import ru.dmkuranov.examplewebsecurity.security.util.SecurityHelper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class BalanceService {
    @Autowired
    private SecurityHelper securityHelper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    @PreAuthorize("isAuthenticated()")
    public BigDecimal getCurrentUserBalance() {
        return getUserBalance(securityHelper.getCurrentUser());
    }

    @Transactional(readOnly = true)
    @PreAuthorize("isAuthenticated() && (principal.id==#user.id || hasRole('ROLE_Admin'))")
    public BigDecimal getUserBalance(User user) {
        List<BigDecimal> decimals = jdbcTemplate.query("select balance from user_balances where user_id=?"
                , new RowMapper<BigDecimal>() {
                    @Override
                    public BigDecimal mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getBigDecimal("balance");
                    }
                }
                , new Object[]{user.getId()}
        );
        if (!decimals.isEmpty()) {
            return decimals.get(0);
        }
        return null;
    }

    public void initBalance(User user) {
        jdbcTemplate.update("insert into user_balances (user_id, balance) values(?, ?)", new Object[]{user.getId(), new BigDecimal(0)});
    }
}