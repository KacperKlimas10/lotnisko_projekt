package org.pl.serwis_logownia.repositories;

import org.pl.serwis_logownia.entities.User;
import org.pl.serwis_logownia.utils.HashHandler;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserRepository {

    JdbcTemplate jdbcTemplate;
    public UserRepository(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    public User findByLogin(String login) {
        String query = "SELECT * FROM Użytkownik WHERE login = ?;";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{login},
                    new BeanPropertyRowMapper<>(User.class));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> getUsersList() {
        String query = "SELECT * FROM Użytkownik;";
        try {
            return jdbcTemplate.query(query,
                    new BeanPropertyRowMapper<>(User.class));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertUser(User user) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO Użytkownik (Login, Hasło) VALUES (?, ?);",
                    user.getLogin(), HashHandler.sha256(user.getHasło()));
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
