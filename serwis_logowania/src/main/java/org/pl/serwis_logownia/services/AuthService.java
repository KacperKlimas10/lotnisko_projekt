package org.pl.serwis_logownia.services;

import org.pl.serwis_logownia.config.DatabaseConfig;
import org.pl.serwis_logownia.entities.User;
import org.pl.serwis_logownia.entities.jsonUser;
import org.pl.serwis_logownia.repositories.UserRepository;
import org.pl.serwis_logownia.utils.HashHandler;
import org.pl.serwis_logownia.utils.JwtUtils;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    UserRepository userRepository = new UserRepository(DatabaseConfig.getConfiguredTemplate());

    public void registerUser(jsonUser user) {
        User userquery = new User();
        userquery.setLogin(user.getUsername());
        userquery.setHasło(user.getPassword());
        userRepository.insertUser(userquery);}

    public List<User> getUsers() {return userRepository.getUsersList();}

    public User getUser() {return userRepository.findByLogin("AdamusSkyrim");}

    public ResponseCookie loginUserCookie(jsonUser userFromJSON) {
        if (HashHandler.validateHash(userFromJSON.getPassword(),
                userRepository.findByLogin(userFromJSON.getUsername()).getHasło()))
        {
            User AuthUser = userRepository.findByLogin(userFromJSON.getUsername());
            // Tworzymy JWT Token
            String userToken = JwtUtils.generateJwtToken(AuthUser.getLogin(), AuthUser.getRola());
            // Tworzymy ciastko z tokenem
            CookieService cookieService = new CookieService();
            return cookieService.createCookie("jwtToken", userToken, 3600);
        }
        return null;
    }
}
